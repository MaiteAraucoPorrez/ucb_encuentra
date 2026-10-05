package org.ucb.encuentra.feature.signup.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ucb.encuentra.feature.signup.domain.usecase.RegisterUseCase

/**
 * MVVM (sin Intent/Effect): el ViewModel expone funciones directas,
 * igual convención que usamos en el proyecto de movies para Sign Up.
 */
class RegisterViewModel(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state.asStateFlow()

    fun onFullNameChanged(value: String) = _state.update { it.copy(fullName = value, errorMessage = null) }
    fun onEmailChanged(value: String) = _state.update { it.copy(email = value, errorMessage = null) }
    fun onPasswordChanged(value: String) = _state.update { it.copy(password = value, errorMessage = null) }
    fun onConfirmPasswordChanged(value: String) = _state.update { it.copy(confirmPassword = value, errorMessage = null) }
    fun onTogglePasswordVisibility() = _state.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }

    fun onSubmit() {
        val current = _state.value
        if (current.password != current.confirmPassword) {
            _state.update { it.copy(errorMessage = "Las contraseñas no coinciden") }
            return
        }
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            registerUseCase(current.fullName, current.email, current.password)
                .onSuccess {
                    _state.update { it.copy(isLoading = false, registerSuccess = true) }
                }
                .onFailure { error ->
                    _state.update { it.copy(isLoading = false, errorMessage = error.message) }
                }
        }
    }
}
