package ru.bellintegrator.school.personnelregistry.api.controller.exception;

import ru.bellintegrator.school.personnelregistry.api.error.wraper.ErrorMessageResponse;
import ru.bellintegrator.school.personnelregistry.api.view.exception.ViewException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Обработывает исключения контроллеров api
 */
@RestControllerAdvice(basePackages = "ru.bellintegrator.school.personnelregistry.api.controller")
public class ExceptionHandlerController {

    @ExceptionHandler(ViewException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    protected ErrorMessageResponse handleException(ViewException e) {
        return new ErrorMessageResponse(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorMessageResponse handleException(Exception e) {
        return new ErrorMessageResponse(e.getMessage());
    }
}
