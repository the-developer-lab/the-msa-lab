package com.lab.user.adapter.`in`.web

import com.lab.user.adapter.`in`.web.dto.UserLoginRequest
import com.lab.user.adapter.`in`.web.dto.UserLoginResponse
import com.lab.user.application.port.`in`.UserLoginUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController("/users")
class UserLoginApi(
    private val userLoginUseCase: UserLoginUseCase,
) {

    @PostMapping("/login")
    fun login(@RequestBody @Validated userLoginRequest: UserLoginRequest): ResponseEntity<UserLoginResponse> {
        val accessToken = userLoginUseCase.login(userLoginRequest.toCommand())
        return ResponseEntity.status(HttpStatus.OK).body(UserLoginResponse(accessToken))
    }
}
