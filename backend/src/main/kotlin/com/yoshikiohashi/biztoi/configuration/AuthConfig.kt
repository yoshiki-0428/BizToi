package com.yoshikiohashi.biztoi.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsWebFilter
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource
import org.springframework.web.reactive.config.EnableWebFlux


/**
 * Configuration for web security
 */
@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@EnableWebFlux
class AuthConfig(
        private val securityContextRepository: SecurityContextRepository
) {
    @Bean
    fun securityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain = http
            // basic, csrf, logout off
            .httpBasic().disable()
            .formLogin().disable()
            .csrf().disable()
            .logout().disable()
            // 認証・認可の設定
            .securityContextRepository(this.securityContextRepository)
            .authorizeExchange()
            // アクセス可能URL
            .pathMatchers("/api/auth/**").permitAll()
            .anyExchange().authenticated()
            .and().addFilterAt(corsWebFilter(), SecurityWebFiltersOrder.CORS)
            .build()

    @Value("\${urls.front}")
    val frontUrl: String = ""

    fun corsWebFilter(): CorsWebFilter {
        val corsConfig = CorsConfiguration()
        corsConfig.addAllowedOrigin(frontUrl)
        corsConfig.addAllowedMethod(HttpMethod.GET)
        corsConfig.addAllowedMethod(HttpMethod.OPTIONS)
        corsConfig.addAllowedHeader(CorsConfiguration.ALL)
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", corsConfig)
        return CorsWebFilter(source)
    }
}