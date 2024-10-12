package com.dk.analytics.data.di

import com.dk.analytics.data.RoomAnalyticsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val analyticsModule = module {
    singleOf(::RoomAnalyticsRepository)
}