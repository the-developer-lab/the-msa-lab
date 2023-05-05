package com.lab.user.adapter.`in`.web.dto

import com.lab.user.application.port.`in`.command.UserLoginCommand
import jakarta.validation.constraints.NotBlank

data class UserLoginRequest(
    @get:NotBlank var username: String,
    @get:NotBlank var password: String,
) {
    fun toCommand(): UserLoginCommand =
        UserLoginCommand(username, password)
}
