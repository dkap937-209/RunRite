package com.dk.run.presentation.di

import com.dk.run.domain.RunningTracker
import com.dk.run.presentation.active_run.ActiveRunViewModel
import com.dk.run.presentation.run_overview.RunOverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import kotlin.math.sin

val runPresentationModule = module {

    singleOf(::RunningTracker)
    single {
        get<RunningTracker>().elapsedTime
    }

    viewModelOf(::RunOverviewViewModel)
    viewModelOf(::ActiveRunViewModel)
}