package org.adrianliz.playingwithcats.questions.domain

import org.adrianliz.playingwithcats.breeds.domain.Breed
import org.adrianliz.playingwithcats.cats.domain.Cat
import java.util.*

data class Question(
    val id: UUID = UUID.randomUUID(),
    val breeds: List<Breed>,
    val cat: Cat,
    val status: QuestionStatus = QuestionStatus.CREATED
)
