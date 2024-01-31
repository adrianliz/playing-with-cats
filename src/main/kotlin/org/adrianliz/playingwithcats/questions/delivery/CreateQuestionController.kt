package org.adrianliz.playingwithcats.questions.delivery

import org.adrianliz.playingwithcats.questions.usecases.QuestionCreatorUseCase
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CreateQuestionController(private val useCase: QuestionCreatorUseCase) {

    @PostMapping("/questions")
    fun createQuestion(): CreateQuestionResponse {
        val question = useCase.create()
        return CreateQuestionResponse(
            question.id.toString(),
            question.breeds.map { CreateQuestionBreedResponse(it.id, it.name) },
            CreateQuestionCatResponse(question.cat.id, question.cat.imageUrl)
        )
    }
}
