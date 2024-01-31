package org.adrianliz.playingwithcats.breeds.domain

interface BreedChooser {
    fun chooseOne(breeds: List<Breed>): Breed
}
