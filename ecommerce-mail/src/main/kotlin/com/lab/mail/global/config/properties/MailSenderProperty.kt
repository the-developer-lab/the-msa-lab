package com.lab.mail.global.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.mail")
class MailSenderProperty(
    val host: String = "localhost",
    val port: Int = 587,
    val username: String = "username",
    val password: String = "password",
)
