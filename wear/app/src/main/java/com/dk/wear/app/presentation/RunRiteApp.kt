package com.dk.wear.app.presentation

import android.app.Application
import com.dk.wear.run.presentation.di.runPresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin

class RunRiteApp: Application() {

    override fun onCreate() {
        startKoin{
            androidLogger()
            androidContext(this@RunRiteApp)
            modules(
                runPresentationModule
            )
        }
    }
}