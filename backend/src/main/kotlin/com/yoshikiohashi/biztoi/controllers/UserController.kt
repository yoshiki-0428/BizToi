package com.yoshikiohashi.biztoi.controllers

import com.yoshikiohashi.biztoi.service.AuthService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono

/**
 * User endpoints, require authentication
 */
@RestController
class UserController(val authService: AuthService) {
    fun getCurrentUser(req: ServerRequest): Mono<ServerResponse> =
        ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .syncBody(authService.getClaims())
}