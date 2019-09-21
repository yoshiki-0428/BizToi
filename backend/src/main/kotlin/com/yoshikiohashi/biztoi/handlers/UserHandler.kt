package com.yoshikiohashi.biztoi.handlers

import com.yoshikiohashi.biztoi.service.AuthService
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono

/**
 * User endpoints, require authentication
 */
@Component
class UserHandler(val authService: AuthService) {
    fun getCurrentUser(req: ServerRequest): Mono<ServerResponse> =
        ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .syncBody(authService.getClaims())
}