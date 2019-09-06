package com.yoshikiohashi.biztoi

import com.yoshikiohashi.biztoi.util.FlywayUtil
import org.springframework.beans.factory.getBean
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BizToiApplication

fun main(args: Array<String>) {
	val run = runApplication<BizToiApplication>(*args)
	val flywayUtil: FlywayUtil = run.getBean(FlywayUtil::class)
	flywayUtil.migrate()
}
