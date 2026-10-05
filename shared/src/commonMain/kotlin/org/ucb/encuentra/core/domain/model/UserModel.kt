package org.ucb.encuentra.core.domain.model

import org.ucb.encuentra.core.domain.vo.Email

data class UserModel(
    val id: String,
    val fullName: String,
    val email: Email
)
