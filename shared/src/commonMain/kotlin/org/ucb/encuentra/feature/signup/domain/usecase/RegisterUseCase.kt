package org.ucb.encuentra.feature.signup.domain.usecase

import org.ucb.encuentra.core.domain.model.UserModel
import org.ucb.encuentra.core.domain.repository.SessionRepository
import org.ucb.encuentra.core.domain.vo.Email
import org.ucb.encuentra.core.domain.vo.Password
import org.ucb.encuentra.feature.signup.domain.repository.RegisterRepository

class RegisterUseCase(
    private val repository: RegisterRepository,
    private val session: SessionRepository
) {
    suspend operator fun invoke(fullName: String, emailRaw: String, password: String): Result<UserModel> {
        if (fullName.isBlank()) return Result.failure(IllegalArgumentException("El nombre es obligatorio"))

        val email = Email(emailRaw)
        if (!email.isValid()) return Result.failure(IllegalArgumentException("Usa tu correo institucional @ucb.edu.bo"))

        val passwordVo = Password(password)
        if (!passwordVo.isValid()) return Result.failure(IllegalArgumentException("La contraseña debe tener 6+ caracteres"))

        return repository.register(fullName, email, password).onSuccess { session.setCurrentUser(it) }
    }
}
