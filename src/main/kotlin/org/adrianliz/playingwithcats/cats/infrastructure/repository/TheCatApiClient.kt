package org.adrianliz.playingwithcats.cats.infrastructure.repository

import org.adrianliz.playingwithcats.cats.domain.Cat
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class TheCatApiClient(
    @Value("\${theCatApi.baseUri}") baseUri: String,
    @Value("\${theCatApi.apiKey}") apiKey: String
) {
    private val client = RestClient.builder().baseUrl(baseUri)
        .defaultHeader("Content-Type", "application/json")
        .defaultHeader("x-api-key", apiKey).build()

    fun getAllCats(page: Int?, limit: Int?): List<Cat> {
        val cats = client.get()
            .uri("/images/search?page=$page&limit=$limit&order=DESC&has_breeds=true")
            .retrieve()
            .body(object : ParameterizedTypeReference<List<TheCatApiSearchResponse>>() {}) ?: emptyList()

        return cats.map { Cat(it.breeds.firstOrNull()?.name ?: "Unknown") }
    }
}
