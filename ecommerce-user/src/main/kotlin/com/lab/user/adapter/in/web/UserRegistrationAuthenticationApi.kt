package com.lab.user.adapter.`in`.web

import com.lab.user.adapter.`in`.web.dto.UserRegistrationAuthorizationRequest
import com.lab.user.application.port.`in`.UserRegistrationCheckUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/users")
@RestController
class UserRegistrationAuthenticationApi(
    private val userRegistrationCheckUseCase: UserRegistrationCheckUseCase,
) {

    @PostMapping("/authentication")
    fun checkAuthenticationCode(@RequestBody @Validated userRegistrationAuthenticationRequest: UserRegistrationAuthorizationRequest): ResponseEntity<Boolean> {
        val result =
            userRegistrationCheckUseCase.checkAuthenticationCode(userRegistrationAuthenticationRequest.toCommand())
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }
}
