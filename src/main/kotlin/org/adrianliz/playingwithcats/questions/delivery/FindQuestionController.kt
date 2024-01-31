package org.adrianliz.playingwithcats.questions.delivery

import org.adrianliz.playingwithcats.questions.domain.QuestionNotFoundException
import org.adrianliz.playingwithcats.questions.usecases.FindQuestionUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
class FindQuestionController(private val useCase: FindQuestionUseCase) {

    @GetMapping("/questions/{questionId}")
    fun findQuestion(@PathVariable("questionId") id: String): FindQuestionResponse {
        try {
            val questionId = UUID.fromString(id)
            return useCase.find(questionId).let {
                FindQuestionResponse(
                    it.id.toString(),
                    it.status.name,
                )
            }
        } catch (ex: QuestionNotFoundException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found", ex)
        } catch (ex: IllegalArgumentException) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid question id", ex)
        }
    }

}
