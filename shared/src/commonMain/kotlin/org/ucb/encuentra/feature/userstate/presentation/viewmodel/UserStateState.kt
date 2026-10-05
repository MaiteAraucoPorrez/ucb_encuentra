package org.ucb.encuentra.feature.userstate.presentation.viewmodel

import org.ucb.encuentra.core.domain.model.UserModel

data class UserStateState(
    val isLoading: Boolean = false,
    val user: UserModel? = null,
    val pendingMatchesCount: Int = 0
)
