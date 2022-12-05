package com.severett.bootimagedemo.aspect

import org.springframework.aot.hint.MemberCategory
import org.springframework.aot.hint.RuntimeHints
import org.springframework.aot.hint.RuntimeHintsRegistrar
import org.springframework.beans.factory.BeanClassLoaderAware
import org.springframework.beans.factory.FactoryBean
import org.springframework.boot.availability.ApplicationAvailability
import org.springframework.context.ApplicationListener

class AspectRuntimeHints : RuntimeHintsRegistrar {
    override fun registerHints(hints: RuntimeHints, classLoader: ClassLoader?) {
        hints.reflection().registerType(TimingAspect::class.java) { builder ->
            builder.withMembers(MemberCategory.INVOKE_DECLARED_METHODS)
        }
        hints.proxies().registerJdkProxy(
            FactoryBean::class.java,
            BeanClassLoaderAware::class.java,
            ApplicationListener::class.java
        )
        hints.proxies().registerJdkProxy(ApplicationAvailability::class.java, ApplicationListener::class.java)
    }
}
