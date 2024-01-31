package org.adrianliz.playingwithcats.questions.infrastructure

import org.adrianliz.playingwithcats.questions.domain.Question
import org.adrianliz.playingwithcats.questions.domain.QuestionRepository
import org.springframework.stereotype.Repository

@Repository
class InMemoryQuestionRepository : QuestionRepository {
    private val questions = mutableListOf<Question>()

    override fun save(question: Question) {
        questions.add(question)
    }
}
