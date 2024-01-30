package org.adrianliz.playingwithcats.breeds.domain

class BreadFilter(private val numOfBreeds: Int) {
    init {
        if (numOfBreeds < 1) {
            throw InvalidNumOfBreedsException("numOfBreeds must be greater than 0")
        }
        if (numOfBreeds > 10) {
            throw InvalidNumOfBreedsException("numOfBreeds must be less than 10")
        }
    }

    fun filter(breeds: List<Breed>): List<Breed> = breeds.asSequence().shuffled().take(numOfBreeds).toList()
}
