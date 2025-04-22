package com.noxyee.note.exception

import com.noxyee.note.exception.model.ErrorResponse
import com.noxyee.note.exception.model.NoteNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(NoteNotFoundException::class)
    fun handleNoteNotFoundException(e: NoteNotFoundException): ResponseEntity<String> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(e.message)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val errors = HashMap<String, String?>()
        e.bindingResult.allErrors.forEach {
            val field = (it as FieldError).field
            val error = it.defaultMessage
            errors[field] = error
        }
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(errors))
    }
}