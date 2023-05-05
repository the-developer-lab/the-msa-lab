package com.lab.gateway.global.filter

import mu.KotlinLogging
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class LoggingFilter : AbstractGatewayFilterFactory<LoggingFilter.Config>(Config::class.java) {

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange, chain -> filter(exchange, chain, config) }
    }

    private fun filter(
        exchange: ServerWebExchange,
        chain: GatewayFilterChain,
        config: Config,
    ): Mono<Void> {
        logger.info { "Logging Filter baseMessage: $config.baseMessage" }

        val request = exchange.request
        val response = exchange.response
        if (config.preLogger) {
            logger.info { "Logging Filter Start: request id -> ${request.id}" }
        }
        return chain.filter(exchange).then(Mono.fromRunnable {
            if (config.postLogger) {
                logger.info { "Logging Filter End: response status code -> ${response.statusCode}" }
            }
        })
    }

    data class Config(
        var baseMessage: String,
        var preLogger: Boolean,
        var postLogger: Boolean,
    )

    companion object {
        private val logger = KotlinLogging.logger {}
    }
}
