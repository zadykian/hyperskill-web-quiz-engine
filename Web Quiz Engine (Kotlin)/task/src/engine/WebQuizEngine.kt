package engine

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebQuizEngine

fun main(args: Array<String>) {
    runApplication<WebQuizEngine>(*args)
}
