package ru.bellintegrator.school.personnelregistry.api.error.wraper;

/**
 * Класс-view для сообщением об ошибках от сервера
 * Используется с целью оборачивания сообщения описания ошибки от контроллера
 * в свойство “error”:{...} при сериализации в json
 *
 *  Должен содержать описания ошибки в поле error
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
