package com.lab.user.adapter.out.scheduler

import com.lab.user.application.port.out.MailOutBoxReadPayloadPort
import com.lab.user.application.port.out.PostRegistrationPublishPort
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class MailOutboxPostScheduler(
    private val mailOutBoxReadPayloadPort: MailOutBoxReadPayloadPort,
    private val postRegistrationPublishPort: PostRegistrationPublishPort,
) {

    @Scheduled(
        fixedDelayString = "\${user-service.mail.outbox-scheduler.fixed-rate}",
        initialDelayString = "\${user-service.mail.outbox-scheduler.initial-delay}",
    )
    @SchedulerLock(name = "processOutboxMessage", lockAtMostFor = "15s", lockAtLeastFor = "15s")
    fun processOutboxMessage() {
        val mailPayloads = mailOutBoxReadPayloadPort.readAllPayloads()
        if (mailPayloads.isNullOrEmpty()) {
            return
        }
        for (mailPayload in mailPayloads) {
            logger.info("publish email")
            postRegistrationPublishPort.publish(mailPayload)
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(MailOutboxPostScheduler::class.java)
    }
}
