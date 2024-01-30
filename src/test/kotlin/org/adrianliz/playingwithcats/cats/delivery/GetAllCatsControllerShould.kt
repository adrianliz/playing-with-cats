package org.adrianliz.playingwithcats.cats.delivery

import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import org.adrianliz.playingwithcats.cats.domain.Cat
import org.adrianliz.playingwithcats.cats.domain.CatRepository
import org.adrianliz.playingwithcats.cats.infrastructure.repository.TheCatApiRepository
import org.adrianliz.playingwithcats.cats.infrastructure.thecatapi.ImagesClient
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.core.ParameterizedTypeReference
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.web.client.RestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GetAllCatsControllerShould(
    @LocalServerPort private val apiPort: Int
) {
    private val client = RestClient.builder().baseUrl("http://localhost:$apiPort").build()

    @Autowired
    private lateinit var catApiClient: ImagesClient

    companion object {
        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("theCatApi.baseUri") { "https://api.thecatapi.com/v1" }
            registry.add("theCatApi.apiKey") { "fake-api-key" }
        }
    }

    @TestConfiguration
    class GetAllCatsControllerTestConfiguration {
        @Bean
        @Primary
        fun imagesClient() = mockk<ImagesClient>()

        @Bean
        fun catRepository(imagesClient: ImagesClient) = TheCatApiRepository(imagesClient)

        @Bean
        fun getAllCatsController(catRepository: CatRepository) = GetAllCatsController(catRepository)
    }


    @BeforeEach
    fun setUp() {
        every {
            catApiClient.getAllCats(1, 10)
        } returns listOf(
            Cat("Garfield"),
            Cat("Felix")
        )
    }

    @Test
    fun `return all cats`() {
        val getAllCatsResponse = client.get()
            .uri("/cats")
            .retrieve()
            .body(object : ParameterizedTypeReference<GetAllCatsResponse>() {})

        assertThat(getAllCatsResponse).isNotNull
        assertThat(getAllCatsResponse?.cats).isNotNull
        assertThat(getAllCatsResponse?.cats).hasSize(2)
        assertThat(
            getAllCatsResponse?.cats?.containsAll(
                listOf(
                    GetCatResponse("Garfield"),
                    GetCatResponse("Felix")
                )
            )
        ).isTrue()
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }
}
