package org.ucb.encuentra.feature.login.data.repository

import org.ucb.encuentra.core.data.UserLocalDataSource
import org.ucb.encuentra.core.domain.model.UserModel
import org.ucb.encuentra.core.domain.vo.Email
import org.ucb.encuentra.core.domain.vo.Password
import org.ucb.encuentra.feature.login.domain.repository.AuthRepository

class AuthRepositoryImpl(private val users: UserLocalDataSource) : AuthRepository {
    override suspend fun login(email: Email, password: Password): Result<UserModel> =
        users.login(email, password.value)
}
