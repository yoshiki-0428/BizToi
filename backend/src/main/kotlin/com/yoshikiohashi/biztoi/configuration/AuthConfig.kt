package com.yoshikiohashi.biztoi.configuration

import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import reactor.core.publisher.Mono


/**
 * Configuration for web security
 */
@EnableWebFluxSecurity
class AuthConfig(private val securityContextRepository: SecurityContextRepository
) {
    @Bean
    fun securityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .logout().disable()

        http
                .securityContextRepository(this.securityContextRepository)

        http
                .authorizeExchange().pathMatchers("/api/auth/**").permitAll()
                .and()
                .authorizeExchange().anyExchange().authenticated()

        return http.build()
    }

    @Bean
    fun authenticationManager(): ReactiveAuthenticationManager {
        return ReactiveAuthenticationManager { authentication -> Mono.just(authentication) }
    }
}