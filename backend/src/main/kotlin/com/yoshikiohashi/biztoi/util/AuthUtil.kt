package com.yoshikiohashi.biztoi.util

import com.nimbusds.jose.proc.SecurityContext
import com.nimbusds.jwt.proc.BadJWTException
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor
import com.yoshikiohashi.biztoi.model.CognitoAuthenticationToken
import com.yoshikiohashi.biztoi.model.TokenClaims
import com.yoshikiohashi.biztoi.service.AuthService
import com.yoshikiohashi.biztoi.service.UserService
import org.slf4j.LoggerFactory
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
        val token = extractToken(header) ?: return null
        return extractAuthentication(token)
    }

    /**
     * Extract authentication details from token
     */
    fun extractAuthentication(token: String): CognitoAuthenticationToken? {
        // TODO Role設定, Refactoring
        val ls: List<GrantedAuthority> = listOf("ROLE_USER", "ROLE_ADMIN").map { role -> SimpleGrantedAuthority(role) }
        return try {
            CognitoAuthenticationToken(token, getClaims(token), ls)
        } catch (je: BadJWTException) {
            LoggerFactory.getLogger(this.javaClass.simpleName).error("BadJWTException: ${je.message ?: "No message"}")
            // TODO トークンが切れていた場合, IdToken をキーにユーザ情報を取得
            // TODO ユーザ情報のRefreshTokenを使用し、IdTokenを更新
            // TODO IdTokenをヘッダーに格納して返却、CognitoAuthenticationToken を返却して一時的にアクセス可能とする
            // 一時的に仮ユーザとしてアクセスは可能
            CognitoAuthenticationToken("refreshToken", TokenClaims(), ls)
        // TODO Role設定, Refactoring
        } catch (e: Exception) {
            LoggerFactory.getLogger(this.javaClass.simpleName).error("JWTException: ${e.message ?: "No message"}")
            null
        }
    }

    fun getClaims(token: String) = TokenClaims(processor.process(token, null))

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
}