package org.adrianliz.playingwithcats.questions.delivery

import org.adrianliz.playingwithcats.questions.domain.QuestionCanNotBeCreatedException
import org.adrianliz.playingwithcats.questions.usecases.QuestionCreatorUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
class CreateQuestionController(private val useCase: QuestionCreatorUseCase) {
    @PostMapping("/questions")
    fun createQuestion(): CreateQuestionResponse {
        try {
            val question = useCase.create()
            return CreateQuestionResponse(
                question.id.toString(),
                question.breeds.map { CreateQuestionBreedResponse(it.id, it.name) },
                CreateQuestionCatResponse(question.cat.id, question.cat.imageUrl),
            )
        } catch (ex: QuestionCanNotBeCreatedException) {
            throw ResponseStatusException(HttpStatus.BAD_GATEWAY, "Question could not be created", ex)
        }
    }
}
