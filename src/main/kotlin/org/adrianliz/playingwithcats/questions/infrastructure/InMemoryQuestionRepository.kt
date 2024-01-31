package org.adrianliz.playingwithcats.questions.infrastructure

import org.adrianliz.playingwithcats.questions.domain.Question
import org.adrianliz.playingwithcats.questions.domain.QuestionRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class InMemoryQuestionRepository : QuestionRepository {
    private val questions = mutableSetOf<Question>()

    override fun save(question: Question) {
        questions.add(question)
    }

    override fun findById(questionId: UUID): Optional<Question> {
        return questions.stream().filter { it.id == questionId }.findFirst()
    }
}
