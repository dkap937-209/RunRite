package com.dk.run.domain

import com.dk.core.domain.location.LocationTimestamp
import kotlin.math.roundToInt

object LocationDataCalculator {

    fun getTotalDistanceMeters(locations: List<List<LocationTimestamp>>): Int{
        return locations.sumOf { timeStampsPerLine ->
            timeStampsPerLine.zipWithNext{ location1, location2 ->
                location1.location.location.distanceTo(location2.location.location)
            }.sum().roundToInt()
        }
    }
}