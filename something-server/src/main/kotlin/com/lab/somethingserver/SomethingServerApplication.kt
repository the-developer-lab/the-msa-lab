package com.lab.somethingserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication
class SomethingServerApplication

fun main(args: Array<String>) {
    runApplication<SomethingServerApplication>(*args)
}
