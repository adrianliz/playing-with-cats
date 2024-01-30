package org.adrianliz.playingwithcats.breeds.domain

interface BreedRepository {
    fun findAll(): List<Breed>
}
