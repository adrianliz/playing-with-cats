package org.adrianliz.playingwithcats.questions.usecases

import io.mockk.every
import io.mockk.mockk
import org.adrianliz.playingwithcats.breeds.domain.Breed
import org.adrianliz.playingwithcats.breeds.infrastructure.repository.TheCatApiBreedRepository
import org.adrianliz.playingwithcats.breeds.infrastructure.thecatapi.BreedsClient
import org.adrianliz.playingwithcats.breeds.mother.BreedMother
import org.adrianliz.playingwithcats.breeds.usecases.SearchBreedsUseCase
import org.adrianliz.playingwithcats.cats.domain.Cat
import org.adrianliz.playingwithcats.cats.domain.CatFilter
import org.adrianliz.playingwithcats.cats.infrastructure.repository.TheCatApiRepository
import org.adrianliz.playingwithcats.cats.infrastructure.thecatapi.ImagesClient
import org.adrianliz.playingwithcats.cats.usecases.SearchCatsUseCase
import org.adrianliz.playingwithcats.questions.domain.BreedChooser
import kotlin.test.Test
import kotlin.test.assertEquals

class QuestionCreatorUseCaseShould {
    private val breedsClient = mockk<BreedsClient>()
    private val imagesClient = mockk<ImagesClient>()
    private val breedChooser = mockk<BreedChooser>()
    private val breedRepository = TheCatApiBreedRepository(breedsClient)
    private val catRepository = TheCatApiRepository(imagesClient)
    private val searchBreedsUseCase = SearchBreedsUseCase(breedRepository)
    private val searchCatsUseCase = SearchCatsUseCase(catRepository)

    private fun givenThereAreBreeds(breeds: List<Breed>) {
        every {
            breedsClient.getAllBreeds()
        } returns breeds
    }

    private fun givenThereIsACatMatching(filter: CatFilter, cat: Cat) {
        every {
            imagesClient.getCatsMatching(filter)
        } returns listOf(cat)
    }

    private fun givenChooserChoosesFirstBreed(breeds: List<Breed>) {
        every {
            val matcher = match<List<Breed>> { filteredBreeds ->
                breeds.containsAll(filteredBreeds)
            }
            breedChooser.chooseOne(matcher)
        } returns breeds[0]
    }

    @Test
    fun `create a question with 3 breeds and a cat that matches the first breed`() {
        val existingBreeds = BreedMother.randoms()
        val firstBreed = existingBreeds[0]
        val cat = Cat(firstBreed.id, firstBreed.name)
        val filter = CatFilter(cat.breedId)
        val useCase = QuestionCreatorUseCase(searchBreedsUseCase, searchCatsUseCase, breedChooser)
        givenThereAreBreeds(existingBreeds)
        givenThereIsACatMatching(filter, cat)
        givenChooserChoosesFirstBreed(existingBreeds)

        val question = useCase.create()

        assertEquals(question.breeds.size, 3)
        assertEquals(question.cat, cat)
    }
}