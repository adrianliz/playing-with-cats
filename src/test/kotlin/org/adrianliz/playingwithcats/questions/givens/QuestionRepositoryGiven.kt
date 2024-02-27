package org.adrianliz.playingwithcats.questions.givens

import io.mockk.every
import org.adrianliz.playingwithcats.questions.domain.Question
import org.adrianliz.playingwithcats.questions.domain.QuestionRepository
import java.util.Optional

class QuestionRepositoryGiven(private val questionRepository: QuestionRepository) {
    fun thereIsQuestion(question: Question) {
        every {
            questionRepository.findById(question.id)
        } returns Optional.of(question)
    }
}
