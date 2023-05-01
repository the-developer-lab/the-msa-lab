package com.lab.user.application.port.`in`.command

data class UserRegistrationCheckCommand(
    val email: String,
    val authenticationCode: String
)
