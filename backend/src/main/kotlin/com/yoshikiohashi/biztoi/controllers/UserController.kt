package com.yoshikiohashi.biztoi.controllers

import com.yoshikiohashi.biztoi.service.AuthService
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

/**
 * User endpoints, require authentication
 */
@RestController
class UserController(val authService: AuthService) {
    fun getCurrentUser(req: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().body(BodyInserters.fromObject(authService.getClaims()))
    }
}