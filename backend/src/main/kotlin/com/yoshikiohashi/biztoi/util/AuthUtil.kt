package com.yoshikiohashi.biztoi.util

import com.nimbusds.jose.proc.SecurityContext
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor
import com.yoshikiohashi.biztoi.model.CognitoAuthenticationToken
import com.yoshikiohashi.biztoi.model.TokenClaims
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component

@Component
class AuthUtil(
        private val processor: ConfigurableJWTProcessor<SecurityContext>
) {
    fun authentication(header: String?): CognitoAuthenticationToken? {
        val token = extractToken(header)
        val ls: List<GrantedAuthority> = listOf("ROLE_USER", "ROLE_ADMIN").map { role -> SimpleGrantedAuthority(role) }
        return if (token == null) {
            CognitoAuthenticationToken("default", TokenClaims(), ls)
        } else {
            extractAuthentication(token)
        }
    }

    /**
     * Extract token from header
     */
    fun extractToken(header: String?): String? {
        val headers = header?.split("Bearer ")

        return if (headers == null || headers.size < 2) {
            null
        } else {
            headers[1]
        }
    }

    /**
     * Extract authentication details from token
     */
    fun extractAuthentication(token: String): CognitoAuthenticationToken? =
            // TODO Role設定
            try {
                val ls: List<GrantedAuthority> = listOf("ROLE_USER", "ROLE_ADMIN").map { role -> SimpleGrantedAuthority(role) }
                CognitoAuthenticationToken(token, getClaims(token), ls)
            } catch (e: Exception) {
                val ls: List<GrantedAuthority> = listOf("ROLE_USER", "ROLE_ADMIN").map { role -> SimpleGrantedAuthority(role) }
                CognitoAuthenticationToken("default", TokenClaims(), ls)
            }

    fun getClaims(token: String) = TokenClaims(processor.process(token, null))
}