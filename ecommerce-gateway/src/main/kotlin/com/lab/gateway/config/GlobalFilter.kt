package com.lab.gateway.config

import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class GlobalFilter : AbstractGatewayFilterFactory<GlobalFilter.Config>(Config::class.java) {

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter(::customFilterLogic)
    }

    private fun customFilterLogic(
        exchange: ServerWebExchange,
        chain: GatewayFilterChain,
    ): Mono<Void> {
        val request = exchange.request
        val response = exchange.response
        println("Global Filter $request.id" )
        return chain.filter(exchange).then(Mono.fromRunnable { println("Global Filter $response.statusCode") })
    }

    data class Config(
        var baseMessage: String,
        var preLogger: Boolean,
        var postLogger: Boolean
    )
}