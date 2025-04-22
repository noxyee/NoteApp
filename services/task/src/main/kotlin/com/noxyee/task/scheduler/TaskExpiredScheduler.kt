package com.noxyee.task.scheduler

import com.noxyee.task.service.TaskExpiredService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class TaskExpiredScheduler(private val taskExpiredService: TaskExpiredService) {

    @Scheduled(cron = "\${task-expires.cron}")
    fun tasksExpired() {
        taskExpiredService.processExpiredTasks()
    }
}