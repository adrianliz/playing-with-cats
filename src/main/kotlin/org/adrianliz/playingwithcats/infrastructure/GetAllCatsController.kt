package org.adrianliz.playingwithcats.infrastructure

import org.adrianliz.playingwithcats.domain.Cat
import org.adrianliz.playingwithcats.domain.CatRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GetAllCatsController(private val catRepository: CatRepository) {

  @GetMapping("/cats")
  fun getAllCats(): List<Cat> {
    return catRepository.findAll()
  }
}
