package com.noxyee.tag

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@EnableCaching
@SpringBootApplication
class TagApplication

fun main(args: Array<String>) {
	runApplication<TagApplication>(*args)
}
