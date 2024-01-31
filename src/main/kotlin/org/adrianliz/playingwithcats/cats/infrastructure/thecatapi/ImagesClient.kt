package org.adrianliz.playingwithcats.cats.infrastructure.thecatapi

import org.adrianliz.playingwithcats.breeds.domain.Breed
import org.adrianliz.playingwithcats.cats.domain.Cat
import org.adrianliz.playingwithcats.cats.domain.CatFilter
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class ImagesClient(
    @Value("\${theCatApi.baseUri}") private val baseUri: String,
    @Value("\${theCatApi.apiKey}") private val apiKey: String
) {
    private val client = RestClient.builder().baseUrl(baseUri)
        .defaultHeader("Content-Type", "application/json")
        .defaultHeader("x-api-key", apiKey).build()

    fun getCatsMatching(filter: CatFilter): List<Cat> {
        val searchImagesResponse = client.get()
            .uri("/images/search?breed_ids=${filter.breedId}&page=0&limit=1&order=RAND&has_breeds=true")
            .retrieve()
            .body(object : ParameterizedTypeReference<List<SearchImageResponse>>() {}) ?: emptyList()

        return searchImagesResponse.map {
            val breed = Breed(it.breeds.first().id, it.breeds.first().name)

            Cat(it.id, it.url, breed)
        }
    }
}
