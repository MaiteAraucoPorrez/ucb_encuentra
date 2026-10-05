package org.ucb.encuentra

import kotlinx.coroutines.test.runTest
import org.ucb.encuentra.userinformation.data.datasource.GithubRemoteDataSource
import org.ucb.encuentra.userinformation.data.dto.UserInfoDto
import org.ucb.encuentra.userinformation.data.mapper.toDomain
import org.ucb.encuentra.userinformation.data.repository.GithubRepositoryImpl
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GithubRepositoryTest {

    private class FakeGithubRemoteDataSource : GithubRemoteDataSource {
        override suspend fun getUser(nickname: String): UserInfoDto {
            return UserInfoDto(
                email = "octocat@github.com",
                avatarUrl = "https://github.com/images/error/octocat_happy.gif"
            )
        }
    }

    @Test
    fun testUserInfoDtoToDomain() {
        val dto = UserInfoDto(email = "user@example.com", avatarUrl = "https://example.com/avatar.png")
        val domain = dto.toDomain(alias = "user")

        assertEquals("user@example.com", domain.email)
        assertEquals("user", domain.alias)
    }

    @Test
    fun testRepositoryFindByAlias() = runTest {
        val repository = GithubRepositoryImpl(FakeGithubRemoteDataSource())
        val result = repository.findByAlias("octocat")

        assertTrue(result.isSuccess)
        assertEquals("octocat@github.com", result.getOrNull()?.email)
    }
}
