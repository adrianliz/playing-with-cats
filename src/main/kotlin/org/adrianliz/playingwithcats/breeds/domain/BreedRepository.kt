package org.adrianliz.playingwithcats.breeds.domain

interface BreedRepository {
    fun search(filter: BreadFilter): List<Breed>
}
