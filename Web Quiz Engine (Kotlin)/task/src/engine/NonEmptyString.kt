package engine

import arrow.core.Either
import arrow.core.left
import arrow.core.raise.ensure
import arrow.core.right

@JvmInline
value class NonEmptyString private constructor(private val value: String) :
    CharSequence by value,
    Comparable<NonEmptyString> {

    override fun compareTo(other: NonEmptyString) = value.compareTo(other.value)

    override fun toString() = value

    companion object {
        operator fun invoke(value: String): Either<Error.InvalidInput, NonEmptyString> =
            if (value.isBlank()) Error.InvalidInput("Value cannot be empty").left()
            else NonEmptyString(value).right()
    }
}

context(RaiseInvalidInput)
fun String.asNonEmpty(fieldName: String? = null): NonEmptyString {
    ensure(this.isNotBlank()) {
        val message = if (fieldName.isNullOrBlank()) "Value cannot be empty" else "Value of $fieldName cannot be empty"
        Error.InvalidInput(message)
    }

    return NonEmptyString(this).bind()
}