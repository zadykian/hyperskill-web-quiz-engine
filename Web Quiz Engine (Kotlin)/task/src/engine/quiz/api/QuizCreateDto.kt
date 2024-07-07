package engine.quiz.api

import arrow.core.raise.ensure
import engine.Error
import engine.Error.Companion.withSuffix
import engine.RaiseInvalidInput
import engine.asNonEmpty
import engine.quiz.AnswerOption
import engine.quiz.Quiz
import engine.quiz.SingleCorrect

@Suppress("MemberVisibilityCanBePrivate")
class QuizCreateDto(
    val title: String,
    val text: String,
    val options: List<String>,
    val answer: Int
) {
    context(RaiseInvalidInput)
    fun asQuiz(): Quiz {
        ensure(options.isNotEmpty()) {
            Error.InvalidInput("Quiz ${QuizCreateDto::options.name} list cannot be empty")
        }

        val answerOptions = options
            .mapIndexed { index, opt -> AnswerOption(opt).mapLeft { err -> err.withSuffix("(option at index $index)") } }
            .bindAll()

        val correctAnswer = answerOptions.getOrNull(answer)
            ?: raise(Error.InvalidInput("Correct answer index should belong to range ${answerOptions.indices}"))

        return Quiz(
            title = title.asNonEmpty(QuizCreateDto::title.name),
            text = text.asNonEmpty(QuizCreateDto::text.name),
            options = SingleCorrect(correctAnswer, answerOptions.minus(correctAnswer))
        )
    }
}
