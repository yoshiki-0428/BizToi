package com.yoshikiohashi.biztoi.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.reactive.config.WebFluxConfigurerComposite


/**
 * Configuration for web security
 */
@EnableWebFluxSecurity
class AuthConfig(private val securityContextRepository: SecurityContextRepository
) {
    @Value("\${urls.front}")
    val frontUrl: String = ""

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
    fun corsConfigurer(): WebFluxConfigurer {
        return object : WebFluxConfigurerComposite() {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                        .allowedOrigins(frontUrl)
                        .allowedMethods("*")
                        .allowedHeaders("*")
                        .maxAge(3600)
            }
        }
    }
}