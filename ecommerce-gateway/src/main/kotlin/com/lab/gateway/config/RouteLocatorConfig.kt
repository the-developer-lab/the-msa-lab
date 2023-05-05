package com.lab.gateway.config

import org.springframework.cloud.gateway.route.Route
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.Buildable
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec
import org.springframework.cloud.gateway.route.builder.PredicateSpec
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

// @Configuration
class RouteLocatorConfig {

    // @Bean(name = ["customRouteLocator"])
    fun customRouteLocator(builder: RouteLocatorBuilder): RouteLocator =
        builder.routes()
            .route(::routeBuildable)
            .build()

    private fun routeBuildable(predicateSpec: PredicateSpec): Buildable<Route> =
        predicateSpec.path("/ecommerce-user/**")
            .filters(::addRequestAndResponseHeader)
            .uri("lb://ECOMMERCE-USER")

    private fun addRequestAndResponseHeader(gatewayFilterSpec: GatewayFilterSpec): GatewayFilterSpec =
        gatewayFilterSpec.addRequestHeader("ecommerce-request", "ecommerce-requset-header")
            .addResponseHeader("ecommerce-response", "ecommerce-response-header")
}