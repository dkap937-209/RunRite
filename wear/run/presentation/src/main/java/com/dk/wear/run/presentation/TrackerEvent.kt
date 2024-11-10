package com.dk.wear.run.presentation

sealed interface TrackerEvent {
    data object RunFinished: TrackerEvent
}