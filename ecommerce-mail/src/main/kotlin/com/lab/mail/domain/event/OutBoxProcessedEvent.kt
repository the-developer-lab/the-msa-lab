package com.lab.mail.domain.event

data class OutBoxProcessedEvent(
    var transactionId: String,
)
