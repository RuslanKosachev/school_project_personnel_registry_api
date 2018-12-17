package ru.bellintegrator.school.personnelregistry.api.view.exception;

/**
 * Типы ошибок при валиидациях полей
 */
public enum ErrorCode {

    // UserView
    USER_NULL_ID(ErrorMessage.USER_ID_NULL),
    USER_NULL_FIRST_NAME(ErrorMessage.USER_FIRST_NAME_NULL),
    USER_NULL_POSITION(ErrorMessage.USER_POSITION_NULL),

    // OfficeView
    OFFICE_NULL_ID(ErrorMessage.OFFICE_ID_NULL),
    OFFICE_NULL_NAME(ErrorMessage.OFFICE_NAME_NULL),
    OFFICE_NULL_ADDRESS(ErrorMessage.OFFICE_ADDRESS_NULL),

    // organizationView
    ORGANIZATION_NULL_ID(ErrorMessage.ORGANIZATION_ID_NULL),
    ORGANIZATION_NULL_NAME(ErrorMessage.ORGANIZATION_NAME_NULL),
    ORGANIZATION_NULL_FULL_NAME(ErrorMessage.ORGANIZATION_FULL_NAME_NULL),
    ORGANIZATION_NULL_ADDRESS(ErrorMessage.ORGANIZATION_ADDRESS_NULL),
    ORGANIZATION_NULL_INN(ErrorMessage.ORGANIZATION_INN_NULL),
    ORGANIZATION_NULL_KPP(ErrorMessage.ORGANIZATION_KPP_NULL);

    private String errorMessage;

    private ErrorCode(String errorString) {
        this.errorMessage = errorString;
    }

    public String getErrorString() {
        return errorMessage;
    }
}