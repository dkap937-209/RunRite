package com.dk.core.data.di

import com.dk.core.data.auth.EncryptedSessionStorage
import com.dk.core.data.network.HttpClientFactory
import com.dk.core.data.run.OfflineFirstRunRepository
import com.dk.core.domain.SessionStorage
import com.dk.core.domain.run.RunRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory(get()).build()
    }
    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()
    singleOf(::OfflineFirstRunRepository).bind<RunRepository>()
}