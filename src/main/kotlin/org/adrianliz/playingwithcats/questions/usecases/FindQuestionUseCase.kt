package org.adrianliz.playingwithcats.questions.usecases

import org.adrianliz.playingwithcats.questions.domain.Question
import org.adrianliz.playingwithcats.questions.domain.QuestionNotFoundException
import org.adrianliz.playingwithcats.questions.domain.QuestionRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class FindQuestionUseCase(private val questionRepository: QuestionRepository) {
    fun find(questionId: UUID): Question {
        return questionRepository.findById(questionId)
            .orElseThrow { QuestionNotFoundException() }
    }
}
