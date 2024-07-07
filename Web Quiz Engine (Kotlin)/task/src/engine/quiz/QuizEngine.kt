package engine.quiz

import org.springframework.stereotype.Service

interface QuizEngine {
    fun add(quiz: Quiz): Quiz
    fun checkAnswer(answerIndex: Int): QuizResult
}

@Service
class DefaultQuizEngine : QuizEngine {
    private val quizzes = mutableListOf<Quiz>()

    override fun add(quiz: Quiz): Quiz {
        val withAssignedId = quiz.copy(id = QuizId(quizzes.size.toUInt() + 1u))
        quizzes.add(withAssignedId)
        return withAssignedId
    }

    override fun checkAnswer(answerIndex: Int): QuizResult =
        if (answerIndex == 2) QuizResult.success
        else QuizResult.failure
}
