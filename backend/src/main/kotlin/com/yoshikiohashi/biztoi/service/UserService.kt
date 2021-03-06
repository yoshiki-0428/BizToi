package com.yoshikiohashi.biztoi.service

import com.yoshikiohashi.biztoi.model.CognitoJWT
import com.yoshikiohashi.biztoi.model.TokenClaims
import com.yoshikiohashi.biztoi.repositories.UserRepository
import com.yoshikiohashi.biztoi.tables.pojos.User
import org.springframework.stereotype.Service
import java.sql.Timestamp

@Service
class UserService(
        private val userRepository: UserRepository
) {
    fun createUser(claims: TokenClaims, cognitoJWT: CognitoJWT): String {
        // user検索
        val user = userRepository.findOne(claims.uuid)

        // user作成
        if (user == null) {
            userRepository.create(mapping(claims, cognitoJWT))
        }
        return cognitoJWT.id_token
    }

    fun updateToken(claims: TokenClaims, cognitoJWT: CognitoJWT) {
        userRepository.update(mapping(claims, cognitoJWT))
    }

    fun getRefreshToken(token: String): String = userRepository.findOneIdToken(token)!!.refreshToken

    private fun mapping(claims: TokenClaims, cognitoJWT: CognitoJWT) =
            User(
                    claims.uuid,
                    cognitoJWT.id_token,
                    cognitoJWT.access_token,
                    cognitoJWT.refresh_token,
                    Timestamp(0),
                    Timestamp(0)
            )
}