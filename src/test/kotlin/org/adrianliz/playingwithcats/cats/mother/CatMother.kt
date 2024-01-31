package org.adrianliz.playingwithcats.cats.mother

import org.adrianliz.playingwithcats.cats.domain.Cat
import org.adrianliz.playingwithcats.common.mother.StringMother

class CatMother {
    companion object {
        fun random() = createCat()

        private fun createCat(
            breedId: String = StringMother.random(),
            breedName: String = StringMother.random()
        ) = Cat(breedId, breedName)
    }
}
