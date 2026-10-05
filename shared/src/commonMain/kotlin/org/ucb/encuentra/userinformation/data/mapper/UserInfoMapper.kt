package org.ucb.encuentra.userinformation.data.mapper

import org.ucb.encuentra.userinformation.data.dto.UserInfoDto
import org.ucb.encuentra.userinformation.domain.model.UserInfoModel

fun UserInfoDto.toDomain(alias: String): UserInfoModel = UserInfoModel(
    email = email ?: "",
    company = company ?: "",
    avatarUrl = avatarUrl ?: "",
    alias = alias
)
