package com.dk.wear.run.data

import com.dk.core.connectivity.domain.DeviceNode
import com.dk.core.connectivity.domain.DeviceType
import com.dk.core.connectivity.domain.NodeDiscovery
import com.dk.wear.run.domain.PhoneConnector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class WatchToPhoneConnector(
    nodeDiscovery: NodeDiscovery,
    applicationScope: CoroutineScope
): PhoneConnector {

    private val _connectedNode = MutableStateFlow<DeviceNode?>(null)

    override val connectedNodes: StateFlow<DeviceNode?>
        get() = _connectedNode.asStateFlow()

    val messagingAction = nodeDiscovery
        .observeConnectedDevices(DeviceType.WATCH)
        .onEach { connectedNodes ->
            val node = connectedNodes.firstOrNull()
            if(node != null && node.isNearby) {
                _connectedNode.value = node
            }
        }.launchIn(applicationScope)
}