package org.ucb.encuentra.feature.signup.domain.repository

import org.ucb.encuentra.core.domain.model.UserModel
import org.ucb.encuentra.core.domain.vo.Email

interface RegisterRepository {
    suspend fun register(fullName: String, email: Email, password: String): Result<UserModel>
}
