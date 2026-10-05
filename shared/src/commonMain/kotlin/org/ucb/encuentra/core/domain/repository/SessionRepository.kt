package org.ucb.encuentra.core.domain.repository

import org.ucb.encuentra.core.domain.model.UserModel

interface SessionRepository {
    fun getCurrentUser(): UserModel?
    fun setCurrentUser(user: UserModel)
    fun clearSession()
}
