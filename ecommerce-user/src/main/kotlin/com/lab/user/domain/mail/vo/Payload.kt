package com.lab.user.domain.mail.vo

data class Payload(
    val email: String,
    val authenticationCode: String,
    val transactionId: String,
)
