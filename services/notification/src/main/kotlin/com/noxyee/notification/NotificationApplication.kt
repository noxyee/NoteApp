package com.noxyee.notification

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@EnableFeignClients
@SpringBootApplication
class NotificationApplication

fun main(args: Array<String>) {
	runApplication<NotificationApplication>(*args)
}
