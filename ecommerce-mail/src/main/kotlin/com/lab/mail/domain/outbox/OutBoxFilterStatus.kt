package com.lab.mail.domain.outbox

enum class OutBoxFilterStatus {
    READY,
    FAILED,
    PROCESSED;

    fun isProcessed(): Boolean = this == PROCESSED
}