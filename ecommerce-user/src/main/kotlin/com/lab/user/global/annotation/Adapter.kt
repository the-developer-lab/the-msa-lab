package com.lab.user.global.annotation

import org.springframework.stereotype.Component

@Target(AnnotationTarget.TYPE, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Component
annotation class Adapter
