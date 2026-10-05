package org.ucb.encuentra.feature.userstate.domain.usecase

import org.ucb.encuentra.core.domain.model.UserModel
import org.ucb.encuentra.core.domain.repository.SessionRepository

class GetCurrentUserUseCase(private val session: SessionRepository) {
    operator fun invoke(): UserModel? = session.getCurrentUser()
}
