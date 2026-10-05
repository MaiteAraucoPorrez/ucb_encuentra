package org.ucb.encuentra

import android.app.Application
import org.ucb.encuentra.di.initKoinAndroid

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoinAndroid(this)
    }
}
