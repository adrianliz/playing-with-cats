package org.adrianliz.playingwithcats.cats.mother

import org.adrianliz.playingwithcats.breeds.domain.Breed
import org.adrianliz.playingwithcats.breeds.mother.BreedMother
import org.adrianliz.playingwithcats.cats.domain.Cat
import org.adrianliz.playingwithcats.common.mother.StringMother

class CatMother {
    companion object {
        fun random() = createCat()

        fun randomWithBreed(breed: Breed) = createCat(breed = breed)

        private fun createCat(
            id: String = StringMother.random(),
            url: String = StringMother.random(),
            breed: Breed = BreedMother.random(),
        ) = Cat(id, url, breed)
    }
}
