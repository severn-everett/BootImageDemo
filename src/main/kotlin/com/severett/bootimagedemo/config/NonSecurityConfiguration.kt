package com.severett.bootimagedemo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@Profile("!security")
class NonSecurityConfiguration {
    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain =
        http
            .authorizeExchange { it.anyExchange().permitAll() }
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .httpBasic {
            }.build()
}
