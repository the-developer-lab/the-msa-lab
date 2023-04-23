package com.lab.user.application.port.out.event

import java.io.Serializable

data class OutboxProcessedEvent(
    var transactionId: String = "",
): Serializable
