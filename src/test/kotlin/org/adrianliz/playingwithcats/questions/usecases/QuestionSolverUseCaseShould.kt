package org.adrianliz.playingwithcats.questions.usecases

import io.mockk.mockk
import io.mockk.verify
import org.adrianliz.playingwithcats.breeds.mother.BreedMother
import org.adrianliz.playingwithcats.questions.domain.QuestionAnswer
import org.adrianliz.playingwithcats.questions.domain.QuestionRepository
import org.adrianliz.playingwithcats.questions.domain.QuestionStatus
import org.adrianliz.playingwithcats.questions.givens.QuestionRepositoryGiven
import org.adrianliz.playingwithcats.questions.mother.QuestionMother
import kotlin.test.Test
import kotlin.test.assertEquals

class QuestionSolverUseCaseShould {
    private val questionRepository = mockk<QuestionRepository>(relaxUnitFun = true)
    private val questionRepositoryGiven = QuestionRepositoryGiven(questionRepository)

    @Test
    fun `change question status to solved when the answer is correct`() {
        val expectedBreed = BreedMother.random()
        val question = QuestionMother.withCatMatchingBreed(expectedBreed)
        val answer = QuestionAnswer(question.id, expectedBreed.id)
        val useCase = QuestionSolverUseCase(questionRepository)
        questionRepositoryGiven.thereIsQuestion(question)

        useCase.solve(answer)

        assertEquals(question.status, QuestionStatus.SOLVED)
        verify {
            questionRepository.save(question)
        }
    }
}
