package com.severett.bootimagedemo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.server.SecurityWebFilterChain


@Configuration
@Profile("withSecurity")
class SecurityConfiguration {
    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain =
        http.authorizeExchange { exchanges ->
            exchanges.anyExchange().authenticated()
        }
            .httpBasic(withDefaults())
            .build()

    @Bean
    fun users(): MapReactiveUserDetailsService {
        @Suppress("DEPRECATION")
        val users = User.withDefaultPasswordEncoder()
        val admin = users
            .username("admin")
            .password("password")
            .roles("USER", "ADMIN")
            .build()
        return MapReactiveUserDetailsService(admin)
    }
}
