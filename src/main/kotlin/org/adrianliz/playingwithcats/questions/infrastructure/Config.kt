package org.adrianliz.playingwithcats.questions.infrastructure

import InMemoryQuestionRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Config {
    @Bean
    fun questionRepository() = InMemoryQuestionRepository()
}
