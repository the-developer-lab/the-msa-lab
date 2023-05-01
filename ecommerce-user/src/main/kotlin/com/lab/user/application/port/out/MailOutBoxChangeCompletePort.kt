package com.lab.user.application.port.out

fun interface MailOutBoxChangeCompletePort {
    fun completeMailOutBox(transactionId: String)
}
