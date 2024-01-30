package org.adrianliz.playingwithcats.cats.infrastructure.thecatapi

data class SearchImageResponse(
    val id: String,
    val url: String,
    val width: Int,
    val height: Int,
    val breeds: List<SearchImageBreedResponse>,
)
