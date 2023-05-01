package com.lab.user.adapter.`in`.web.dto

import com.lab.user.application.port.`in`.command.UserRegistrationCheckCommand
import jakarta.validation.constraints.NotBlank

class UserRegistrationAuthorizationRequest(
    @get:NotBlank
    var email: String,

    @get:NotBlank
    var authenticationCode: String,
) {

    fun toCommand(): UserRegistrationCheckCommand = UserRegistrationCheckCommand(
        email = email,
        authenticationCode = authenticationCode
    )
}
