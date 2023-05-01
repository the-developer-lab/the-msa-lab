package com.lab.user.application.port.out

import com.lab.user.domain.mail.vo.Payload

fun interface MailOutBoxReadPayloadPort {
    fun readAllPayloads(): List<Payload>
}
