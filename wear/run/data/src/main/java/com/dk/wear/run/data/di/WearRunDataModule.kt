package com.dk.wear.run.data.di

import com.dk.wear.run.data.HealthServicesExerciseTracker
import com.dk.wear.run.domain.ExerciseTracker
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val wearRunDataModule = module {
    singleOf(::HealthServicesExerciseTracker).bind<ExerciseTracker>()
}