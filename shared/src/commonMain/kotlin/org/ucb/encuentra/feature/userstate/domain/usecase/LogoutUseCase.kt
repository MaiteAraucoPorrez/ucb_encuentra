package org.ucb.encuentra.feature.userstate.domain.usecase

import org.ucb.encuentra.core.domain.repository.SessionRepository

class LogoutUseCase(private val session: SessionRepository) {
    operator fun invoke() = session.clearSession()
}
