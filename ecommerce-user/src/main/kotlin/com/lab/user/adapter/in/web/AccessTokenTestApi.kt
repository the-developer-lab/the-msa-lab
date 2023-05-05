package com.lab.user.adapter.`in`.web

import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/users")
@RestController
class AccessTokenTestApi {

    @GetMapping("/details")
    fun details(@RequestHeader(name = "AUTHORIZATION") username: String): ResponseEntity<Nothing> {
        logger.info { "authorizationHeader -> $username" }
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    companion object {
        private val logger = KotlinLogging.logger {}
    }
}
