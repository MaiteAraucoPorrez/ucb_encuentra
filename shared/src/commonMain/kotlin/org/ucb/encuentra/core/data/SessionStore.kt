package org.ucb.encuentra.core.data

import org.ucb.encuentra.core.domain.model.UserModel

object SessionStore {
    private var currentUser: UserModel? = null

    fun get(): UserModel? = currentUser
    fun set(user: UserModel) { currentUser = user }
    fun clear() { currentUser = null }
}
