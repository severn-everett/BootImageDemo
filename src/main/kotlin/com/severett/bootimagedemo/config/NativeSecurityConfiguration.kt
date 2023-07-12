package com.severett.bootimagedemo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.core.env.get
import org.springframework.security.config.Customizer
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.server.SecurityWebFilterChain

private const val SECURITY_ENABLED = "bootimagedemo.securityEnabled"

@Configuration
class NativeSecurityConfiguration(private val env: Environment) {
    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        val securityEnabled = env[SECURITY_ENABLED]?.toBoolean() ?: false
        return if (securityEnabled) {
            http.authorizeExchange { exchanges ->
                exchanges.anyExchange().authenticated()
            }
                .httpBasic(Customizer.withDefaults())
                .build()
        } else {
            http
                .authorizeExchange { it.anyExchange().permitAll() }
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic {
                }.build()
        }
    }

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
