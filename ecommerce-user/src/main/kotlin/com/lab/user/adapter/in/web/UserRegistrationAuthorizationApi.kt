package com.lab.user.adapter.`in`.web

import com.lab.user.adapter.`in`.web.dto.UserRegistrationAuthorizationRequestDto
import com.lab.user.application.port.`in`.UserRegistrationAuthorizeUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/users")
@RestController
class UserRegistrationAuthorizationApi(
    private val userRegistrationAuthorizeUseCase: UserRegistrationAuthorizeUseCase
) {

    @PostMapping("/registration/authorization")
    fun checkAuthenticationCode(@RequestBody userRegistrationAuthorizationRequestDto: UserRegistrationAuthorizationRequestDto): Boolean {
        return userRegistrationAuthorizeUseCase.checkAuthenticationCode(userRegistrationAuthorizationRequestDto.toCommand())
    }
}
