package ru.bellintegrator.school.personnelregistry.api.view.exception;

import ru.bellintegrator.school.personnelregistry.api.view.UserView;

/**
 * Сообщения ошибок при отсуцтвии обязательных полей
 */
public enum ErrorCode {

    // UserView
    USER_NULL_ID("отсуцтвует идентификатор сотрудника"),
    USER_NULL_FIRST_NAME("отсуцтвует имя сотрудника"),
    USER_NULL_POSITION("отсуцтвует должность сотрудника"),

    // OfficeView
    OFFICE_NULL_ID("отсуцтвует идентификатор офиса"),
    OFFICE_NULL_NAME("отсуцтвует названия офиса"),
    OFFICE_NULL_ADDRESS("отсуцтвует адрес офиса"),

    // organizationView
    ORGANIZATION_NULL_ID("отсуцтвует идентификатор организации"),
    ORGANIZATION_NULL_NAME("отсуцтвует название организации"),
    ORGANIZATION_NULL_FULL_NAME("отсуцтвует полное название организации"),
    ORGANIZATION_NULL_ADDRESS("отсуцтвует адрес организации"),
    ORGANIZATION_NULL_INN("отсуцтвует идентификационный номер налогоплательщика организации"),
    ORGANIZATION_NULL_KPP("отсуцтвует код причины постановки на учет в налоговых органах");

    private String errorMessage;

    private ErrorCode(String errorString) {
        this.errorMessage = errorString;
    }

    public String getErrorString() {
        return errorMessage;
    }
}