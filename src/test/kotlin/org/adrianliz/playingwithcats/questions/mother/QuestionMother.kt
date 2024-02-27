package org.adrianliz.playingwithcats.questions.mother

import org.adrianliz.playingwithcats.breeds.domain.Breed
import org.adrianliz.playingwithcats.breeds.mother.BreedMother
import org.adrianliz.playingwithcats.cats.domain.Cat
import org.adrianliz.playingwithcats.cats.mother.CatMother
import org.adrianliz.playingwithcats.questions.domain.Question

class QuestionMother {
    companion object {
        fun withCatMatchingBreed(breed: Breed): Question {
            return create(
                breeds = listOf(breed, BreedMother.random(), BreedMother.random()),
                cat = CatMother.randomWithBreed(breed),
            )
        }

        private fun create(
            breeds: List<Breed>,
            cat: Cat,
        ): Question {
            return Question(breeds = breeds, cat = cat)
        }
    }
}
