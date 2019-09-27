package com.yoshikiohashi.biztoi.handlers

import com.yoshikiohashi.biztoi.service.AuthService
import com.yoshikiohashi.biztoi.service.UserService
import com.yoshikiohashi.biztoi.util.AuthUtil
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.badRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono
import java.net.URI


/**
 * Auth endpoints
 */
@Component
class AuthHandler(
        private val authUtil: AuthUtil,
        private val authService: AuthService,
        private val userService: UserService
) {
    @Value("\${endpoints.authorize}")
    private val authorizeUrl: String = ""

    @Value("\${endpoints.logout}")
    private val logoutUrl: String = ""

    /**
     * Redirect user to correct url for authorization code
     */
    fun login(req: ServerRequest): Mono<ServerResponse> {
        val redirect: String = req.queryParam("redirect_uri").get()
        return ServerResponse.permanentRedirect(URI(authorizeUrl + redirect)).build()
    }

    fun logout(req: ServerRequest): Mono<ServerResponse> {
        val redirect: String = req.queryParam("redirect_uri").get()
        return ServerResponse.permanentRedirect(URI(logoutUrl + redirect)).build()
    }

    /**
     * Get aws tokens with authorization code
     */
    fun token(req: ServerRequest): Mono<ServerResponse> {
        val cognitoJWT = authService.getToken(
                req.queryParam("code").orElse(null),
                req.queryParam("redirect_uri").orElse(null))
        return if (cognitoJWT != null) {
            ok().syncBody(
                    userService.createUser(authUtil.getClaims(cognitoJWT.id_token), cognitoJWT)
            )
        } else {
            badRequest().build()
        }
    }
}
