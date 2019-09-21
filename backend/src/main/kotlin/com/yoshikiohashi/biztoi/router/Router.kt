package com.yoshikiohashi.biztoi.router

import com.yoshikiohashi.biztoi.handlers.AuthHandler
import com.yoshikiohashi.biztoi.handlers.UserHandler
import com.yoshikiohashi.biztoi.repositories.BookRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router

@Configuration
class Router(
        private val authHandler: AuthHandler,
        private val userHandler: UserHandler,
        private val bookRepository: BookRepository
) {
    @Bean
    fun apiRouter() = router {
        "/api".nest {
            "/auth".nest {
                accept(MediaType.TEXT_HTML).nest {
                    GET("/login", authHandler::login)
                    GET("/logout", authHandler::logout)
                }
                accept(MediaType.APPLICATION_JSON_UTF8).nest {
                    GET("/token", authHandler::token)
                }
            }

            "/users".nest {
                GET("/me", userHandler::getCurrentUser)
            }

            "/books".nest {
                GET("/") { ok().syncBody(bookRepository.findAll()) }
            }
        }
    }
}