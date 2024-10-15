package com.dk.analytics.data.di

import com.dk.analytics.data.RoomAnalyticsRepository
import com.dk.analytics.domain.AnalyticsRepository
import com.dk.core.database.RunDatabase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val analyticsModule = module {
    singleOf(::RoomAnalyticsRepository).bind<AnalyticsRepository>()
    single {
        get<RunDatabase>().analyticsDao
    }
}