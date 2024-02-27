package org.adrianliz.playingwithcats.questions.usecases

import org.adrianliz.playingwithcats.questions.domain.QuestionAnswer
import org.adrianliz.playingwithcats.questions.domain.QuestionRepository
import org.springframework.stereotype.Service

@Service
class QuestionSolverUseCase(
    private val questionRepository: QuestionRepository,
    private val findQuestionUseCase: FindQuestionUseCase,
) {
    fun solve(questionAnswer: QuestionAnswer) {
        val question = findQuestionUseCase.find(questionAnswer.questionId)

        question.solve(questionAnswer)
        questionRepository.save(question)
    }
}
