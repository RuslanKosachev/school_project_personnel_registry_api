package ru.bellintegrator.school.personnelregistry.api.error;

/**
 * Типы ошибок при валиидациях полей
 */
public enum ErrorCode {

    // UserView
    USER_V_NULL(ErrorMessage.USER_V_NULL),
    USER_V_ID_NULL(ErrorMessage.USER_V_ID_NULL),
    USER_V_FNAME_NULL(ErrorMessage.USER_V_FNAME_NULL),
    USER_V_POSITION_NULL(ErrorMessage.USER_V_POSITION_NULL),
    // Employee DAO
    EMPLOYEE_NULL(ErrorMessage.EMPLOYEE_NULL),
    EMPLOYEE_ID_NULL(ErrorMessage.EMPLOYEE_ID_NULL),
    EMPLOYEE_FNAME_NULL(ErrorMessage.EMPLOYEE_FNAME_NULL),
    EMPLOYEE_POSITION_NULL(ErrorMessage.EMPLOYEE_POSITION_NULL),
    EMPLOYEE_SQL_BY_ID_NO_RESULT(ErrorMessage.EMPLOYEE_SQL_BY_ID_NO_RESULT),

    // OfficeView,
    OFFICE_V_NULL(ErrorMessage.OFFICE_V_NULL),
    OFFICE_V_ID_NULL(ErrorMessage.OFFICE_V_ID_NULL),
    OFFICE_V_NAME_NULL(ErrorMessage.OFFICE_V_NAME_NULL),
    OFFICE_V_ADDRESS_NULL(ErrorMessage.OFFICE_V_ADDRESS_NULL),
    // Office DAO
    OFFICE_NULL(ErrorMessage.OFFICE_NULL),
    OFFICE_ID_NULL(ErrorMessage.OFFICE_ID_NULL),
    OFFICE_NAME_NULL(ErrorMessage.OFFICE_NAME_NULL),
    OFFICE_ADDRESS_NULL(ErrorMessage.OFFICE_ADDRESS_NULL),
    OFFICE_SQL_BY_ID_NO_RESULT(ErrorMessage.OFFICE_SQL_BY_ID_NO_RESULT),

    // organizationView,
    ORG_V_NULL(ErrorMessage.ORG_V_NULL),
    ORG_V_ID_NULL(ErrorMessage.ORG_V_ID_NULL),
    ORG_V_NAME_NULL(ErrorMessage.ORG_V_NAME_NULL),
    ORG_V_FULL_NAME_NULL(ErrorMessage.ORG_V_FULL_NAME_NULL),
    ORG_V_ADDRESS_NULL(ErrorMessage.ORG_V_ADDRESS_NULL),
    ORG_V_INN_NULL(ErrorMessage.ORG_V_INN_NULL),
    ORG_V_KPP_NULL(ErrorMessage.ORG_V_KPP_NULL),
    // organization DAO
    ORG_NULL(ErrorMessage.ORG_NULL),
    ORG_ID_NULL(ErrorMessage.ORG_ID_NULL),
    ORG_NAME_NULL(ErrorMessage.ORG_NAME_NULL),
    ORG_FULL_NAME_NULL(ErrorMessage.ORG_FULL_NAME_NULL),
    ORG_ADDRESS_NULL(ErrorMessage.ORG_ADDRESS_NULL),
    ORG_INN_NULL(ErrorMessage.ORG_INN_NULL),
    ORG_KPP_NULL(ErrorMessage.ORG_KPP_NULL),
    ORG_SQL_BY_ID_NO_RESULT(ErrorMessage.ORG_SQL_BY_ID_NO_RESULT),

    // CountryCatalog catalog DAO
    COUNTRY_CATALOG_SQL_BY_ID_NO_RESULT(ErrorMessage.COUNTRY_CATALOG_SQL_BY_ID_NO_RESULT),
    COUNTRY_CATALOG_SQL_BY_CODE_NO_RESULT(ErrorMessage.COUNTRY_CATALOG_SQL_BY_CODE_NO_RESULT),

    // Identification document catalog DAO
    IDENTIFICATION_DOCUMENT_CATALOG_SQL_BY_ID_NO_RESULT(ErrorMessage.IDENTIFICATION_DOCUMENT_CATALOG_SQL_BY_ID_NO_RESULT),
    IDENTIFICATION_DOCUMENT_CATALOG_SQL_BY_CODE_NO_RESULT(ErrorMessage.IDENTIFICATION_DOCUMENT_CATALOG_SQL_BY_CODE_NO_RESULT);

    private String errorMessage;

    private ErrorCode(String errorString) {
        this.errorMessage = errorString;
    }

    public String getErrorString() {
        return errorMessage;
    }
}