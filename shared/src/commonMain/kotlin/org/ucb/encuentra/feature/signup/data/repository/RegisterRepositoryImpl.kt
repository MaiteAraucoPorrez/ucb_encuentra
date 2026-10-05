package org.ucb.encuentra.feature.signup.data.repository

import org.ucb.encuentra.core.data.UserLocalDataSource
import org.ucb.encuentra.core.domain.model.UserModel
import org.ucb.encuentra.core.domain.vo.Email
import org.ucb.encuentra.feature.signup.domain.repository.RegisterRepository

class RegisterRepositoryImpl(private val users: UserLocalDataSource) : RegisterRepository {
    override suspend fun register(fullName: String, email: Email, password: String): Result<UserModel> =
        users.register(fullName, email, password)
}
