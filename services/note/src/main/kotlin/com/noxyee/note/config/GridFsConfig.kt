package com.noxyee.note.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.core.convert.MongoConverter
import org.springframework.data.mongodb.gridfs.GridFsTemplate

@Configuration
class GridFsConfig {

    @Bean
    fun gridFsTemplate(mongoDatabaseFactory: MongoDatabaseFactory, mongoConverter: MongoConverter): GridFsTemplate {
        return GridFsTemplate(mongoDatabaseFactory, mongoConverter)
    }
}
