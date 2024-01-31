package org.adrianliz.playingwithcats.questions.domain

import org.adrianliz.playingwithcats.breeds.domain.Breed
import org.adrianliz.playingwithcats.cats.domain.Cat

data class Question(val breeds: List<Breed>, val cat: Cat)
