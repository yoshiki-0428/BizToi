package com.yoshikiohashi.biztoi.router

import com.yoshikiohashi.biztoi.controllers.AuthController
import com.yoshikiohashi.biztoi.controllers.UserController
import com.yoshikiohashi.biztoi.repositories.BookRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

@Configuration
class Router(
        private val authController: AuthController,
        private val userController: UserController,
        private val bookRepository: BookRepository
) {
    @Bean
    fun apiRouter() = router {
        "/api".nest {
            "/auth".nest {
                accept(MediaType.TEXT_HTML).nest {
                    GET("/login", authController::login)
                    GET("/logout", authController::logout)
                }
                accept(MediaType.APPLICATION_JSON_UTF8).nest {
                    GET("/token", authController::token)
                }
            }

            "/users".nest {
                GET("/me", userController::getCurrentUser)
            }

            "/books".nest {
                GET("/") { ok().syncBody(bookRepository.findAll()) }
            }
        }
    }
}