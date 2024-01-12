package com.mcmouse88.hrenapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                koinModule
            )
        }
    }
}