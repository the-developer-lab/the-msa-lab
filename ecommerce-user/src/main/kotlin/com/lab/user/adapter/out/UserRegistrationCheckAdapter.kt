package com.lab.user.adapter.out

import com.lab.user.application.port.out.UserRegistrationCheckPort
import com.lab.user.global.annotation.Adapter
import org.springframework.data.redis.core.StringRedisTemplate

@Adapter
class UserRegistrationCheckAdapter(
    private val redisTemplate: StringRedisTemplate,
) : UserRegistrationCheckPort {

    override fun checkAuthenticationCode(email: String, authenticationCode: String): Boolean {
        val userAuthenticationCode = redisTemplate.opsForValue().get(email)
        return userAuthenticationCode.equals(authenticationCode)
    }
}
