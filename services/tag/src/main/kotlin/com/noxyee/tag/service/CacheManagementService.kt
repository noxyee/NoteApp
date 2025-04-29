package com.noxyee.tag.service

import com.noxyee.tag.config.RedisConfig
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class CacheManagementService(private val redisTemplate: RedisTemplate<String, Any>) {

    fun removeTagsCacheByUserId(userId: String) {
        removeCacheByKeyPattern("${RedisConfig.TAGS_CACHE_NAME}::*$userId*")
    }

    private fun removeCacheByKeyPattern(pattern: String) {
        redisTemplate.keys(pattern).forEach { redisTemplate.delete(it) }
    }
}