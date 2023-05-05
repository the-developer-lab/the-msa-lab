package com.lab.gateway.global.filter

import com.lab.gateway.global.utils.JwtUtils
import mu.KotlinLogging
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class JwtFilter(
    private val jwtUtils: JwtUtils,
    private val redisTemplate: StringRedisTemplate,
) : AbstractGatewayFilterFactory<JwtFilter.Config>(Config::class.java) {

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange, chain -> filter(exchange, chain, config) }
    }

    private fun filter(
        exchange: ServerWebExchange,
        chain: GatewayFilterChain,
        config: Config,
    ): Mono<Void> {
        logger.info { "Jwt Filter baseMessage: $config.baseMessage" }

        val excludePaths = config.excludePaths
        val request = exchange.request
        val requestUriRawPath = requestUriRawPath(exchange, request)
        if (!excludePaths.contains(requestUriRawPath)) {
            logger.info { "verify access token and change AuthorizationHeader" }
            val accessToken = accessToken(request.headers)
            verifyBlackListAccessToken(accessToken)
            changeAuthorizationHeader(exchange, jwtUtils.parseToUsername(accessToken))
        }
        return chain.filter(exchange).then(Mono.fromRunnable {
        })
    }

    private fun requestUriRawPath(exchange: ServerWebExchange, request: ServerHttpRequest): String {
        ServerWebExchangeUtils.addOriginalRequestUrl(exchange, request.uri)
        return request.uri.rawPath
    }

    private fun accessToken(headers: HttpHeaders): String {
        headers.getFirst(AUTHORIZATION)?.let {
            if (it.startsWith(BEARER)) {
                return it.substring(BEARER_LENGTH)
            }
        }
        throw RuntimeException("jwt token not use")
    }

    private fun verifyBlackListAccessToken(accessToken: String) {
        if (redisTemplate.hasKey(accessToken)) {
            throw RuntimeException("jwt token is black list")
        }
    }

    private fun changeAuthorizationHeader(exchange: ServerWebExchange, username: String) {
        exchange.mutate()
            .request { builder -> builder.header(AUTHORIZATION, username) }
            .build()
    }

    data class Config(
        var baseMessage: String,
        var excludePaths: List<String>,
    )

    companion object {
        private val logger = KotlinLogging.logger {}
        private const val BEARER = "Bearer "
        private const val BEARER_LENGTH = BEARER.length
    }
}
