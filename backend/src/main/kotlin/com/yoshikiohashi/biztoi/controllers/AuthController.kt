package com.yoshikiohashi.biztoi.controllers

import com.yoshikiohashi.biztoi.model.CognitoJWT
import com.yoshikiohashi.biztoi.service.AuthService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import java.net.URI


/**
 * Auth endpoints
 */
//@RestController
//@RequestMapping("/auth")
@Component
class AuthController(val authService: AuthService) {
    @Value("\${endpoints.authorize}")
    private val authorizeUrl: String = ""

    @Value("\${endpoints.logout}")
    private val logoutUrl: String = ""

    /**
     * Redirect user to correct url for authorization code
     */
//    @GetMapping("/login")
    fun login(req: ServerRequest) =
            ServerResponse.permanentRedirect(URI(authorizeUrl)).build()

    /**
     * Get aws tokens with authorization code
     */
//    @GetMapping("/token")
    fun token(@RequestParam("code") code: String): ResponseEntity<CognitoJWT?> =
            authService.getToken(code)

//    @GetMapping("/logout")
    fun logout(): ResponseEntity<Any> =
            ResponseEntity
                    .status(HttpStatus.SEE_OTHER)
                    .header(HttpHeaders.LOCATION, logoutUrl)
                    .build()
}
