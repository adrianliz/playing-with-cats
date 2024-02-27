package org.adrianliz.playingwithcats.cats.givens

import io.mockk.every
import io.mockk.unmockkObject
import org.adrianliz.playingwithcats.cats.domain.Cat
import org.adrianliz.playingwithcats.cats.domain.CatFilter
import org.adrianliz.playingwithcats.cats.infrastructure.thecatapi.ImagesClient

class ImagesClientGiven(private val imagesClient: ImagesClient) {
    fun thereIsACatMatching(
        filter: CatFilter,
        cat: Cat,
    ) {
        every {
            imagesClient.getCatsMatching(filter)
        } returns listOf(cat)
    }

    fun cleanMock() {
        unmockkObject(imagesClient)
    }
}
