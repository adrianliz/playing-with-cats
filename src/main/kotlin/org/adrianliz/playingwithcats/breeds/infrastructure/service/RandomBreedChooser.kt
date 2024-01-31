package org.adrianliz.playingwithcats.breeds.infrastructure.service

import org.adrianliz.playingwithcats.breeds.domain.Breed
import org.adrianliz.playingwithcats.breeds.domain.BreedChooser
import org.springframework.stereotype.Service

@Service
class RandomBreedChooser : BreedChooser {
    override fun chooseOne(breeds: List<Breed>): Breed {
        return breeds.random()
    }
}
