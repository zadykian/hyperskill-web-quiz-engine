package engine

import arrow.core.Either
import arrow.core.raise.Raise
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity

typealias RaiseInvalidInput = Raise<Error.InvalidInput>

sealed class Error(displayText: String) {
    var displayText: String = displayText
        private set

    override fun toString() = displayText

    class InvalidInput(displayText: String) : Error(displayText)

    companion object {
        fun <TError : Error> TError.withSuffix(suffix: String) =
            apply { displayText = "$displayText $suffix" }
    }
}

fun <TRight> Either<Error.InvalidInput, TRight>.mapToResponse(onRight: HttpStatusCode = HttpStatus.OK)
        : ResponseEntity<Any> =
    when (this) {
        is Either.Right -> ResponseEntity
            .status(onRight)
            .contentType(MediaType.APPLICATION_JSON)
            .body(this.value)

        is Either.Left -> ResponseEntity
            .badRequest()
            .body(object {
                @Suppress("unused")
                val errorText = this@mapToResponse.value.displayText
            })
    }
