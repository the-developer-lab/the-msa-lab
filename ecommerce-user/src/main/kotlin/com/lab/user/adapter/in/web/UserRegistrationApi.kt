package com.lab.user.adapter.`in`.web

import com.lab.user.adapter.`in`.web.dto.UserRegistrationRequestDto
import com.lab.user.application.port.`in`.UserRegistrationUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RequestMapping("/users")
@RestController
class UserRegistrationApi(
    private val userRegistrationUseCase: UserRegistrationUseCase,
) {

    @PostMapping("/registration")
    fun registry(@RequestBody userRegistrationDto: UserRegistrationRequestDto): ResponseEntity<URI> {
        val id = userRegistrationUseCase.registry(userRegistrationDto.toCommand())
        return ResponseEntity.created(URI.create("users/$id")).build()
    }
}
