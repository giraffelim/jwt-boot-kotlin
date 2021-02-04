package com.giraffelim.boot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JwtBootKotlinApplication

fun main(args: Array<String>) {
    runApplication<JwtBootKotlinApplication>(*args)
}
