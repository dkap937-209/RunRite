package com.dk.core.database.mappers

import com.dk.core.database.entity.RunEntity
import com.dk.core.domain.location.Location
import com.dk.core.domain.run.Run
import org.bson.types.ObjectId
import java.time.Instant
import java.time.ZoneId
import kotlin.math.max
import kotlin.time.Duration.Companion.milliseconds

fun RunEntity.toRun(): Run {
    return Run(
        id = id,
        duration = durationMillis.milliseconds,
        dateTimeUtc = Instant.parse(dateTimeUtc)
            .atZone(ZoneId.of("UTC")),
        distanceMeters = distanceMeters,
        location = Location(
            lat = latitude,
            long = longitude
        ),
        maxSpeedKmh = maxSpeedKmh,
        totalElevationMeters = totalElevationMeters,
        mapPictureUrl = mapPictureUrl,
        avgHeartRate = avgHeartRate,
        maxHeartRate = maxHeartRate
    )
}

fun Run.toRunEntity(): RunEntity {
    return RunEntity(
        id = id ?: ObjectId().toHexString(),
        durationMillis = duration.inWholeMilliseconds,
        maxSpeedKmh = maxSpeedKmh,
        distanceMeters = distanceMeters,
        dateTimeUtc = dateTimeUtc.toInstant().toString(),
        latitude = location.lat,
        longitude = location.long,
        mapPictureUrl = mapPictureUrl,
        avgSpeedKmh = avgSpeedKmh,
        totalElevationMeters = totalElevationMeters,
        avgHeartRate = avgHeartRate,
        maxHeartRate = maxHeartRate
    )
}