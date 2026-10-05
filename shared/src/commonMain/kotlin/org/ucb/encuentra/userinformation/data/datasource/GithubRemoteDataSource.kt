package org.ucb.encuentra.userinformation.data.datasource

import org.ucb.encuentra.userinformation.data.dto.UserInfoDto

interface GithubRemoteDataSource {
    suspend fun getUser(nickname: String): UserInfoDto
}
