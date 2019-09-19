package com.yoshikiohashi.biztoi.util

import com.nimbusds.jose.JOSEException
import com.nimbusds.jose.proc.SecurityContext
import com.nimbusds.jwt.proc.BadJWTException
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor
import com.yoshikiohashi.biztoi.model.CognitoAuthenticationToken
import com.yoshikiohashi.biztoi.model.CognitoJWT
import com.yoshikiohashi.biztoi.model.TokenClaims
import com.yoshikiohashi.biztoi.service.AuthService
import com.yoshikiohashi.biztoi.service.UserService
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component

@Component
class AuthUtil(
        private val processor: ConfigurableJWTProcessor<SecurityContext>,
        private val authService: AuthService,
        private val userService: UserService
) {
    fun authentication(header: String?): CognitoAuthenticationToken? {
        return extractToken(header)?.let {
            extractAuthentication(it)
        } ?:run {
            null
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
    fun extractAuthentication(token: String): CognitoAuthenticationToken? {
        // TODO Role設定, Refactoring
        val ls: List<GrantedAuthority> = listOf("ROLE_USER", "ROLE_ADMIN").map { role -> SimpleGrantedAuthority(role) }
        return try {
            CognitoAuthenticationToken(token, getClaims(token), ls)
        } catch (je: JOSEException) {
            val refreshToken = userService.getRefreshToken(token)
            return authService.refreshToken(refreshToken)?.let {
                val tokenClaims = getClaims(it.id_token)
                userService.updateToken(tokenClaims, it)
                CognitoAuthenticationToken(it.id_token, tokenClaims, ls)
            } ?:run {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    fun getClaims(token: String) = TokenClaims(processor.process(token, null))
}