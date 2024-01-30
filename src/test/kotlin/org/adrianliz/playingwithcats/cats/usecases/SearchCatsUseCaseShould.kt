package org.adrianliz.playingwithcats.cats.usecases

import io.mockk.every
import io.mockk.mockk
import org.adrianliz.playingwithcats.cats.domain.Cat
import org.adrianliz.playingwithcats.cats.domain.CatFilter
import org.adrianliz.playingwithcats.cats.infrastructure.repository.TheCatApiRepository
import org.adrianliz.playingwithcats.cats.infrastructure.thecatapi.ImagesClient
import kotlin.test.Test
import kotlin.test.assertEquals

class SearchCatsUseCaseShould {
    private val client = mockk<ImagesClient>()
    private val catsRepository = TheCatApiRepository(client)

    private fun randomString(): String {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return List(10) { charPool.random() }.joinToString("")
    }

    private fun createCat(
        breedId: String = randomString(),
        breedName: String = randomString()
    ) = Cat(breedId, breedName)

    private fun givenThereIsACatMatching(filter: CatFilter, cat: Cat) {
        every {
            client.getCatsMatching(filter)
        } returns listOf(cat)
    }

    @Test
    fun `search a cat by breed`() {
        val existingCat = createCat()
        val filter = CatFilter(existingCat.breedId)
        givenThereIsACatMatching(filter, existingCat)
        val searchCatsUseCase = SearchCatsUseCase(catsRepository)

        val cats = searchCatsUseCase.search(filter)

        assertEquals(cats[0].breedId, existingCat.breedId)
    }
}
