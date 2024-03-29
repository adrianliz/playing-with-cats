package org.adrianliz.playingwithcats.breeds.usecases

import io.mockk.mockk
import org.adrianliz.playingwithcats.breeds.domain.BreadFilter
import org.adrianliz.playingwithcats.breeds.domain.InvalidNumOfBreedsException
import org.adrianliz.playingwithcats.breeds.givens.BreedsClientGiven
import org.adrianliz.playingwithcats.breeds.infrastructure.repository.TheCatApiBreedRepository
import org.adrianliz.playingwithcats.breeds.infrastructure.thecatapi.BreedsClient
import org.adrianliz.playingwithcats.breeds.mother.BreedMother
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class SearchBreedsUseCaseShould {
    private val breedsClient = mockk<BreedsClient>()
    private val breedsClientGiven = BreedsClientGiven(breedsClient)
    private val breedRepository = TheCatApiBreedRepository(breedsClient)

    @ParameterizedTest
    @CsvSource("3, 3", "3, 5")
    fun `search randomly n breeds of m when requested`(
        numOfBreedsToFilter: Int,
        totalBreeds: Int,
    ) {
        val existingBreeds = BreedMother.randoms(totalBreeds)
        val useCase = SearchBreedsUseCase(breedRepository)
        val filter = BreadFilter(numOfBreedsToFilter)
        breedsClientGiven.thereAreBreeds(existingBreeds)

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

    @Test
    fun `throws invalid number of breeds exception when requested less than 1 breeds`() {
        val useCase = SearchBreedsUseCase(breedRepository)

        assertThrows<InvalidNumOfBreedsException> {
            useCase.search(BreadFilter(0))
        }
    }

    @AfterEach
    fun clean() {
        breedsClientGiven.cleanMock()
    }
}
