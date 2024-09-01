package com.dk.runrite

import android.app.Application
import com.dk.auth.data.di.authDataModule
import com.dk.auth.presentation.di.authViewModelModule
import com.dk.core.data.di.coreDataModule
import com.dk.run.location.di.locationModule
import com.dk.run.presentation.di.runPresentationModule
import com.dk.runrite.di.appModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class RunRiteApp: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

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
                coreDataModule,
                runPresentationModule,
                locationModule
            )
        }
    }
}