package com.yoshikiohashi.biztoi.service

import com.yoshikiohashi.biztoi.model.CognitoJWT
import com.yoshikiohashi.biztoi.model.TokenClaims
import com.yoshikiohashi.biztoi.repositories.UserRepository
import com.yoshikiohashi.biztoi.tables.pojos.User
import org.springframework.stereotype.Service

@Service
class UserService(
        private val userRepository: UserRepository
) {
    fun createUser(claims: TokenClaims, cognitoJWT: CognitoJWT): String {
        // user検索
        val user = userRepository.findOne(claims.uuid)

        // user作成
        return if (user == null) {
            userRepository.create(User(
                    claims.uuid,
                    cognitoJWT.id_token,
                    cognitoJWT.access_token,
                    cognitoJWT.refresh_token
            ))
            return cognitoJWT.id_token
        } else {
            user.idToken
        }
    }

}