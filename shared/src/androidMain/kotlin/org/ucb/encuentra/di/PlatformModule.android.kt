package org.ucb.encuentra.di

import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import org.ucb.encuentra.core.data.local.AppDatabase
import org.ucb.encuentra.core.data.local.buildAppDatabase
import org.ucb.encuentra.core.data.local.getDatabaseBuilder

actual val platformModule: Module = module {
    single<AppDatabase> { buildAppDatabase(getDatabaseBuilder(androidContext())) }
}
