package com.yoshikiohashi.biztoi.configuration

import com.yoshikiohashi.biztoi.filter.AuthFilter
import com.nimbusds.jose.proc.SecurityContext
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor
import com.yoshikiohashi.biztoi.service.AuthService
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

/**
 * Configuration for web security
 */
@EnableWebSecurity
class AuthConfig(
        val processor: ConfigurableJWTProcessor<SecurityContext>,
        val authService: AuthService
) : WebSecurityConfigurerAdapter() {
    @Value("\${urls.front}")
    private val frontUrl: String = ""

    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(AuthFilter(processor, authenticationManager(), authService))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http
                .cors()
                .configurationSource(corsConfigurationSource())
    }

    override fun configure(web: WebSecurity) {
        web
                .ignoring()
                .antMatchers(
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**")
    }

    /**
     * CORS設定
     *
     * @return CORS設定
     */
    private fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL)
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL)
        corsConfiguration.addAllowedOrigin(frontUrl)
        corsConfiguration.setAllowCredentials(true)

        val corsConfigurationSource: UrlBasedCorsConfigurationSource = UrlBasedCorsConfigurationSource()
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration)

        return corsConfigurationSource
    }
}
