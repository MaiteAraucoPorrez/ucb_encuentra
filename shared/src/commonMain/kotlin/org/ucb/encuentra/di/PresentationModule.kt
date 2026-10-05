package org.ucb.encuentra.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.ucb.encuentra.feature.login.presentation.viewmodel.LoginViewModel
import org.ucb.encuentra.feature.matchdetail.presentation.viewmodel.MatchDetailViewModel
import org.ucb.encuentra.feature.matchlist.presentation.viewmodel.MatchListViewModel
import org.ucb.encuentra.feature.signup.presentation.viewmodel.RegisterViewModel
import org.ucb.encuentra.feature.userstate.presentation.viewmodel.UserStateViewModel
import org.ucb.encuentra.userinformation.presentation.viewmodel.UserInformationViewModel

val presentationModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::MatchListViewModel)
    viewModelOf(::MatchDetailViewModel)
    viewModelOf(::UserStateViewModel)
    viewModelOf(::UserInformationViewModel)
}
