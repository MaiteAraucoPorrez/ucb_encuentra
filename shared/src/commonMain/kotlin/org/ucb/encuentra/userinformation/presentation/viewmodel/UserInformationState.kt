package org.ucb.encuentra.userinformation.presentation.viewmodel

import org.ucb.encuentra.userinformation.domain.model.UserInfoModel

sealed interface UserInformationUiState {
    data object Idle : UserInformationUiState
    data object Loading : UserInformationUiState
    data class Success(val userInfo: UserInfoModel) : UserInformationUiState
    data class Error(val message: String) : UserInformationUiState
}
