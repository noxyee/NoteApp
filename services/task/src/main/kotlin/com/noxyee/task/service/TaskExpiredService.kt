package com.noxyee.task.service

import com.noxyee.task.dao.TaskRepository
import com.noxyee.task.kafka.producer.TaskExpiredProducer
import com.noxyee.task.model.TaskExpiredMessage
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TaskExpiredService(
    private val taskRepository: TaskRepository,
    private val taskExpiredProducer: TaskExpiredProducer
) {

    fun processExpiredTasks() {
        val now = LocalDateTime.now()
            .withNano(0)
            .withSecond(0)
            .withMinute(0)
        val startTime = now.minusHours(1)
        val endTime = now.minusNanos(1)
        val expiredTasks =
            taskRepository.findAllBySendMailAfterDueDateTrueAndDoneFalseAndDueDateBetween(startTime, endTime)
        expiredTasks.forEach {
            val taskExpiredMessage = TaskExpiredMessage(it.userId, it.title, it.dueDate!!)
            taskExpiredProducer.sendTaskExpiredMessage(taskExpiredMessage)
        }
    }
}