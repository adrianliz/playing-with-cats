package org.adrianliz.playingwithcats.cats.infrastructure.repository

import org.adrianliz.playingwithcats.cats.domain.Cat
import org.adrianliz.playingwithcats.cats.domain.CatRepository
import org.springframework.stereotype.Service

@Service
class TheCatApiRepository(private val client: TheCatApiClient) : CatRepository {

    override fun findAll(): List<Cat> {
        return client.getAllCats(1, 10)
    }
}
