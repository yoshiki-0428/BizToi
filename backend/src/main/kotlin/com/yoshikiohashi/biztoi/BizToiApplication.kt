package com.yoshikiohashi.biztoi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BizToiApplication

fun main(args: Array<String>) {
	val run = runApplication<BizToiApplication>(*args)
}
