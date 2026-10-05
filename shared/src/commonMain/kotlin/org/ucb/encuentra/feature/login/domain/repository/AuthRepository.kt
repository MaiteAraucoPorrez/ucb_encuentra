package org.ucb.encuentra.feature.login.domain.repository

import org.ucb.encuentra.core.domain.model.UserModel
import org.ucb.encuentra.core.domain.vo.Email
import org.ucb.encuentra.core.domain.vo.Password

interface AuthRepository {
    suspend fun login(email: Email, password: Password): Result<UserModel>
}
