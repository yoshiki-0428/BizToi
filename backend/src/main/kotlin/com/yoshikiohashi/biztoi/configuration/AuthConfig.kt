package com.yoshikiohashi.biztoi.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

/**
 * Configuration for web security
 */
@EnableWebFluxSecurity
class AuthConfig(
        val authenticationManager: AuthenticationManager,
        val securityContextRepository: SecurityContextRepository
) {

    @Bean
    fun securityFilterChain(
            http: ServerHttpSecurity
    ): SecurityWebFilterChain {
        http
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .logout().disable()

        http.authenticationManager(this.authenticationManager)
        http.securityContextRepository(this.securityContextRepository)
        http
//                .addFilterAt(AuthFilter(processor, authService), SecurityWebFiltersOrder.AUTHENTICATION)
                .authorizeExchange().pathMatchers("/api/auth/**").permitAll()
        http.authorizeExchange().anyExchange().authenticated()

        return http.build()
    }
}