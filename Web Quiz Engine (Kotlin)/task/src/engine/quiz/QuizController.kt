package engine.quiz

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/quiz", produces = [MediaType.APPLICATION_JSON_VALUE])
class QuizController(@Autowired private val engine: QuizEngine) {
    @GetMapping
    fun get() = engine.getNew()

    @PostMapping("/{answer}")
    fun answer(@PathVariable answer: Int) = engine.checkAnswer(answer)
}
