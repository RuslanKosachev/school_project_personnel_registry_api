package ru.bellintegrator.school.personnelregistry.api.view.exception;

/**
 * Класс-view с сообщением об ошибках от сервера в ответе запроса
 */
public class ErrorMessageResponse {
    private final String error;

    public ErrorMessageResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
