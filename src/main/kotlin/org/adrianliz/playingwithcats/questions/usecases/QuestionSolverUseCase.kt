package org.adrianliz.playingwithcats.questions.usecases

import org.adrianliz.playingwithcats.questions.domain.QuestionAnswer
import org.adrianliz.playingwithcats.questions.domain.QuestionRepository
import org.springframework.stereotype.Service

@Service
class QuestionSolverUseCase(private val questionRepository: QuestionRepository) {
    fun solve(questionAnswer: QuestionAnswer) {
        val question = questionRepository.findById(questionAnswer.questionId)
        question.ifPresent {
            it.solve(questionAnswer)
            questionRepository.save(it)
        }
    }
}
