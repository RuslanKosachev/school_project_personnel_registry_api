package ru.bellintegrator.school.personnelregistry.api.view.exception;

/**
 * Класс с сообщением об ошибках в ответе
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
