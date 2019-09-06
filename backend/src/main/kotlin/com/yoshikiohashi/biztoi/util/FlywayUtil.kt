//package com.yoshikiohashi.biztoi.util
//
//import org.flywaydb.core.Flyway
//import org.springframework.beans.factory.annotation.Value
//
//
//class FlywayUtil {
//    @Value("\${spring.datasource.url}")
//    private val url: String = ""
//
//    @Value("\${spring.datasource.username}")
//    private val username: String = ""
//
//    @Value("\${spring.datasource.password}")
//    private val password: String = ""
//
//    fun migrate() {
//        val flyway = Flyway.configure().dataSource(url, username, password).load()
//        flyway.repair()
//        flyway.migrate()
//    }
//
//    fun clean() {
//        val flyway = Flyway.configure().dataSource(url, username, password).load()
//        flyway.clean()
//    }
//}
