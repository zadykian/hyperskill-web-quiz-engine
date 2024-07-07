package engine.quiz

import arrow.core.NonEmptyList
import engine.NonEmptyString

@JvmInline
value class QuizId(val id: UInt)

typealias AnswerOption = NonEmptyString

sealed interface QuizAnswerOptions {
    fun asList(): NonEmptyList<AnswerOption>
}

class SingleCorrect(
    val correctAnswer: AnswerOption,
    val otherOptions: List<AnswerOption>
) : QuizAnswerOptions {
    override fun asList() = NonEmptyList(correctAnswer, otherOptions)
}

data class Quiz(
    val id: QuizId? = null,
    val title: NonEmptyString,
    val text: NonEmptyString,
    val options: QuizAnswerOptions,
)

