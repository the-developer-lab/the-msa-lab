package com.lab.user.adapter.`in`.web.dto

import com.lab.user.application.port.`in`.command.UserRegistrationAuthorizeCommand
import jakarta.validation.constraints.NotBlank

class UserRegistrationAuthorizationRequestDto(
    @get:NotBlank
    var email: String,

    @get:NotBlank
    var authenticationCode: String,
) {

    fun toCommand(): UserRegistrationAuthorizeCommand = UserRegistrationAuthorizeCommand(
        email = email,
        authenticationCode = authenticationCode
    )
}
