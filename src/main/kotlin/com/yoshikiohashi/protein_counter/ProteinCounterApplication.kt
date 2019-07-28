package com.yoshikiohashi.protein_counter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProteinCounterApplication

fun main(args: Array<String>) {
	runApplication<ProteinCounterApplication>(*args)
}
