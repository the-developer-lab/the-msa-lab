package com.lab.mail.domain.event

data class UserRegistrationEvent(
    var email: String = "",
    var authenticationCode: String = "",
    var transactionId: String = "",
)
