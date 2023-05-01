package com.lab.user.adapter.out.message.event

import java.io.Serializable

data class UserRegistrationEvent(
    var userId: Long = 0L,
    var email: String = "",
    var authenticationCode: String = "",
) : Serializable
