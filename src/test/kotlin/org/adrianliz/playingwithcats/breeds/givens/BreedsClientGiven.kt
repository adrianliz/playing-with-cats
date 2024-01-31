package org.adrianliz.playingwithcats.breeds.givens

import io.mockk.every
import io.mockk.unmockkObject
import org.adrianliz.playingwithcats.breeds.domain.Breed
import org.adrianliz.playingwithcats.breeds.infrastructure.thecatapi.BreedsClient

class BreedsClientGiven(private val breedsClient: BreedsClient) {
    fun thereAreBreeds(breeds: List<Breed>) {
        every {
            breedsClient.getAllBreeds()
        } returns breeds
    }

    fun cleanMock() {
        unmockkObject(breedsClient)
    }
}
