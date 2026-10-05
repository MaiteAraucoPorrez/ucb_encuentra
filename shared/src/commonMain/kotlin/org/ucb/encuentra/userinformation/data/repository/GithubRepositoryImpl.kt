package org.ucb.encuentra.userinformation.data.repository

import org.ucb.encuentra.userinformation.data.datasource.GithubRemoteDataSource
import org.ucb.encuentra.userinformation.data.mapper.toDomain
import org.ucb.encuentra.userinformation.domain.model.UserInfoModel
import org.ucb.encuentra.userinformation.domain.repository.GithubRepository

class GithubRepositoryImpl(
    private val dataSource: GithubRemoteDataSource
) : GithubRepository {
    override suspend fun findByAlias(alias: String): Result<UserInfoModel> = runCatching {
        dataSource.getUser(alias).toDomain(alias)
    }
}
