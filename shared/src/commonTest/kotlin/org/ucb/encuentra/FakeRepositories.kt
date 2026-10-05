package org.ucb.encuentra

import org.ucb.encuentra.core.domain.model.UserModel
import org.ucb.encuentra.core.domain.repository.SessionRepository
import org.ucb.encuentra.core.domain.vo.Email
import org.ucb.encuentra.core.domain.vo.Password
import org.ucb.encuentra.feature.login.domain.repository.AuthRepository
import org.ucb.encuentra.feature.signup.domain.repository.RegisterRepository

val testUser = UserModel(id = "mateo@ucb.edu.bo", fullName = "Mateo Rojas", email = Email("mateo@ucb.edu.bo"))

class FakeSessionRepository : SessionRepository {
    var user: UserModel? = null
    override fun getCurrentUser(): UserModel? = user
    override fun setCurrentUser(user: UserModel) { this.user = user }
    override fun clearSession() { user = null }
}

class FakeAuthRepository(private val result: Result<UserModel> = Result.success(testUser)) : AuthRepository {
    var calls = 0
    override suspend fun login(email: Email, password: Password): Result<UserModel> {
        calls++
        return result
    }
}

class FakeRegisterRepository(private val result: Result<UserModel> = Result.success(testUser)) : RegisterRepository {
    var calls = 0
    override suspend fun register(fullName: String, email: Email, password: String): Result<UserModel> {
        calls++
        return result
    }
}
