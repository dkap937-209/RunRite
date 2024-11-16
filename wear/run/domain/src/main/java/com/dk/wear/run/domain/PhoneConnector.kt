package com.dk.wear.run.domain

import com.dk.core.connectivity.domain.DeviceNode
import kotlinx.coroutines.flow.StateFlow

interface PhoneConnector {
    val connectedNodes: StateFlow<DeviceNode?>
}