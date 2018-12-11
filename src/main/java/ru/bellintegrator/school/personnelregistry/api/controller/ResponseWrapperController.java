package ru.bellintegrator.school.personnelregistry.api.controller;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import ru.bellintegrator.school.personnelregistry.api.view.exception.wraper.ErrorMessageResponse;
import ru.bellintegrator.school.personnelregistry.api.view.wrapper.Data;
import ru.bellintegrator.school.personnelregistry.api.view.wrapper.Result;
import static ru.bellintegrator.school.personnelregistry.api.view.wrapper.Result.RESULT_FALSE;
import static ru.bellintegrator.school.personnelregistry.api.view.wrapper.Result.RESULT_TRUE;

/**
 * Обрабатывает ответ сервера api
 */
@RestControllerAdvice(basePackages = "ru.bellintegrator.school.personnelregistry.api.controller")
public class ResponseWrapperController implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ErrorMessageResponse) {
            return body;
        } else if (body instanceof Boolean) {
            if ((Boolean) body == true) {
                return new Result(RESULT_TRUE);
            } else {
                return new Result(RESULT_FALSE);
            }
        } else {
            return new Data(body);
        }
    }
}