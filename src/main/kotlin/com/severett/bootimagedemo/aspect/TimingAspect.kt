package com.severett.bootimagedemo.aspect

import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.context.annotation.ImportRuntimeHints
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger { }

@Aspect
@ImportRuntimeHints(AspectRuntimeHints::class)
@Component
class TimingAspect {
    @Around(value= "execution(* com.severett.bootimagedemo.controller.AuthorController.*(..))")
    fun aroundAuthorController(pjp: ProceedingJoinPoint) = execTimingAdvice(pjp)

    @Around(value= "execution(* com.severett.bootimagedemo.controller.BookController.*(..))")
    fun aroundBookController(pjp: ProceedingJoinPoint) = execTimingAdvice(pjp)

    private fun execTimingAdvice(pjp: ProceedingJoinPoint): Any {
        val startTime = getNanoTime()
        try {
            return pjp.proceed()
        } finally {
            val endTime = getNanoTime()
            if (startTime != null && endTime != null) {
                logger.debug { "Time elapsed for ${pjp.signature.name}: ${(endTime - startTime) / 1_000} µs" }
            }
        }
    }

    private fun getNanoTime() = if (logger.isDebugEnabled) System.nanoTime() else null
}
