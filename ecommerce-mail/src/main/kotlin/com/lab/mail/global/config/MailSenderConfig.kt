package com.lab.mail.global.config

import com.lab.mail.global.config.properties.MailSenderProperty
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.Properties

@Configuration
class MailSenderConfig {

    @ConditionalOnMissingBean
    @Bean(name = ["javaMailSender"])
    fun javaMailSender(mailSenderProperty: MailSenderProperty): JavaMailSender {
        val mailSender = JavaMailSenderImpl()
        mailSender.host = mailSenderProperty.host
        mailSender.port = mailSenderProperty.port
        mailSender.username = mailSenderProperty.username
        mailSender.password = mailSenderProperty.password

        val props: Properties = mailSender.javaMailProperties
        props["mail.transport.protocol"] = "smtp"
        props["mail.smtp.auth"] = true
        props["mail.smtp.starttls.enable"] = true
        props["mail.debug"] = "true"
        return mailSender
    }
}
