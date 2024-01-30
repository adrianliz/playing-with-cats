package org.adrianliz.playingwithcats.cats.infrastructure.repository

data class TheCatApiSearchResponse(
    val id: String,
    val url: String,
    val width: Int,
    val height: Int,
    val breeds: List<TheCatApiBreedResponse>,
)
