package com.yoshikiohashi.biztoi.util

import org.flywaydb.core.Flyway
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration


@Configuration
class FlywayUtil {
    @Value("\${spring.datasource.url}")
    private val url: String = ""

    @Value("\${spring.datasource.username}")
    private val username: String = ""

    @Value("\${spring.datasource.password}")
    private val password: String = ""

    fun migrate() {
        val flyway = Flyway()
        flyway.setDataSource(url, username, password)
        flyway.repair()
        flyway.migrate()
    }

    fun clean() {
        val flyway = Flyway()
        flyway.setDataSource(url, username, password)
        flyway.clean()
    }
}
