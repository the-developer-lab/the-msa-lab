package com.lab.user.application.port.out

import com.lab.user.domain.mail.MailOutbox

fun interface UserRegistrationEmailOutBoxRegistryPort {
    fun registryEmailOutBox(mailOutbox: MailOutbox): String
}
