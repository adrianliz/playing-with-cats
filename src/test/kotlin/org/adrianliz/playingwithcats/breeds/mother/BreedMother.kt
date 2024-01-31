package org.adrianliz.playingwithcats.breeds.mother

import org.adrianliz.playingwithcats.breeds.domain.Breed
import org.adrianliz.playingwithcats.common.mother.StringMother

class BreedMother {
    companion object {
        fun randoms(numOfBreeds: Int = 10) = IntRange(1, numOfBreeds).map {
            random()
        }

        fun random() = create()

        private fun create(
            id: String = StringMother.random(),
            name: String = StringMother.random()
        ) = Breed(id, name)
    }
}
