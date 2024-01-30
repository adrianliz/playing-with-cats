package org.adrianliz.playingwithcats.cats.domain

interface CatRepository {
    fun search(filter: CatFilter): List<Cat>
}
