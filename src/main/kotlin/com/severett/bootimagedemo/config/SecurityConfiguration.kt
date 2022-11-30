package com.severett.bootimagedemo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain


@Configuration
@Profile("withSecurity")
class SecurityConfiguration {
    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http.authorizeExchange { exchanges ->
                exchanges.anyExchange().authenticated()
            }
            .httpBasic(withDefaults())
            .build()
    }
}
