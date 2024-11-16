package com.dk.wear.app.presentation.di

import com.dk.wear.app.presentation.RunRiteApp
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single {
        (androidApplication() as RunRiteApp).applicationScope
    }
}