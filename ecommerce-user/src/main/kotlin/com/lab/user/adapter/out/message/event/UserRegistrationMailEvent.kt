package com.lab.user.adapter.out.message.event

import java.io.Serializable

data class UserRegistrationMailEvent(
    var email: String = "",
    var authenticationCode: String = "",
    var transactionId: String = "",
) : Serializable
