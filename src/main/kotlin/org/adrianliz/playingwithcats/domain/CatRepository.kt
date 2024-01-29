package org.adrianliz.playingwithcats.domain

interface CatRepository {
  fun findAll(): List<Cat>
}
