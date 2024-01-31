package org.adrianliz.playingwithcats.questions.usecases

import org.adrianliz.playingwithcats.breeds.domain.BreadFilter
import org.adrianliz.playingwithcats.breeds.usecases.SearchBreedsUseCase
import org.adrianliz.playingwithcats.cats.domain.CatFilter
import org.adrianliz.playingwithcats.cats.usecases.SearchCatsUseCase
import org.adrianliz.playingwithcats.questions.domain.BreedChooser
import org.adrianliz.playingwithcats.questions.domain.Question
import org.springframework.stereotype.Service

@Service
class QuestionCreatorUseCase(
    val searchBreedsUseCase: SearchBreedsUseCase,
    val searchCatsUseCase: SearchCatsUseCase
) {

    fun create(breedChooser: BreedChooser): Question {
        val breeds = searchBreedsUseCase.search(BreadFilter(3))
        val questionBreed = breedChooser.chooseOne(breeds)
        val cat = searchCatsUseCase.search(CatFilter(questionBreed.id))

        return Question(breeds, cat.random())
    }
}
