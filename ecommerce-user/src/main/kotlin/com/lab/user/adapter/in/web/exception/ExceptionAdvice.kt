package com.lab.user.adapter.`in`.web.exception

import com.lab.user.global.error.DistributedLockTimeOutException
import com.lab.user.global.error.InvalidUserCreateAccessTokenException
import com.lab.user.global.error.InvalidUserSaveException
import com.lab.user.global.error.PasswordMisMatchException
import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionAdvice {

    @ExceptionHandler(InvalidUserCreateAccessTokenException::class)
    fun invalidUserCrateAccessTokenException(e: InvalidUserCreateAccessTokenException): ResponseEntity<ExceptionResponse> {
        logger.error { e.message }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ExceptionResponse(e.message))
    }

    @ExceptionHandler(DistributedLockTimeOutException::class)
    fun distributedLockTimeOutException(e: DistributedLockTimeOutException): ResponseEntity<ExceptionResponse> {
        logger.error { e.message }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionResponse(e.message))
    }

    @ExceptionHandler(InvalidUserSaveException::class)
    fun invalidUserSaveException(e: InvalidUserSaveException): ResponseEntity<ExceptionResponse> {
        logger.warn { e.message }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionResponse(e.message))
    }

    @ExceptionHandler(PasswordMisMatchException::class)
    fun passwordMisMatchException(e: PasswordMisMatchException): ResponseEntity<ExceptionResponse> {
        logger.warn { e.message }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionResponse(e.message))
    }

    @ExceptionHandler(Exception::class)
    fun exception(e: Exception): ResponseEntity<Nothing> {
        logger.error { e.message }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
    }

    companion object {
        private val logger = KotlinLogging.logger { }
    }
}
