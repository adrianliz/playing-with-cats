package org.adrianliz.playingwithcats.questions.usecases

import org.adrianliz.playingwithcats.breeds.domain.BreadFilter
import org.adrianliz.playingwithcats.breeds.domain.BreedChooser
import org.adrianliz.playingwithcats.breeds.usecases.SearchBreedsUseCase
import org.adrianliz.playingwithcats.cats.domain.CatFilter
import org.adrianliz.playingwithcats.cats.usecases.SearchCatsUseCase
import org.adrianliz.playingwithcats.questions.domain.Question
import org.adrianliz.playingwithcats.questions.domain.QuestionRepository
import org.springframework.stereotype.Service

@Service
class QuestionCreatorUseCase(
    val searchBreedsUseCase: SearchBreedsUseCase,
    val searchCatsUseCase: SearchCatsUseCase,
    val breedChooser: BreedChooser,
    val questionRepository: QuestionRepository
) {

    fun create(): Question {
        val breeds = searchBreedsUseCase.search(BreadFilter(3))
        val questionBreed = breedChooser.chooseOne(breeds)
        val cat = searchCatsUseCase.search(CatFilter(questionBreed.id))

        val question = Question(breeds = breeds, cat = cat.random())
        questionRepository.save(question)

        return question
    }
}
