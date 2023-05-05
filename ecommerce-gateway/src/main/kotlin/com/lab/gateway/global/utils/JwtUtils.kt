package com.lab.gateway.global.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.interfaces.DecodedJWT
import com.lab.gateway.global.extension.beforeNow
import org.springframework.stereotype.Component

@Component
class JwtUtils {

    fun parseToUsername(token: String): String {
        val decodedJWT = JWT.decode(token)
        val expiresAt = decodedJWT.expiresAt
        if (expiresAt.beforeNow()) {
            throw RuntimeException()
        }
        return parseToClaim(decodedJWT, "username")
    }

    private fun parseToClaim(decodedJWT: DecodedJWT, claim: String): String =
        decodedJWT.claims[claim]
            ?.asString()
            ?: throw RuntimeException()
}
