package com.dk.runrite

import android.app.Application
import com.dk.auth.data.di.authDataModule
import com.dk.auth.presentation.di.authViewModelModule
import com.dk.core.data.di.coreDataModule
import com.dk.runrite.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class RunRiteApp: Application() {

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin{
            androidLogger()
            androidContext(this@RunRiteApp)
            modules(
                appModule,
                authDataModule,
                authViewModelModule,
                coreDataModule
            )
        }
    }
}