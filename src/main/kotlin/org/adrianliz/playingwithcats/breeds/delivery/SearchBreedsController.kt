package org.adrianliz.playingwithcats.breeds.delivery

import org.adrianliz.playingwithcats.breeds.domain.BreadFilter
import org.adrianliz.playingwithcats.breeds.usecases.SearchBreedsUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchBreedsController(private val useCase: SearchBreedsUseCase) {

    @GetMapping("/breeds")
    fun searchBreeds(request: SearchBreedsRequest): SearchBreedsResponse {
        val breeds = useCase.search(BreadFilter(request.limit))
        return SearchBreedsResponse(breeds.map { BreedResponse(it.id, it.name) })
    }
}
