package com.noxyee.task

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.data.mongodb.config.EnableMongoAuditing
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.transaction.annotation.EnableTransactionManagement

@EnableMongoAuditing
@EnableTransactionManagement
@EnableScheduling
@EnableFeignClients
@SpringBootApplication
class TaskApplication

fun main(args: Array<String>) {
    runApplication<TaskApplication>(*args)
}
