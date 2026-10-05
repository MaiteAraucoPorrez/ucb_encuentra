package org.ucb.encuentra.core.data

import org.ucb.encuentra.core.domain.model.UserModel
import org.ucb.encuentra.core.domain.repository.SessionRepository

class SessionRepositoryImpl : SessionRepository {
    override fun getCurrentUser(): UserModel? = SessionStore.get()
    override fun setCurrentUser(user: UserModel) = SessionStore.set(user)
    override fun clearSession() = SessionStore.clear()
}
