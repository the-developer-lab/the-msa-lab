package com.lab.user.application.service

import com.lab.user.application.port.`in`.UserRegistrationCheckUseCase
import com.lab.user.application.port.`in`.command.UserRegistrationCheckCommand
import com.lab.user.application.port.out.UserChangeActiveStatusPort
import com.lab.user.application.port.out.UserRegistrationCheckPort
import com.lab.user.global.annotation.UseCase

@UseCase
class UserRegistrationCheckUseCaseImpl(
    private val userRegistrationCheckPort: UserRegistrationCheckPort,
    private val userChangeActiveStatusPort: UserChangeActiveStatusPort,
) : UserRegistrationCheckUseCase {

    override fun checkAuthenticationCode(userRegistrationCheckCommand: UserRegistrationCheckCommand): Boolean {
        val result = userRegistrationCheckPort.checkAuthenticationCode(
            userRegistrationCheckCommand.email,
            userRegistrationCheckCommand.authenticationCode
        )
        if (result) {
            userChangeActiveStatusPort.changeActiveStatus(userRegistrationCheckCommand.email)
        }
        return result
    }
}
