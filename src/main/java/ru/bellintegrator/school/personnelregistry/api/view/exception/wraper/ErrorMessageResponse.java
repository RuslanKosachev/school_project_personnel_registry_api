package ru.bellintegrator.school.personnelregistry.api.view.exception.wraper;

/**
 * Класс-view для сообщением об ошибках от сервера
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
