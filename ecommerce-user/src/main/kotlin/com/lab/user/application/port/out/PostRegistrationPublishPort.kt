package com.lab.user.application.port.out

import com.lab.user.domain.mail.vo.Payload

fun interface PostRegistrationPublishPort {

    fun publish(payload: Payload) {
        publish(payload.email, payload.authenticationCode, payload.transactionId)
    }

    fun publish(email: String, authenticationCode: String, transactionId: String)
}
