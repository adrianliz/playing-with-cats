package org.adrianliz.playingwithcats.questions.domain

import java.util.*

interface QuestionRepository {
    fun save(question: Question)

    fun findById(questionId: UUID): Optional<Question>
}
