package engine.quiz

class QuizResult private constructor(val success: Boolean, val feedback: String) {
    companion object {
        val success = QuizResult(true, "Congratulations, you're right!")
        val failure = QuizResult(false, "Wrong answer! Please, try again.")
    }
}
