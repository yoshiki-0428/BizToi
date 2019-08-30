package com.yoshikiohashi.biztoi.controllers

import com.sun.security.ntlm.Server
import com.yoshikiohashi.biztoi.model.CognitoJWT
import com.yoshikiohashi.biztoi.model.TokenClaims
import com.yoshikiohashi.biztoi.service.AuthService

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.BodyInserter
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

    fun getCurrentUser2(req: ServerRequest): Mono<ServerResponse> {
        return ServerResponse.ok().body(BodyInserters.fromObject(authService.getClaims()))
    }

}