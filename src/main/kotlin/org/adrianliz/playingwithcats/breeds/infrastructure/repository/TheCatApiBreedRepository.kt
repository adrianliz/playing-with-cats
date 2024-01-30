package org.adrianliz.playingwithcats.breeds.infrastructure.repository

import org.adrianliz.playingwithcats.breeds.domain.BreadFilter
import org.adrianliz.playingwithcats.breeds.domain.Breed
import org.adrianliz.playingwithcats.breeds.domain.BreedRepository
import org.adrianliz.playingwithcats.breeds.infrastructure.thecatapi.BreedsClient
import org.springframework.stereotype.Repository

@Repository
class TheCatApiBreedRepository(private val client: BreedsClient) : BreedRepository {

    override fun search(filter: BreadFilter): List<Breed> {
        return filter.filter(findAll())
    }

    private fun findAll(): List<Breed> {
        return client.getAllBreeds()
    }
}
