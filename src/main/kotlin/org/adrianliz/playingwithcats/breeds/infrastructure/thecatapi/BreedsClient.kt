package org.adrianliz.playingwithcats.breeds.infrastructure.thecatapi

import org.adrianliz.playingwithcats.breeds.domain.Breed
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class BreedsClient(
    @Value("\${theCatApi.baseUri}") baseUri: String,
    @Value("\${theCatApi.apiKey}") apiKey: String
) {

    private val client = RestClient.builder().baseUrl(baseUri)
        .defaultHeader("Content-Type", "application/json")
        .defaultHeader("x-api-key", apiKey).build()

    fun getAllBreeds(): List<Breed> {
        val breedsResponse = client.get()
            .uri("/breeds")
            .retrieve()
            .body(object : ParameterizedTypeReference<List<BreedResponse>>() {}) ?: emptyList()

        return breedsResponse.map { Breed(it.name) }
    }
}
