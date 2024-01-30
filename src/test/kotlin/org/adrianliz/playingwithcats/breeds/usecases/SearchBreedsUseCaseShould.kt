package org.adrianliz.playingwithcats.breeds.usecases

import io.mockk.every
import io.mockk.mockk
import org.adrianliz.playingwithcats.breeds.domain.BreadFilter
import org.adrianliz.playingwithcats.breeds.domain.Breed
import org.adrianliz.playingwithcats.breeds.domain.InvalidNumOfBreedsException
import org.adrianliz.playingwithcats.breeds.infrastructure.repository.TheCatApiBreedRepository
import org.adrianliz.playingwithcats.breeds.infrastructure.thecatapi.BreedsClient
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals


class SearchBreedsUseCaseShould {
    private val breedsClient = mockk<BreedsClient>()
    private val breedRepository = TheCatApiBreedRepository(breedsClient)

    private fun randomString(): String {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return List(10) { charPool.random() }.joinToString("")
    }

    private fun createBreed(
        id: String = randomString(),
        name: String = randomString()
    ) = Breed(id, name)

    private fun givenThereAreBreeds(breeds: List<Breed>) {
        every {
            breedsClient.getAllBreeds()
        } returns breeds
    }

    @ParameterizedTest
    @CsvSource("3, 3", "3, 5")
    fun `search randomly n breeds of m when requested`(numOfBreedsToFilter: Int, totalBreeds: Int) {
        val existingBreeds = IntRange(1, totalBreeds).map {
            createBreed()
        }
        givenThereAreBreeds(existingBreeds)
        val useCase = SearchBreedsUseCase(breedRepository)
        val filter = BreadFilter(numOfBreedsToFilter)

        val foundedBreeds = useCase.search(filter)

        assertEquals(numOfBreedsToFilter, foundedBreeds.size)
        foundedBreeds.forEach {
            assertContains(existingBreeds, it)
        }
    }

    @Test
    fun `throws invalid number of breeds exception when requested more than 10 breeds`() {
        val useCase = SearchBreedsUseCase(breedRepository)

        assertThrows<InvalidNumOfBreedsException> {
            useCase.search(BreadFilter(11))
        }
    }
}
