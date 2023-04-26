package com.lab.mail.global.aop

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.springframework.core.annotation.Order
import org.springframework.mail.MailException
import org.springframework.orm.jpa.JpaSystemException
import org.springframework.stereotype.Component

@Order(1)
@Aspect
@Component
class MailSenderExceptionAspect {

    @Pointcut(value = "execution(* com.lab.mail.domain.template.MailSenderTemplate.sendAuthorizeEmail(..))\n")
    fun mailSenderPointCut() {
    }

    @Around("mailSenderPointCut()")
    fun process(joinPoint: ProceedingJoinPoint): Any? {
        return try {
            joinPoint.proceed()
        } catch (e: MailException) {
            throw RuntimeException("send mail failed ${e.localizedMessage}")
        } catch (e: JpaSystemException) {
            throw RuntimeException("outbox filter failed ${e.localizedMessage}")
        } catch (e: Exception) {
            throw RuntimeException("some thing data wrong ${e.localizedMessage}")
        }
    }
}
