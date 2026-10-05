package org.ucb.encuentra.userinformation.domain.repository

import org.ucb.encuentra.userinformation.domain.model.UserInfoModel

interface GithubRepository {
    suspend fun findByAlias(alias: String): Result<UserInfoModel>
}
