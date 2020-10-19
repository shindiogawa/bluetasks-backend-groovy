package br.com.ogawadev.bluetasksbackendgroovy.infrastructure.web

import org.springframework.validation.Errors
import org.springframework.validation.ObjectError

class RestResponseError {

    String error

    private RestResponseError() {}

    static RestResponseError fromValidationError(Errors errors) {
        RestResponseError responseError = new RestResponseError()
        StringBuilder sb = new StringBuilder()

        for(ObjectError error: errors.getAllErrors()) {
            sb.append(error.getDefaultMessage()).append(". ")
        }

        responseError.error = sb.toString()
        return responseError
    }

    static RestResponseError fromMessage(String message) {
        RestResponseError resp = new RestResponseError()
        resp.error = message
        return resp
    }

}
