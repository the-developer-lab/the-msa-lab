package com.lab.user.application.port.out

import com.lab.user.domain.user.vo.Email

fun interface UserRegistrationPublishPort {

    fun publish(userId: Long, email: Email, authenticationCode: String) {
        this.publish(userId, email.email, authenticationCode)
    }

    fun publish(userId: Long, email: String, authenticationCode: String)
}
