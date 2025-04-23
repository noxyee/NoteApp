package com.noxyee.note

import io.mongock.runner.springboot.EnableMongock
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableTransactionManagement
@EnableMongoAuditing
@EnableMongock
@EnableFeignClients
@SpringBootApplication
class NoteApplication

fun main(args: Array<String>) {
    runApplication<NoteApplication>(*args)
}
