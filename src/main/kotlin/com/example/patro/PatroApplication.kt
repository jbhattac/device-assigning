package com.example.patro

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
@EntityScan("com.example.patro.model")
class PatroApplication

fun main(args: Array<String>) {
    runApplication<PatroApplication>(*args)
}
