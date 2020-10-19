package br.com.ogawadev.bluetasksbackendgroovy.infrastructure.web

import org.springframework.data.rest.core.RepositoryConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class WebRequestExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    RestResponseError handleException(RepositoryConstraintViolationException e) {
        return RestResponseError.fromValidationError(e.getErrors())
    }
}
