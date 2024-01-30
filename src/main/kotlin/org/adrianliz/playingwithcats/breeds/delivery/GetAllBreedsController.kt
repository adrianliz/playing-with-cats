package org.adrianliz.playingwithcats.breeds.delivery

import org.adrianliz.playingwithcats.breeds.domain.BreedRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GetAllBreedsController(private val breedRepository: BreedRepository) {

    @GetMapping("/breeds")
    fun getAllBreeds(): GetAllBreedsResponse {
        val breeds = breedRepository.findAll()
        return GetAllBreedsResponse(breeds.map { GetBreedResponse(it.name) })
    }
}
