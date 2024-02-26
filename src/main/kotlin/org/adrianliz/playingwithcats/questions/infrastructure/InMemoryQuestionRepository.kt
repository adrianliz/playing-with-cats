import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.adrianliz.playingwithcats.questions.domain.Question
import org.adrianliz.playingwithcats.questions.domain.QuestionRepository
import java.util.Optional
import java.util.UUID

class InMemoryQuestionRepository : QuestionRepository {
    private val questions = mutableMapOf<UUID, Question>()
    private val scope = CoroutineScope(Dispatchers.Default)

    override fun save(question: Question) {
        questions[question.id] = question
        scheduleDeletion(question.id)
    }

    override fun findById(questionId: UUID): Optional<Question> {
        return Optional.ofNullable(questions[questionId])
    }

    private fun scheduleDeletion(questionId: UUID) {
        scope.launch {
            val tenMinutesInMillis = 10 * 60 * 1000L
            delay(tenMinutesInMillis)
            questions.remove(questionId)
        }
    }
}
