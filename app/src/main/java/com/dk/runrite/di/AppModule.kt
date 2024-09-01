package com.dk.runrite.di

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.dk.core.data.auth.EncryptedSessionStorage
import com.dk.runrite.MainViewModel
import com.dk.runrite.RunRiteApp
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<SharedPreferences> {
        EncryptedSharedPreferences(
            androidApplication(),
            "auth_pref",
            MasterKey(androidApplication()),
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    single<CoroutineScope> {
        (androidApplication() as RunRiteApp).applicationScope
    }

    viewModelOf(::MainViewModel)
}