package com.lab.user.application.port.out.event

import java.io.Serializable

data class UserRegistrationMailEvent(
    var transactionId: String = "",
    var email: String = "",
    var authenticationCode: String = "",
): Serializable
