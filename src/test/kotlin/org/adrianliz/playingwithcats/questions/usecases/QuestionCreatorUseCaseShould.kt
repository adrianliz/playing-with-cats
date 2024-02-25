package org.adrianliz.playingwithcats.questions.usecases

import io.mockk.mockk
import io.mockk.verify
import org.adrianliz.playingwithcats.breeds.domain.BreedChooser
import org.adrianliz.playingwithcats.breeds.givens.BreedChooserGiven
import org.adrianliz.playingwithcats.breeds.givens.BreedsClientGiven
import org.adrianliz.playingwithcats.breeds.infrastructure.repository.TheCatApiBreedRepository
import org.adrianliz.playingwithcats.breeds.infrastructure.thecatapi.BreedsClient
import org.adrianliz.playingwithcats.breeds.mother.BreedMother
import org.adrianliz.playingwithcats.breeds.usecases.SearchBreedsUseCase
import org.adrianliz.playingwithcats.cats.domain.CatFilter
import org.adrianliz.playingwithcats.cats.givens.ImagesClientGiven
import org.adrianliz.playingwithcats.cats.infrastructure.repository.TheCatApiRepository
import org.adrianliz.playingwithcats.cats.infrastructure.thecatapi.ImagesClient
import org.adrianliz.playingwithcats.cats.mother.CatMother
import org.adrianliz.playingwithcats.cats.usecases.SearchCatsUseCase
import org.adrianliz.playingwithcats.questions.domain.QuestionRepository
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test
import kotlin.test.assertEquals

class QuestionCreatorUseCaseShould {
    private val breedsClient = mockk<BreedsClient>()
    private val imagesClient = mockk<ImagesClient>()
    private val breedChooser = mockk<BreedChooser>()
    private val questionRepository = mockk<QuestionRepository>(relaxUnitFun = true)
    private val breedsClientGiven = BreedsClientGiven(breedsClient)
    private val imagesClientGiven = ImagesClientGiven(imagesClient)
    private val breedChooserGiven = BreedChooserGiven(breedChooser)
    private val breedRepository = TheCatApiBreedRepository(breedsClient)
    private val catRepository = TheCatApiRepository(imagesClient)
    private val searchBreedsUseCase = SearchBreedsUseCase(breedRepository)
    private val searchCatsUseCase = SearchCatsUseCase(catRepository)

    @Test
    fun `create a question with 3 breeds and a cat that matches the first breed`() {
        val existingBreeds = BreedMother.randoms()
        val firstBreed = existingBreeds.first()
        val cat = CatMother.randomWithBreed(firstBreed)
        val filter = CatFilter(cat.breed)
        val useCase = QuestionCreatorUseCase(searchBreedsUseCase, searchCatsUseCase, breedChooser, questionRepository)
        breedsClientGiven.thereAreBreeds(existingBreeds)
        imagesClientGiven.thereIsACatMatching(filter, cat)
        breedChooserGiven.choosesFirstBreed(existingBreeds)

        val question = useCase.create()

        assertEquals(question.breeds.size, 3)
        assertEquals(question.cat, cat)
        verify { questionRepository.save(question) }
    }

    @BeforeEach
    fun clean() {
        breedsClientGiven.cleanMock()
        imagesClientGiven.cleanMock()
        breedChooserGiven.cleanMock()
    }
}
