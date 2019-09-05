package com.yoshikiohashi.biztoi.controllers

import com.yoshikiohashi.biztoi.service.AuthService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono
import java.net.URI


/**
 * Auth endpoints
 */
@Component
class AuthController(val authService: AuthService) {
    @Value("\${endpoints.authorize}")
    private val authorizeUrl: String = ""

    @Value("\${endpoints.logout}")
    private val logoutUrl: String = ""

    /**
     * Redirect user to correct url for authorization code
     */
    fun login(req: ServerRequest) =
            ServerResponse.permanentRedirect(URI(authorizeUrl)).build()

    fun logout(req: ServerRequest) =
            ServerResponse.permanentRedirect(URI(logoutUrl)).build()

    /**
     * Get aws tokens with authorization code
     */
    fun token(req: ServerRequest): Mono<ServerResponse> {
        return ServerResponse
                .ok().body(BodyInserters.fromObject(authService.getToken(req.queryParam("code").get())!!))
    }
}
