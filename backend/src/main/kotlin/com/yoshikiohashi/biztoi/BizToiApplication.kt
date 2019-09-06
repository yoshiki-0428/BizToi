package com.yoshikiohashi.biztoi

import org.flywaydb.core.Flyway
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BizToiApplication

@Value("\${spring.datasource.url}")
private val url: String = ""

@Value("\${spring.datasource.username}")
private val username: String = ""

@Value("\${spring.datasource.password}")
private val password: String = ""

fun main(args: Array<String>) {
	runApplication<BizToiApplication>(*args)
	val flyway = Flyway()
	flyway.setDataSource(url, username, password)
	flyway.repair()
	flyway.migrate()
}
