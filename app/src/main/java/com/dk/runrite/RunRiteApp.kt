package com.dk.runrite

import android.app.Application
import android.content.Context
import com.dk.auth.data.di.authDataModule
import com.dk.auth.presentation.di.authViewModelModule
import com.dk.core.data.di.coreDataModule
import com.dk.core.database.di.databaseModule
import com.dk.run.data.di.runDataModule
import com.dk.run.location.di.locationModule
import com.dk.run.network.di.networkModule
import com.dk.run.presentation.di.runPresentationModule
import com.dk.runrite.di.appModule
import com.google.android.play.core.splitcompat.SplitCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
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
            workManagerFactory()
            modules(
                appModule,
                authDataModule,
                authViewModelModule,
                coreDataModule,
                runPresentationModule,
                locationModule,
                databaseModule,
                networkModule,
                runDataModule
            )
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        SplitCompat.install(this)
    }
}