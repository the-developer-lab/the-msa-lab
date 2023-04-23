package com.lab.mail

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
class EcommerceMailApplication

fun main(args: Array<String>) {
    runApplication<EcommerceMailApplication>(*args)
}
