package com.dk.run.network.di

import com.dk.core.domain.run.RemoteRunDataSource
import com.dk.run.network.KtorRemoteDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {
    singleOf(::KtorRemoteDataSource).bind<RemoteRunDataSource>()
}