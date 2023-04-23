package com.lab.user.adapter.out.persistence

import com.lab.user.application.port.out.UserRegistrationAuthenticationPort
import com.lab.user.application.port.out.UserRegistrationAuthorizePort
import com.lab.user.global.annotation.Adapter
import org.springframework.data.redis.core.StringRedisTemplate
import java.util.UUID

@Adapter
class UserRegistrationAuthenticationAdapter(
    private val redisTemplate: StringRedisTemplate,
) : UserRegistrationAuthenticationPort,
    UserRegistrationAuthorizePort {

    override fun registryAuthenticationCode(email: String): String {
        val authenticationCode = UUID.randomUUID().toString().substring(6)
        redisTemplate.opsForValue().set(email, authenticationCode)
        return authenticationCode
    }

    override fun checkAuthenticationCode(email: String, authenticationCode: String): Boolean {
        val userAuthenticationCode = redisTemplate.opsForValue().get(email)
        return userAuthenticationCode.equals(authenticationCode)
    }
}
