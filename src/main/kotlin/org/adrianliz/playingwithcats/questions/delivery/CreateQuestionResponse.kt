package org.adrianliz.playingwithcats.questions.delivery

data class CreateQuestionResponse(
    val id: String,
    val breeds: List<CreateQuestionBreedResponse>,
    val cat: CreateQuestionCatResponse,
)
