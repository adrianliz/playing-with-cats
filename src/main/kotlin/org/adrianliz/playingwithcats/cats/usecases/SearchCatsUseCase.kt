package org.adrianliz.playingwithcats.cats.usecases

import org.adrianliz.playingwithcats.cats.domain.Cat
import org.adrianliz.playingwithcats.cats.domain.CatFilter
import org.adrianliz.playingwithcats.cats.domain.CatRepository
import org.springframework.stereotype.Service

@Service
class SearchCatsUseCase(private val catsRepository: CatRepository) {
    fun search(filter: CatFilter): List<Cat> {
        return catsRepository.search(filter)
    }
}
