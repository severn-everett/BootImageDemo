package com.severett.bootimagedemo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

private const val SECURITY_ENABLED = "bootimagedemo.securityEnabled"

@Configuration
class SecurityConfiguration(private val env: Environment) {
    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        val securityEnabled = env[SECURITY_ENABLED]?.toBoolean() ?: false
        return if (securityEnabled) {
            http.authorizeExchange { exchanges ->
                exchanges.anyExchange().authenticated()
            }
                .httpBasic(withDefaults())
                .build()
        } else {
            http.authorizeExchange { exchanges ->
                exchanges.anyExchange().permitAll()
            }
                .csrf().disable()
                .build()
        }
    }
}
