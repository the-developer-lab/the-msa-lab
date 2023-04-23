package com.lab.mail.domain.template

import com.lab.mail.domain.outbox.OutBoxFilterEntity
import com.lab.mail.domain.outbox.OutBoxFilterRepository
import com.lab.mail.domain.outbox.OutBoxFilterStatus
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MailSenderTemplate(
    private val mailSender: JavaMailSender,
    private val outBoxFilterRepository: OutBoxFilterRepository,
    @Value("\${mail-service.mail.auth-url-template}")
    private val authorizeUrlTemplate: String,
) {

    @Transactional
    fun sendAuthorizeEmail(transactionId: String, email: String, authenticationCode: String) {
        val outBoxFilterEntity = outBoxFilterRepository.findByTransactionId(transactionId)
        if(outBoxFilterEntity == null) {
            val authorizeUrl = authorizeUrlTemplate.format(email, authenticationCode)
            mailSender.send(generateAuthenticationMailTemplateForm(email, authorizeUrl))
            outBoxFilterRepository.saveAndFlush(
                OutBoxFilterEntity(
                    transactionId = transactionId,
                    outboxFilterStatus = OutBoxFilterStatus.PROCESSED
                )
            )
            return
        }
        outBoxFilterRepository.findByTransactionId(transactionId)?.let {
            if (it.isProcessed()) return@let
            outBoxFilterRepository.saveAndFlush(it.updateProcessedStatus())
        }
    }

    private fun generateAuthenticationMailTemplateForm(
        email: String,
        authenticationUrl: String,
    ): SimpleMailMessage = SimpleMailMessage().apply {
        from = "noreply@guacamole.com"
        setTo(email)
        subject = "과카몰리 - 인증 메일 발송"
        text = """<p> 링크 : <a href="$authenticationUrl">인증 하기</a> </p>"""
    }
}
