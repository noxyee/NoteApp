package com.noxyee.note

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class NoteApplication

fun main(args: Array<String>) {
    runApplication<NoteApplication>(*args)
}
