package com.dk.auth.domain

import com.dk.core.domain.util.DataError
import com.dk.core.domain.util.EmptyResult
import javax.xml.crypto.Data

interface AuthRepository {
    suspend fun login(email: String, password: String): EmptyResult<DataError.Network>
    suspend fun register(email: String, password: String): EmptyResult<DataError.Network>
}