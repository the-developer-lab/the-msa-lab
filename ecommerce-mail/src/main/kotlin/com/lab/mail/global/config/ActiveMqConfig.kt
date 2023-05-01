package com.lab.mail.global.config

import com.lab.mail.global.constant.ARG_DEAD_LETTER_EXCHANGE
import com.lab.mail.global.constant.ARG_DEAD_LETTER_ROUTING_KEY
import com.lab.mail.global.constant.DLQ_FALL_BACK_POST_REGISTRATION_EMAIL
import com.lab.mail.global.constant.DLR_FALL_BACK
import com.lab.mail.global.constant.DLX_POST_REGISTRATION_EMAIL_FAILURE
import com.lab.mail.global.constant.DLX_POST_REGISTRATION_EMAIL_PROCESSED_FAILURE
import com.lab.mail.global.constant.Q_POST_REGISTRATION_EMAIL
import com.lab.mail.global.constant.Q_POST_REGISTRATION_EMAIL_PROCESSED
import com.lab.mail.global.constant.X_POST_REGISTRATION_PROCESSED
import org.springframework.amqp.core.AcknowledgeMode
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.Declarables
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.retry.interceptor.RetryOperationsInterceptor

@EnableRabbit
@Configuration
class ActiveMqConfig(
    private val cachingConnectionFactory: CachingConnectionFactory,
) {

    @Bean(name = ["postRegistrationEmailSchema"])
    fun postRegistrationEmailSchema(): Declarables {
        return Declarables(
            DirectExchange(X_POST_REGISTRATION_PROCESSED),
            Queue(Q_POST_REGISTRATION_EMAIL, true, false, false, queueArguments(DLX_POST_REGISTRATION_EMAIL_FAILURE, DLR_FALL_BACK)),
            Queue(Q_POST_REGISTRATION_EMAIL_PROCESSED, true, false, false, queueArguments(DLX_POST_REGISTRATION_EMAIL_PROCESSED_FAILURE, DLR_FALL_BACK)),
            Binding(
                Q_POST_REGISTRATION_EMAIL_PROCESSED, Binding.DestinationType.QUEUE, X_POST_REGISTRATION_PROCESSED, "", null),
        )
    }

    @Bean(name = ["deadLetterDeclarables"])
    fun deadLetterDeclarables(): Declarables {
        return Declarables(
            DirectExchange(DLX_POST_REGISTRATION_EMAIL_FAILURE),
            Queue(DLQ_FALL_BACK_POST_REGISTRATION_EMAIL),
            Binding(
                DLQ_FALL_BACK_POST_REGISTRATION_EMAIL,
                Binding.DestinationType.QUEUE, DLX_POST_REGISTRATION_EMAIL_FAILURE, DLR_FALL_BACK, null)
        )
    }

    @ConditionalOnMissingBean
    @Bean(name = ["registrationListenerContainerFactory"])
    fun registrationListenerContainerFactory(configurer: SimpleRabbitListenerContainerFactoryConfigurer): SimpleRabbitListenerContainerFactory {
        val factory = SimpleRabbitListenerContainerFactory()
        configurer.configure(factory, cachingConnectionFactory)
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO)
        factory.setAdviceChain(retryInterceptor())
        return factory
    }

    @ConditionalOnMissingBean
    @Bean(name = ["retryInterceptor"])
    fun retryInterceptor(): RetryOperationsInterceptor {
        return RetryInterceptorBuilder.stateless().maxAttempts(3)
            .backOffOptions(2000, 2.0, 100000)
            .recoverer(RejectAndDontRequeueRecoverer())
            .build()
    }

    @ConditionalOnMissingBean
    @Bean(name = ["rabbitTemplate"])
    fun rabbitTemplate(converter: Jackson2JsonMessageConverter): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(cachingConnectionFactory)
        rabbitTemplate.messageConverter = converter
        return rabbitTemplate
    }

    @ConditionalOnMissingBean
    @Bean(name = ["converter"])
    fun converter(): Jackson2JsonMessageConverter {
        return Jackson2JsonMessageConverter()
    }

    private fun queueArguments(
        exchange: String = "",
        routingKey: String = "",
    ): Map<String, String> = mapOf(
        ARG_DEAD_LETTER_EXCHANGE to exchange,
        ARG_DEAD_LETTER_ROUTING_KEY to routingKey
    )
}
