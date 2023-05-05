package com.lab.user.application.scheduler

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

    @Scheduled(cron = "\${user-service.mail.outbox-scheduler.cron}")
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
