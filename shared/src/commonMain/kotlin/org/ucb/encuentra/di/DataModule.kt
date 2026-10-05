package org.ucb.encuentra.di

import org.koin.dsl.module
import org.ucb.encuentra.core.data.UserLocalDataSource
import org.ucb.encuentra.core.data.local.AppDatabase
import org.ucb.encuentra.core.data.SessionRepositoryImpl
import org.ucb.encuentra.core.domain.repository.SessionRepository
import org.ucb.encuentra.feature.login.data.repository.AuthRepositoryImpl
import org.ucb.encuentra.feature.login.domain.repository.AuthRepository
import org.ucb.encuentra.feature.matchlist.data.repository.MatchRepositoryImpl
import org.ucb.encuentra.feature.matchlist.domain.repository.MatchRepository
import org.ucb.encuentra.feature.signup.data.repository.RegisterRepositoryImpl
import org.ucb.encuentra.feature.signup.domain.repository.RegisterRepository
import org.ucb.encuentra.userinformation.data.datasource.GithubRemoteDataSource
import org.ucb.encuentra.userinformation.data.repository.GithubRepositoryImpl
import org.ucb.encuentra.userinformation.data.service.GitHubApiService
import org.ucb.encuentra.userinformation.domain.repository.GithubRepository

val dataModule = module {
    // Room (el AppDatabase lo entrega platformModule: Android/iOS)
    single { get<AppDatabase>().userDao() }
    single { get<AppDatabase>().matchDao() }
    single { UserLocalDataSource(get()) }

    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<RegisterRepository> { RegisterRepositoryImpl(get()) }
    single<MatchRepository> { MatchRepositoryImpl(get()) }
    single<SessionRepository> { SessionRepositoryImpl() }
    single<GithubRemoteDataSource> { GitHubApiService() }
    single<GithubRepository> { GithubRepositoryImpl(get()) }
}
