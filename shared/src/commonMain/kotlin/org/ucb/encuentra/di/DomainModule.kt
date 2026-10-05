package org.ucb.encuentra.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.ucb.encuentra.feature.login.domain.usecase.LoginUseCase
import org.ucb.encuentra.feature.matchdetail.domain.usecase.ClaimMatchUseCase
import org.ucb.encuentra.feature.matchdetail.domain.usecase.GetMatchDetailUseCase
import org.ucb.encuentra.feature.matchdetail.domain.usecase.RejectMatchUseCase
import org.ucb.encuentra.feature.matchlist.domain.usecase.FilterMatchesByCategoryUseCase
import org.ucb.encuentra.feature.matchlist.domain.usecase.ObserveMatchesUseCase
import org.ucb.encuentra.feature.signup.domain.usecase.RegisterUseCase
import org.ucb.encuentra.feature.userstate.domain.usecase.GetCurrentUserUseCase
import org.ucb.encuentra.feature.userstate.domain.usecase.GetPendingMatchesCountUseCase
import org.ucb.encuentra.feature.userstate.domain.usecase.LogoutUseCase
import org.ucb.encuentra.userinformation.domain.usecase.FindGithubUserUseCase

val domainModule = module {
    singleOf(::LoginUseCase)
    singleOf(::RegisterUseCase)
    singleOf(::ObserveMatchesUseCase)
    singleOf(::FilterMatchesByCategoryUseCase)
    singleOf(::GetMatchDetailUseCase)
    singleOf(::ClaimMatchUseCase)
    singleOf(::RejectMatchUseCase)
    singleOf(::GetCurrentUserUseCase)
    singleOf(::GetPendingMatchesCountUseCase)
    singleOf(::LogoutUseCase)
    singleOf(::FindGithubUserUseCase)
}
