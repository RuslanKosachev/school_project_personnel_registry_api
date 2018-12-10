package ru.bellintegrator.school.personnelregistry.api.controller.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bellintegrator.school.personnelregistry.api.view.exception.ErrorMessageResponse;
import ru.bellintegrator.school.personnelregistry.api.view.exception.ViewException;

/**
 * Обработывает исключения контроллеров api
 */
@RestControllerAdvice(basePackages = "ru.bellintegrator.school.personnelregistry.api.controller")
public class ExceptionHandlerController {

    @ExceptionHandler(ViewException.class)
    protected ErrorMessageResponse handleException(ViewException e) {
        return new ErrorMessageResponse(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    protected ErrorMessageResponse handleException(Exception e) {
        return new ErrorMessageResponse(e.getMessage());
    }
}
