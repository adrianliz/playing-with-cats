package org.adrianliz.playingwithcats.cats.delivery

import org.adrianliz.playingwithcats.cats.domain.CatRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GetAllCatsController(private val catRepository: CatRepository) {

    @GetMapping("/cats")
    fun getAllCats(): GetAllCatsResponse {
        return catRepository.findAll()
            .map { GetCatResponse(it.breed) }.let { GetAllCatsResponse(it) }
    }
}
