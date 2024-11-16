package com.dk.wear.app.presentation

import android.app.Application
import com.dk.core.connectivity.data.coreConnectivityDataModule
import com.dk.wear.app.presentation.di.appModule
import com.dk.wear.run.data.di.wearRunDataModule
import com.dk.wear.run.presentation.di.wearRunPresentationModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class RunRiteApp: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@RunRiteApp)
            modules(
                appModule,
                wearRunPresentationModule,
                wearRunDataModule,
                coreConnectivityDataModule,
            )
        }
    }
}