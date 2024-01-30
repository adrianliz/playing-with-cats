package org.adrianliz.playingwithcats.cats.infrastructure.repository

import org.adrianliz.playingwithcats.cats.domain.Cat
import org.adrianliz.playingwithcats.cats.domain.CatFilter
import org.adrianliz.playingwithcats.cats.domain.CatRepository
import org.adrianliz.playingwithcats.cats.infrastructure.thecatapi.ImagesClient
import org.springframework.stereotype.Service

@Service
class TheCatApiRepository(private val client: ImagesClient) : CatRepository {
    
    override fun search(filter: CatFilter): List<Cat> {
        return client.getCatsMatching(filter)
    }
}
