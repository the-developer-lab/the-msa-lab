package com.lab.user.application.port.`in`.command

data class UserRegistrationAuthorizeCommand(
    val email: String,
    val authenticationCode: String
)
