package org.ucb.encuentra.di

import org.koin.core.module.Module

fun sharedModules(): List<Module> = listOf(
    platformModule,
    dataModule,
    domainModule,
    presentationModule
)
