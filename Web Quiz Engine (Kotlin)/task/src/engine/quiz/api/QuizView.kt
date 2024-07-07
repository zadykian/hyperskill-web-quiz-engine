package engine.quiz.api

import engine.NonEmptyString
import engine.quiz.Quiz

data class QuizView(
    val id: UInt,
    val title: NonEmptyString,
    val text: NonEmptyString,
    val options: List<NonEmptyString>
)

fun Quiz.asView() = QuizView(
    id = id?.id ?: throw IllegalArgumentException("Quiz should have an id assigned!"),
    title = title,
    text = text,
    options = this.options.asList()
)
