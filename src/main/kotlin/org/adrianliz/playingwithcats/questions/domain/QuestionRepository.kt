package org.adrianliz.playingwithcats.questions.domain

interface QuestionRepository {
    fun save(question: Question)
}
