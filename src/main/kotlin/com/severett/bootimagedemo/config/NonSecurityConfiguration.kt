package com.severett.bootimagedemo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.Customizer
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@Profile("noSecurity")
class NonSecurityConfiguration {
    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http.authorizeExchange { exchanges ->
            exchanges.anyExchange().permitAll()
        }
            .httpBasic(Customizer.withDefaults())
            .build()
    }
}
