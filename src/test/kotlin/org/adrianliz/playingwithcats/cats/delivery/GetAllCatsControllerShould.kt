package org.adrianliz.playingwithcats.cats.delivery

import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import org.adrianliz.playingwithcats.cats.domain.Cat
import org.adrianliz.playingwithcats.cats.domain.CatRepository
import org.adrianliz.playingwithcats.cats.infrastructure.repository.TheCatApiClient
import org.adrianliz.playingwithcats.cats.infrastructure.repository.TheCatApiRepository
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
import org.springframework.web.client.RestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GetAllCatsControllerShould(
    @LocalServerPort private val apiPort: Int
) {
    private val client = RestClient.builder().baseUrl("http://localhost:$apiPort").build()

    @Autowired
    private lateinit var catApiClient: TheCatApiClient


    @TestConfiguration
    class GetAllCatsControllerTestConfiguration {
        @Bean
        @Primary
        fun mockCatApiClient() = mockk<TheCatApiClient>()

        @Bean
        fun catRepository(mockCatApiClient: TheCatApiClient) = TheCatApiRepository(mockCatApiClient)

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
