package com.noxyee.notification.dao

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface NotificationRepository: MongoRepository<NotificationDocument, String> {
}