package com.dk.run.location

import android.location.Location
import com.dk.core.domain.location.LocationWithAltitude

fun Location.toLocationWithAltitude(): LocationWithAltitude {
    return LocationWithAltitude(
        location = com.dk.core.domain.location.Location(
            lat = latitude,
            long = longitude
        ),
        altitude = altitude
    )
}