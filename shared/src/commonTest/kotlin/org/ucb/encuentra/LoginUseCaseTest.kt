package org.ucb.encuentra

import kotlinx.coroutines.test.runTest
import org.ucb.encuentra.core.domain.vo.Email
import org.ucb.encuentra.core.domain.vo.Password
import org.ucb.encuentra.feature.login.domain.usecase.LoginUseCase
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class LoginUseCaseTest {

    @Test
    fun rejectsEmailOutsideInstitutionalDomain() = runTest {
        val repo = FakeAuthRepository()
        val session = FakeSessionRepository()

        val result = LoginUseCase(repo, session)(Email("mateo@gmail.com"), Password("123456"))

        assertTrue(result.isFailure)
        assertEquals(0, repo.calls) // ni siquiera llega al repositorio
        assertNull(session.user)
    }

    @Test
    fun rejectsShortPassword() = runTest {
        val repo = FakeAuthRepository()

        val result = LoginUseCase(repo, FakeSessionRepository())(Email("mateo@ucb.edu.bo"), Password("123"))

        assertTrue(result.isFailure)
        assertEquals(0, repo.calls)
    }

    @Test
    fun successStoresSession() = runTest {
        val session = FakeSessionRepository()

        val result = LoginUseCase(FakeAuthRepository(), session)(Email("mateo@ucb.edu.bo"), Password("123456"))

        assertTrue(result.isSuccess)
        assertEquals(testUser, session.user)
    }

    @Test
    fun repositoryFailureDoesNotStoreSession() = runTest {
        val repo = FakeAuthRepository(Result.failure(IllegalArgumentException("Usuario o contraseña incorrectos")))
        val session = FakeSessionRepository()

        val result = LoginUseCase(repo, session)(Email("mateo@ucb.edu.bo"), Password("123456"))

        assertTrue(result.isFailure)
        assertNull(session.user)
    }
}
