package com.dk.auth.domain

import com.dk.core.domain.util.DataError
import com.dk.core.domain.util.EmptyResult

interface AuthRepository {
    suspend fun register(email: String, password: String): EmptyResult<DataError.Network>
}