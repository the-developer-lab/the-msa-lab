package com.lab.user.global.aspect

import com.lab.user.global.annotation.DistributedLock
import com.lab.user.global.error.DistributedLockTimeOutException
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.springframework.context.expression.MethodBasedEvaluationContext
import org.springframework.core.DefaultParameterNameDiscoverer
import org.springframework.core.ParameterNameDiscoverer
import org.springframework.core.annotation.Order
import org.springframework.expression.ExpressionParser
import org.springframework.expression.spel.standard.SpelExpressionParser
import org.springframework.stereotype.Component

@Order(1)
@Aspect
@Component
class DistributedLockAspect(
    private val redissonClient: RedissonClient,
) {
    private val expressionParser: ExpressionParser = SpelExpressionParser()
    private val nameDiscoverer: ParameterNameDiscoverer = DefaultParameterNameDiscoverer()

    @Pointcut(value = "@annotation(com.lab.user.global.annotation.DistributedLock)")
    fun distributedLockPointCut() {
    }

    @Around("distributedLockPointCut()")
    fun process(joinPoint: ProceedingJoinPoint): Any? {
        val targetMethod = (joinPoint.signature as MethodSignature).method
        val args = joinPoint.args
        if (args.isEmpty()) {
            throw RuntimeException("DistributedLockAspect.process(), arguments is empty")
        }

        val evaluationContext = MethodBasedEvaluationContext(args[0], targetMethod, args, nameDiscoverer)
        val distributedLockAnnotation = targetMethod.getAnnotation(DistributedLock::class.java)
        val distributedLockKey = expressionParser.parseExpression(distributedLockAnnotation.key)
            .getValue(evaluationContext)
            .toString()

        val distributedLockName =
            "${distributedLockAnnotation.prefix}$distributedLockKey${distributedLockAnnotation.suffix}"
        val distributedLock: RLock = redissonClient.getLock(distributedLockName)

        return try {
            if (!distributedLock.tryLock(
                    distributedLockAnnotation.waitTime,
                    distributedLockAnnotation.leaseTime,
                    distributedLockAnnotation.timeUnit
                )
            ) {
                throw DistributedLockTimeOutException()
            }
            joinPoint.proceed()
        } finally {
            if (distributedLock.isLocked) {
                distributedLock.unlock()
            }
        }
    }
}
