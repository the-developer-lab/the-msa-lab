package com.lab.user.application.port.out

import com.lab.user.adapter.out.persistence.mail.MailOutboxEntity
import com.lab.user.domain.mail.MailOutbox

fun interface MailOutBoxRegistrationPort {
    fun registry(mailOutbox: MailOutbox): MailOutboxEntity
}
