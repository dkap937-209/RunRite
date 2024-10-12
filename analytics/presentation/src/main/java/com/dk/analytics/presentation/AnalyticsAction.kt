package com.dk.analytics.presentation

sealed interface AnalyticsAction {
    data object OnBackClick: AnalyticsAction
}