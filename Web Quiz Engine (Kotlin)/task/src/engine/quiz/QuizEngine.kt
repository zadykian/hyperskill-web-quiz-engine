package engine.quiz

import org.springframework.stereotype.Service

interface QuizEngine {
    fun getNew(): Quiz
    fun checkAnswer(answerIndex: Int): QuizResult
}

@Service
class SingleQuizEngine : QuizEngine {
    private val quiz = Quiz(
        title = "The Java Logo",
        text = "What is depicted on the Java logo?",
        options = listOf("Robot", "Tea leaf", "Cup of coffee", "Bug")
    )

    override fun getNew() = quiz

    override fun checkAnswer(answerIndex: Int): QuizResult =
        if (answerIndex == 2) QuizResult.success
        else QuizResult.failure
}
