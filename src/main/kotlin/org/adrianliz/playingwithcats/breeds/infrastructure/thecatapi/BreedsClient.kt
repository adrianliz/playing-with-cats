package org.adrianliz.playingwithcats.breeds.infrastructure.thecatapi

import com.hazelcast.map.IMap
import org.adrianliz.playingwithcats.breeds.domain.Breed
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class BreedsClient(
    @Value("\${theCatApi.baseUri}") private val baseUri: String,
    @Value("\${theCatApi.apiKey}") private val apiKey: String,
    private val cache: IMap<String, Breed>,
) {
    private val client =
        RestClient.builder().baseUrl(baseUri)
            .defaultHeader("Content-Type", "application/json")
            .defaultHeader("x-api-key", apiKey).build()

    fun getAllBreeds(): List<Breed> {
        val cachedBreeds = cache.values
        if (cachedBreeds.isNotEmpty()) {
            return cachedBreeds.toList()
        }

        val breedsResponse =
            client.get()
                .uri("/breeds")
                .retrieve()
                .body(object : ParameterizedTypeReference<List<BreedResponse>>() {}) ?: emptyList()

        return breedsResponse.filter { it.wikipediaUrl != null }.map { Breed(it.id, it.name, it.wikipediaUrl!!) }
            .onEach { breed -> cache.set(breed.id, breed) }
    }
}
