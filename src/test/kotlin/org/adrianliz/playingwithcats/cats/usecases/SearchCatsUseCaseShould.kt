package org.adrianliz.playingwithcats.cats.usecases

import io.mockk.every
import io.mockk.mockk
import org.adrianliz.playingwithcats.cats.domain.Cat
import org.adrianliz.playingwithcats.cats.domain.CatFilter
import org.adrianliz.playingwithcats.cats.infrastructure.repository.TheCatApiRepository
import org.adrianliz.playingwithcats.cats.infrastructure.thecatapi.ImagesClient
import org.adrianliz.playingwithcats.cats.mother.CatMother
import kotlin.test.Test
import kotlin.test.assertEquals

class SearchCatsUseCaseShould {
    private val client = mockk<ImagesClient>()
    private val catsRepository = TheCatApiRepository(client)

    private fun givenThereIsACatMatching(filter: CatFilter, cat: Cat) {
        every {
            client.getCatsMatching(filter)
        } returns listOf(cat)
    }

    @Test
    fun `search a cat by breed`() {
        val existingCat = CatMother.random()
        val filter = CatFilter(existingCat.breedId)
        givenThereIsACatMatching(filter, existingCat)
        val searchCatsUseCase = SearchCatsUseCase(catsRepository)

        val cats = searchCatsUseCase.search(filter)

        assertEquals(cats[0].breedId, existingCat.breedId)
    }
}