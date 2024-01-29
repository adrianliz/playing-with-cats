package org.adrianliz.playingwithcats

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PlayingWithCatsApplication

fun main(args: Array<String>) {
    runApplication<PlayingWithCatsApplication>(*args)
}
