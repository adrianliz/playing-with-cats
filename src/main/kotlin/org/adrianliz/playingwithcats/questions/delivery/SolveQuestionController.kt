package org.adrianliz.playingwithcats.questions.delivery

import org.adrianliz.playingwithcats.questions.domain.QuestionAnswer
import org.adrianliz.playingwithcats.questions.domain.QuestionNotFoundException
import org.adrianliz.playingwithcats.questions.usecases.QuestionSolverUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
class SolveQuestionController(
    private val useCase: QuestionSolverUseCase,
) {
    @PostMapping("/questions/{questionId}/solve")
    fun solveQuestion(
        @PathVariable("questionId") id: String,
        @RequestBody payload: QuestionAnswerPayload,
    ) {
        try {
            val questionId = UUID.fromString(id)
            val questionAnswer = QuestionAnswer(questionId, payload.breedId)
            useCase.solve(questionAnswer)
        } catch (ex: QuestionNotFoundException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found", ex)
        } catch (ex: IllegalArgumentException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid question id", ex)
        }
    }
}
