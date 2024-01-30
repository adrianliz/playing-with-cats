package org.adrianliz.playingwithcats.breeds.delivery

import org.adrianliz.playingwithcats.breeds.domain.InvalidNumOfBreedsException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class SearchBreedsExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(value = [InvalidNumOfBreedsException::class])
    protected fun handle(
        ex: InvalidNumOfBreedsException
    ) = run { ResponseStatusException(HttpStatus.BAD_REQUEST, ex.message, ex) }
}
