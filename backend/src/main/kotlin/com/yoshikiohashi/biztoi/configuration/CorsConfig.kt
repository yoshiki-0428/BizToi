package com.yoshikiohashi.biztoi.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.reactive.config.EnableWebFlux


@Configuration
@EnableWebFlux
class CorsGlobalConfiguration : WebFluxConfigurer {
    @Value("\${urls.front}")
    private val frontUrl: String = ""

    override fun addCorsMappings(corsRegistry: CorsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins(frontUrl)
                .allowedMethods("*")
                .allowedHeaders("*")
                .maxAge(3600)
    }
}