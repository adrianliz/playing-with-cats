package org.adrianliz.playingwithcats.breeds.infrastructure.thecatapi

import com.fasterxml.jackson.annotation.JsonProperty

class BreedResponse(
    val id: String,
    val name: String,
    @JsonProperty(value = "wikipedia_url") val wikipediaUrl: String? = null,
)
