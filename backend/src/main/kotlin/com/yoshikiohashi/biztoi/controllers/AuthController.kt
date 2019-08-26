package com.yoshikiohashi.biztoi.controllers

import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthRequest
import com.amazonaws.services.cognitoidp.model.AuthFlowType
import com.yoshikiohashi.biztoi.model.CognitoJWT
import com.yoshikiohashi.biztoi.service.AuthService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.HashMap
import com.amazonaws.regions.Regions
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider
import com.amazonaws.auth.profile.ProfileCredentialsProvider
import com.amazonaws.auth.AWSCredentialsProvider
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult


/**
 * Auth endpoints
 */
@RestController
@RequestMapping("/auth")
class AuthController(val authService: AuthService) {
    @Value("\${endpoints.authorize}")
    private val authorizeUrl: String = ""

    @Value("\${endpoints.logout}")
    private val logoutUrl: String = ""

    /**
     * Redirect user to correct url for authorization code
     */
    @GetMapping("/login")
    fun login(): ResponseEntity<Any> =
            ResponseEntity
                    .status(HttpStatus.SEE_OTHER)
                    .header(HttpHeaders.LOCATION, authorizeUrl)
                    .build()

    /**
     * Get aws tokens with authorization code
     */
    @GetMapping("/token")
    fun token(@RequestParam("code") code: String): ResponseEntity<CognitoJWT?> =
            authService.getToken(code)

    @GetMapping("/logout")
    fun logout(): ResponseEntity<Any> =
            ResponseEntity
                    .status(HttpStatus.SEE_OTHER)
                    .header(HttpHeaders.LOCATION, logoutUrl)
                    .build()
}
