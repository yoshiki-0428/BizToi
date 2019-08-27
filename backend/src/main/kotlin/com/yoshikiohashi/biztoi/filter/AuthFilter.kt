package com.yoshikiohashi.biztoi.filter

import com.nimbusds.jose.proc.BadJOSEException
import com.yoshikiohashi.biztoi.model.CognitoAuthenticationToken
import com.nimbusds.jose.proc.SecurityContext
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor
import com.yoshikiohashi.biztoi.model.CognitoJWT
import com.yoshikiohashi.biztoi.service.AuthService
import com.yoshikiohashi.biztoi.toBase64
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Filter that handles jwt authentication.
 *
 * @property processor Processor for AWS ID token
 */
class AuthFilter(
        private val processor: ConfigurableJWTProcessor<SecurityContext>,
        authenticationManager: AuthenticationManager,
        private val authService: AuthService
) : BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(
            req: HttpServletRequest,
            res: HttpServletResponse,
            chain: FilterChain
    ) {
        try {
            val token = extractToken(req.getHeader("Authorization"))
            val authentication = extractAuthentication(token, req.getHeader("RefreshToken"))

            SecurityContextHolder.getContext().authentication = authentication
            chain.doFilter(req, res)
        } catch (e: AccessDeniedException) {
            LoggerFactory.getLogger(this.javaClass.simpleName).error("Access denied: ${e.message ?: "No message"}")
            res.status = 401
            res.writer.write("Access denied")
        }
    }

    /**
     * Extract token from header
     */
    private fun extractToken(header: String?): String? {
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
    @Throws(AccessDeniedException::class)
    private fun extractAuthentication(token: String?, refreshToken: String?): CognitoAuthenticationToken? {
        if (token == null)
            return null

        return try {
            val claims = processor.process(token, null)

            CognitoAuthenticationToken(token, claims)
        } catch (be: BadJOSEException) {
            val cognitoJWT: CognitoJWT =
                    authService.refreshToken(refreshToken) ?: throw AccessDeniedException("${be.javaClass.simpleName} (${be.message ?: "No message"})")
            extractAuthentication(cognitoJWT.id_token, cognitoJWT.refresh_token)
        } catch (e: Exception) {
            throw AccessDeniedException("${e.javaClass.simpleName} (${e.message ?: "No message"})")
        }
    }
}