package org.adrianliz.playingwithcats.questions.domain

import java.util.Optional
import java.util.UUID

interface QuestionRepository {
    fun save(question: Question)

    fun findById(questionId: UUID): Optional<Question>
}
