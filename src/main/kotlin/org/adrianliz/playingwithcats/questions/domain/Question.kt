package org.adrianliz.playingwithcats.questions.domain

import org.adrianliz.playingwithcats.breeds.domain.Breed
import org.adrianliz.playingwithcats.cats.domain.Cat
import java.util.UUID

data class Question(
    val id: UUID = UUID.randomUUID(),
    val breeds: List<Breed>,
    val cat: Cat,
    var status: QuestionStatus = QuestionStatus.CREATED,
) {
    fun hasBeenSolved() = status != QuestionStatus.CREATED

    fun solve(answer: QuestionAnswer) {
        if (answer.breedId == cat.breed.id) {
            status = QuestionStatus.SOLVED
            return
        }
        status = QuestionStatus.FAILED
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Question

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
