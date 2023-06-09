package com.lab.user.adapter.`in`.web

import com.lab.user.adapter.`in`.web.dto.UserRegistrationRequest
import com.lab.user.application.port.`in`.UserRegistrationRegistryUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RequestMapping("/users")
@RestController
class UserRegistrationApi(
    private val userRegistrationRegistryUseCase: UserRegistrationRegistryUseCase,
) {

    @PostMapping("/registration")
    fun registry(@RequestBody @Validated userRegistration: UserRegistrationRequest): ResponseEntity<URI> {
        val id = userRegistrationRegistryUseCase.registry(userRegistration.toCommand())
        return ResponseEntity.status(HttpStatus.CREATED).body(URI.create("users/$id"))
    }
}
