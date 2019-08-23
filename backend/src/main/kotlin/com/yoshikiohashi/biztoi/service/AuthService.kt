package com.yoshikiohashi.biztoi.service

import com.yoshikiohashi.biztoi.model.CognitoJWT
import com.yoshikiohashi.biztoi.model.TokenClaims
import com.nimbusds.jwt.JWTClaimsSet
import com.yoshikiohashi.biztoi.toBase64
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate
import java.util.Date

/**
 * Service for authentication
 */
@Component
class AuthService {
    @Value("\${endpoints.token}")
    private val tokenUrl: String = ""

    @Value("\${cognito.client}")
    private val clientId: String = ""

    @Value("\${cognito.secret}")
    private val clientSecret: String = ""

    @Value("\${cognito.callback}")
    private val callbackUrl: String = ""

    /**
     * Get token claims from security context
     */
    fun getClaims(): TokenClaims {
        val authentication = SecurityContextHolder.getContext().authentication
        val details = authentication.details as JWTClaimsSet

        return TokenClaims(
                uuid = details.getStringClaim("sub"),
                auth_time = details.getClaim("auth_time") as Long,
                issued = details.getClaim("iat") as Date,
                expire = details.getClaim("exp") as Date,
                name = "test",
                cognitoUserName = "aaa",
                email = details.getStringClaim("email")
        )
    }

    /**
     * Get token with authorization code
     */
    fun getToken(code: String): CognitoJWT? {
        val client = RestTemplate()

        val headers = LinkedMultiValueMap<String, String>()

        val auth = "$clientId:$clientSecret".toBase64()

        headers.add("HeaderName", "value")
        headers.add("Authorization", "Basic $auth")
        headers.add("Content-Type", "application/x-www-form-urlencoded")

        val req = HttpEntity<Nothing?>(null, headers)

        val url = "$tokenUrl?grant_type=authorization_code&client_id=$clientId&code=$code&redirect_uri=$callbackUrl"

        return client.postForObject(url, req, CognitoJWT::class.java)
    }
}
