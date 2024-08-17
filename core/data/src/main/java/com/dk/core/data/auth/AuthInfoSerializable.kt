package com.dk.core.data.auth

import io.ktor.client.plugins.auth.providers.RefreshTokensParams
import kotlinx.serialization.Serializable

@Serializable
data class AuthInfoSerializable(
    val accessToken: String,
    val refreshToken: String,
    val userId: String
)
