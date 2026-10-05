package org.ucb.encuentra

import kotlinx.coroutines.test.runTest
import org.ucb.encuentra.feature.signup.domain.usecase.RegisterUseCase
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class RegisterUseCaseTest {

    @Test
    fun rejectsBlankName() = runTest {
        val repo = FakeRegisterRepository()

        val result = RegisterUseCase(repo, FakeSessionRepository())("  ", "mateo@ucb.edu.bo", "123456")

        assertTrue(result.isFailure)
        assertEquals(0, repo.calls)
    }

    @Test
    fun rejectsNonInstitutionalEmail() = runTest {
        val repo = FakeRegisterRepository()

        val result = RegisterUseCase(repo, FakeSessionRepository())("Mateo", "mateo@gmail.com", "123456")

        assertTrue(result.isFailure)
        assertEquals(0, repo.calls)
    }

    @Test
    fun rejectsShortPassword() = runTest {
        val repo = FakeRegisterRepository()

        val result = RegisterUseCase(repo, FakeSessionRepository())("Mateo", "mateo@ucb.edu.bo", "123")

        assertTrue(result.isFailure)
        assertEquals(0, repo.calls)
    }

    @Test
    fun successLogsTheUserIn() = runTest {
        val session = FakeSessionRepository()

        val result = RegisterUseCase(FakeRegisterRepository(), session)("Mateo", "mateo@ucb.edu.bo", "123456")

        assertTrue(result.isSuccess)
        assertEquals(testUser, session.user)
    }

    @Test
    fun duplicatedEmailDoesNotLogIn() = runTest {
        val repo = FakeRegisterRepository(Result.failure(IllegalStateException("Ya existe una cuenta con ese correo")))
        val session = FakeSessionRepository()

        val result = RegisterUseCase(repo, session)("Mateo", "mateo@ucb.edu.bo", "123456")

        assertTrue(result.isFailure)
        assertNull(session.user)
    }
}
