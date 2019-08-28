package com.yoshikiohashi.biztoi

import com.yoshikiohashi.biztoi.controllers.AuthController
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class Router(
        private val authController: AuthController
) {
    @Bean
    fun apiRouter() = router {
        "/api".nest {
            accept(MediaType.TEXT_HTML).nest {
                "/auth".nest {
                    GET("/login", authController::login )
                }
            }
        }
    }
}