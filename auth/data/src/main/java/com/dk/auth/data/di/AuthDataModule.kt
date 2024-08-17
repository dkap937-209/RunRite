package com.dk.auth.data.di

import com.dk.auth.data.AuthRepositoryImpl
import com.dk.auth.data.EmailPatternValidator
import com.dk.auth.domain.AuthRepository
import com.dk.auth.domain.PatternValidator
import com.dk.auth.domain.UserDataValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authDataModule = module {
    single<PatternValidator> {
        EmailPatternValidator
    }
    singleOf(::UserDataValidator)
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
}