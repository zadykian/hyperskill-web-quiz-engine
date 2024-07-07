package engine.quiz.api

import arrow.core.raise.either
import engine.mapToResponse
import engine.quiz.QuizEngine
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/quizzes", produces = [MediaType.APPLICATION_JSON_VALUE])
class QuizController(@Autowired private val engine: QuizEngine) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody quizCreateDto: QuizCreateDto) =
        either {
            val quizToAdd = quizCreateDto.asQuiz()
            val added = engine.add(quizToAdd)
            added.asView()
        }.mapToResponse(onRight = HttpStatus.CREATED)
}
