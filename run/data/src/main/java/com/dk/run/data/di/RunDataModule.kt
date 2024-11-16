package com.dk.run.data.di

import com.dk.core.domain.run.SyncRunScheduler
import com.dk.run.data.CreateRunWorker
import com.dk.run.data.DeleteRunWorker
import com.dk.run.data.FetchRunsWorker
import com.dk.run.data.SyncRunWorkerScheduler
import com.dk.run.data.connectivity.PhoneToWatchConnector
import com.dk.run.domain.WatchConnector
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val runDataModule = module {
    workerOf(::CreateRunWorker)
    workerOf(::FetchRunsWorker)
    workerOf(::DeleteRunWorker)

    singleOf(::SyncRunWorkerScheduler).bind<SyncRunScheduler>()
    singleOf(::PhoneToWatchConnector).bind<WatchConnector>()
}