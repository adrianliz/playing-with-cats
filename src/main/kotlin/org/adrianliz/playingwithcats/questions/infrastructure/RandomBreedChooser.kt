package org.adrianliz.playingwithcats.questions.infrastructure

import org.adrianliz.playingwithcats.breeds.domain.Breed
import org.adrianliz.playingwithcats.questions.domain.BreedChooser
import org.springframework.stereotype.Service

@Service
class RandomBreedChooser : BreedChooser {
    override fun chooseOne(breeds: List<Breed>): Breed {
        return breeds.random()
    }
}
