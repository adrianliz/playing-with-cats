package org.adrianliz.playingwithcats.infrastructure

import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import org.adrianliz.playingwithcats.domain.Cat
import org.adrianliz.playingwithcats.domain.CatRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.context.annotation.Bean
import org.springframework.core.ParameterizedTypeReference
import org.springframework.web.client.RestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GetAllCatsControllerShould(
  @LocalServerPort private val apiPort: Int
) {

  private val client = RestClient.builder().baseUrl("http://localhost:$apiPort").build()
  @Autowired
  private lateinit var catRepository: CatRepository

  @TestConfiguration
  class GetAllCatsControllerTestConfiguration {
    @Bean
    fun catRepository() = mockk<CatRepository>()

    @Bean
    fun getAllCatsController(catRepository: CatRepository) = GetAllCatsController(catRepository)
  }


  @BeforeEach
  fun setUp() {
    every {
      catRepository.findAll()
    } returns listOf(
      Cat("Garfield", 40),
      Cat("Felix", 100)
    )
  }

  @Test
  fun `return all cats`() {
    val cats = client.get()
      .uri("/cats")
      .retrieve()
      .body(object : ParameterizedTypeReference<List<Cat>>() {});

    assertThat(cats).containsExactly(
      Cat("Garfield", 40),
      Cat("Felix", 100)
    )
  }

  @AfterEach
  fun tearDown() {
    unmockkAll()
  }
}
