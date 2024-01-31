package org.adrianliz.playingwithcats.breeds.givens

import io.mockk.every
import io.mockk.unmockkObject
import org.adrianliz.playingwithcats.breeds.domain.Breed
import org.adrianliz.playingwithcats.breeds.domain.BreedChooser

class BreedChooserGiven(private val breedChooser: BreedChooser) {
    fun choosesFirstBreed(breeds: List<Breed>) {
        every {
            val matcher = match<List<Breed>> { filteredBreeds ->
                breeds.containsAll(filteredBreeds)
            }
            breedChooser.chooseOne(matcher)
        } returns breeds.first()
    }

    fun cleanMock() {
        unmockkObject(breedChooser)
    }
}
