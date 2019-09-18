package com.yoshikiohashi.biztoi.repositories

import com.yoshikiohashi.biztoi.Tables.USER
import com.yoshikiohashi.biztoi.tables.pojos.User
import com.yoshikiohashi.biztoi.tables.records.UserRecord
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class UserRepository(private val ctx: DSLContext) {
    fun findAll(): List<User> = ctx
            .select()
            .from(USER)
            .fetch()
            .into(User::class.java)

    fun findOne(id: String): UserRecord? = ctx
            .selectFrom(USER)
            .where(USER.ID.eq(id))
            .fetchOne()

    fun create(user: User): Int = ctx
            .insertInto(USER,
                    USER.ID, USER.ID_TOKEN, USER.ACCESS_TOKEN, USER.REFRESH_TOKEN)
            .values(user.id, user.idToken, user.accessToken, user.refreshToken)
            .execute()

    fun update(user: User): Int = ctx
            .update(USER)
            .set(USER.ID_TOKEN, user.idToken)
            .set(USER.ACCESS_TOKEN, user.accessToken)
            .where(USER.ID.eq(user.id))
            .execute()
}