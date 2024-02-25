package org.adrianliz.playingwithcats.cats.usecases

import io.mockk.mockk
import org.adrianliz.playingwithcats.cats.domain.CatFilter
import org.adrianliz.playingwithcats.cats.givens.ImagesClientGiven
import org.adrianliz.playingwithcats.cats.infrastructure.repository.TheCatApiRepository
import org.adrianliz.playingwithcats.cats.infrastructure.thecatapi.ImagesClient
import org.adrianliz.playingwithcats.cats.mother.CatMother
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import kotlin.test.assertEquals

class SearchCatsUseCaseShould {
    private val client = mockk<ImagesClient>()
    private val imagesClientGiven = ImagesClientGiven(client)
    private val catsRepository = TheCatApiRepository(client)

    @Test
    fun `search a cat by breed`() {
        val existingCat = CatMother.random()
        val filter = CatFilter(existingCat.breed)
        val searchCatsUseCase = SearchCatsUseCase(catsRepository)
        imagesClientGiven.thereIsACatMatching(filter, existingCat)

        val cats = searchCatsUseCase.search(filter)

        assertEquals(cats[0].breed, existingCat.breed)
    }

    @BeforeEach
    fun clean() {
        imagesClientGiven.cleanMock()
    }
}
