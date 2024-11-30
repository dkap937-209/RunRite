package com.dk.wear.run.data.di

import com.dk.wear.run.data.HealthServicesExerciseTracker
import com.dk.wear.run.data.WatchToPhoneConnector
import com.dk.wear.run.domain.ExerciseTracker
import com.dk.wear.run.domain.PhoneConnector
import com.dk.wear.run.domain.RunningTracker
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val wearRunDataModule = module {
    singleOf(::HealthServicesExerciseTracker).bind<ExerciseTracker>()
    singleOf(::WatchToPhoneConnector).bind<PhoneConnector>()
    singleOf(::RunningTracker)
    single {
        get<RunningTracker>().elapsedTime
    }
}