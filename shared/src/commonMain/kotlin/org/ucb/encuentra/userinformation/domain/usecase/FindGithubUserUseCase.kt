package org.ucb.encuentra.userinformation.domain.usecase

import org.ucb.encuentra.userinformation.domain.model.UserInfoModel
import org.ucb.encuentra.userinformation.domain.repository.GithubRepository

/**
 * En el repo del docente (rama feature/userinformation) el ViewModel llama
 * a un UseCase, no al repositorio directo. Lo agregamos aquí para respetar
 * esa capa (en el proyecto de movies el ViewModel llamaba al repo directo).
 */
class FindGithubUserUseCase(private val repository: GithubRepository) {
    suspend operator fun invoke(alias: String): Result<UserInfoModel> = repository.findByAlias(alias)
}
