package org.adrianliz.playingwithcats.breeds.usecases

import org.adrianliz.playingwithcats.breeds.domain.BreadFilter
import org.adrianliz.playingwithcats.breeds.domain.Breed
import org.adrianliz.playingwithcats.breeds.domain.BreedRepository
import org.springframework.stereotype.Service

@Service
class SearchBreedsUseCase(private val breedRepository: BreedRepository) {

    fun search(filter: BreadFilter): List<Breed> {
        return breedRepository.search(filter)
    }
}
