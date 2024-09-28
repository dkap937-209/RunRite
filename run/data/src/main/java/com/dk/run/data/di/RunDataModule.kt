package com.dk.run.data.di

import com.dk.run.data.CreateRunWorker
import com.dk.run.data.DeleteRunWorker
import com.dk.run.data.FetchRunsWorker
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.dsl.module

val runDataModule = module {
    workerOf(::CreateRunWorker)
    workerOf(::FetchRunsWorker)
    workerOf(::DeleteRunWorker)
}