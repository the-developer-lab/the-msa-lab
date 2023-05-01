package com.lab.user.adapter.`in`.message

import com.lab.user.adapter.out.MailOutboxRegistrationAdapter
import com.lab.user.adapter.out.message.event.UserRegistrationEvent
import com.lab.user.domain.mail.MailOutbox
import com.lab.user.domain.mail.vo.MailOutboxStatus
import com.lab.user.domain.mail.vo.MailOutboxType
import com.lab.user.domain.mail.vo.Payload
import com.lab.user.domain.mail.vo.PublishAt
import com.lab.user.domain.mail.vo.TransactionAt
import com.lab.user.global.constant.Q_USER_REGISTRATION
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserRegistrationSubscriber(
    private val mailOutboxRegistryAdapter: MailOutboxRegistrationAdapter,
) {

    @Async
    @RabbitListener(queues = [Q_USER_REGISTRATION], containerFactory = "registrationListenerContainerFactory")
    fun publish(userRegistrationEvent: UserRegistrationEvent) {
        mailOutboxRegistryAdapter.registry(
            toDomainModel(
                userRegistrationEvent.email,
                userRegistrationEvent.authenticationCode
            )
        )
    }

    private fun toDomainModel(
        email: String,
        authenticationCode: String,
        transactionId: String = UUID.randomUUID().toString(),
    ): MailOutbox =
        MailOutbox(
            mailOutboxStatus = MailOutboxStatus.STARTED,
            payload = toPayload(email, authenticationCode, transactionId),
            mailOutboxType = MailOutboxType.AUTHORIZATION,
            transactionAt = TransactionAt(),
            publishAt = PublishAt(),
            transactionId = transactionId
        )

    private fun toPayload(
        email: String,
        authenticationCode: String,
        transactionId: String = UUID.randomUUID().toString(),
    ) = Payload(
        email = email,
        authenticationCode = authenticationCode,
        transactionId = transactionId
    )
}
