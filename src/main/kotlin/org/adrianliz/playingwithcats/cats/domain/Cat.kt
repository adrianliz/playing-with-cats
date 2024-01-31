package org.adrianliz.playingwithcats.cats.domain

import org.adrianliz.playingwithcats.breeds.domain.Breed

data class Cat(val id: String, val imageUrl: String, val breed: Breed)
