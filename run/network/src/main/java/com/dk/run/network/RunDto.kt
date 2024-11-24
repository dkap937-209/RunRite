package com.dk.run.network

import kotlinx.serialization.Serializable

@Serializable
data class RunDto(
    val id: String,
    val dateTimeUtc: String,
    val durationMillis: Long,
    val distanceMeters: Int,
    val lat: Double,
    val long: Double,
    val mapPictureUrl: String?,
    val avgSpeedKmh: Double,
    val maxSpeedKmh: Double,
    val totalElevationMeters: Int,
    val avgHeartRate: Int?,
    val maxHeartRate: Int?
)
