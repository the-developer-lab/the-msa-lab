package com.lab.user.adapter.out

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.lab.user.application.port.out.AuthorizationTokenCreatePort
import com.lab.user.global.annotation.Adapter
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import java.util.UUID

@Adapter
class AuthorizationTokenCreatePortAdapter : AuthorizationTokenCreatePort {

    override fun createAuthorizationToken(username: String): String =
        JWT.create()
            .withIssuer("woozi")
            .withSubject("MSA Ecommerce")
            .withClaim("username", username)
            .withClaim("role", "member")
            .withIssuedAt(Date())
            .withExpiresAt(expireAt())
            .withJWTId(UUID.randomUUID().toString())
            .withNotBefore(notBefore())
            .sign(Algorithm.HMAC256("woozi"))

    private fun expireAt(): Date = Date.from(
        LocalDate.now()
            .atStartOfDay()
            .atZone(ZoneId.of("Asia/Seoul"))
            .plusDays(1)
            .toInstant()
    )

    private fun notBefore(): Date = Date.from(
        LocalDate.now()
            .atStartOfDay()
            .atZone(ZoneId.of("Asia/Seoul"))
            .plusSeconds(1000L)
            .toInstant()
    )
}
