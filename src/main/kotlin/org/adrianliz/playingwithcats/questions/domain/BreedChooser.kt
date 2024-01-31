package org.adrianliz.playingwithcats.questions.domain

import org.adrianliz.playingwithcats.breeds.domain.Breed

interface BreedChooser {
    fun chooseOne(breeds: List<Breed>): Breed
}
