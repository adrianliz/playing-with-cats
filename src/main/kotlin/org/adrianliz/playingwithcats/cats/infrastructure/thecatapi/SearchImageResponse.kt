package org.adrianliz.playingwithcats.cats.infrastructure.thecatapi

data class SearchImageResponse(
    val id: String,
    val url: String,
    val breeds: List<SearchImageBreedResponse>,
)
