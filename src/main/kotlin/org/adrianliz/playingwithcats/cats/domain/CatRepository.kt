package org.adrianliz.playingwithcats.cats.domain

interface CatRepository {
    fun findAll(): List<Cat>
}
