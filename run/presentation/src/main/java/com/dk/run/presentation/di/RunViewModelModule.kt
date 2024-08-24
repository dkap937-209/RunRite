package com.dk.run.presentation.di

import com.dk.run.presentation.run_overview.RunOverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val runModelModule = module {
    viewModelOf(::RunOverviewViewModel)
}