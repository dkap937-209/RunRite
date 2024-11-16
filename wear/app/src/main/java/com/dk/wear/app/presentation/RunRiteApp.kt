package com.dk.wear.app.presentation

import android.app.Application
import com.dk.wear.run.data.di.wearRunDataModule
import com.dk.wear.run.presentation.di.wearRunPresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RunRiteApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@RunRiteApp)
            modules(
                wearRunPresentationModule,
                wearRunDataModule
            )
        }
    }
}