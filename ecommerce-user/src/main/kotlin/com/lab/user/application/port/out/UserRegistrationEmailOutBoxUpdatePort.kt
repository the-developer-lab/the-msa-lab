package com.lab.user.application.port.out

fun interface UserRegistrationEmailOutBoxUpdatePort {
    fun completeEmailOutBox(transactionId: String)
}
