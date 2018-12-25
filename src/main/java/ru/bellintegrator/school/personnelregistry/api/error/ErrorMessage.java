package ru.bellintegrator.school.personnelregistry.api.error;

/**
 * Содержит сообщения ошибок при валиидациях полей
 * */
public interface ErrorMessage {

    // User VIEW - Employee
    String USER_V_NULL = "нет ссылки на тип объекта UserView";
    String USER_V_ID_NEGATIVE_OR_ZERO = "идентификатор сотрудника не должен быть меньше единицы";
    String USER_V_ID_NULL = "отсуцтвует идентификатор сотрудника";
    String USER_V_FNAME_NULL = "имя сотрудника обязателено для заполнения";
    String USER_V_POSITION_NULL = "должность сотрудника обязателено для заполнения";
    String USER_V_FNAME_MIN = "значение имени сотрудника не должно быть пустой строкой";
    String USER_V_FNAME_MAX = "значение имени сотрудника не должно превышать 50 символов";
    String USER_V_SNAME_MIN = "значение фамилии сотрудника не должно быть пустой строкой";
    String USER_V_SNAME_MAX = "значение фамилии сотрудника не должно превышать 50 символов";
    String USER_V_MNAME_MIN = "значение отчества сотрудника не должно быть пустой строкой";
    String USER_V_MNAME_MAX = "значение отчества сотрудника не должно превышать 50 символов";
    String USER_V_POSITION_MIN = "значение должности не должа быть пустой строкой";
    String USER_V_POSITION_MAX = "значение  должности не должно превышать 100 символов";
    String USER_V_PHONE_MIN = "значение телефона не должо быть пустой строкой";
    String USER_V_PHONE_MAX = "значение телефона не должно превышать 20 символов";
    String USER_V_DOC_NAME_MIN = "значение наименование документа сотрудника не должо быть пустой строкой";
    String USER_V_DOC_NAME_MAX = "значение наименование документа сотрудника не должно превышать 250 символов";
    String USER_V_DOC_NUMBER_MIN = "значение номера документа сотрудника не должо быть пустой строкой";
    String USER_V_DOC_NUMBER_MAX = "значение номера документа сотрудника не должно превышать 20 символов";
    // Employee DAO
    String EMPLOYEE_NULL = "нет ссылки на тип объекта Employee";
    String EMPLOYEE_ID_NULL = "отсуцтвует идентификатор сотрудника";
    String EMPLOYEE_FNAME_NULL = "отсуцтвует имя сотрудника";
    String EMPLOYEE_POSITION_NULL = "отсуцтвует должность сотрудника";
    String EMPLOYEE_SQL_BY_ID_NO_RESULT = "при запросе по уникальному идентификатору, сотрудник не найден";

    // Office VIEW
    String OFFICE_V_NULL = "нет ссылки на тип объекта OfficeView";
    String OFFICE_V_ID_NEGATIVE_OR_ZERO = "идентификатор подразделения не должен быть меньше единицы";
    String OFFICE_V_ID_NULL = "отсуцтвует идентификатор офиса";
    String OFFICE_V_NAME_NULL = "названия офиса обязателено для заполнения";
    String OFFICE_V_ADDRESS_NULL = "адрес офиса обязателен для заполнения";
    String OFFICE_V_NAME_MIN = "значение наименования подразделения не должо быть пустой строкой";
    String OFFICE_V_NAME_MAX = "значение наименования подразделения не должно превышать 100 символов";
    String OFFICE_V_ADDRESS_MIN = "значение адреса подразделения не должо быть пустой строкой";
    String OFFICE_V_ADDRESS_MAX = "значение адреса подразделения не должно превышать 350 символов";
    String OFFICE_V_PHONE_MIN = "значение телефона подразделения не должо быть пустой строкой";
    String OFFICE_V_PHONE_MAX = "значение телефона подразделения не должно превышать 20 символов";
    // Office DAO
    String OFFICE_NULL = "нет ссылки на тип объекта Office";
    String OFFICE_ID_NULL = "отсуцтвует идентификатор офиса";
    String OFFICE_NAME_NULL = "отсуцтвует названия подразделения";
    String OFFICE_ADDRESS_NULL = "отсуцтвует адрес подразделения";
    String OFFICE_SQL_BY_ID_NO_RESULT = "при запросе по уникальному идентификатору, подразделение не найдено";

    // organization VIEW
    String ORG_V_NULL = "нет ссылки на тип объекта OrganizationView";
    String ORG_V_ID_NEGATIVE_OR_ZERO = "идентификатор организации не должен быть меньше единицы";
    String ORG_V_ID_NULL = "отсуцтвует идентификатор организации";
    String ORG_V_NAME_NULL = "название организации обязателен одля заполнения";
    String ORG_V_FULL_NAME_NULL = "полное название организации обязателено для заполнения";
    String ORG_V_ADDRESS_NULL = "адрес организации обязателен для заполнения";
    String ORG_V_INN_NULL = "идентификационный номер налогоплательщика организации обязателен для заполнения";
    String ORG_V_KPP_NULL = "код причины постановки на учет в налоговых органах обязателен для заполнения";
    String ORG_V_NAME_MIN = "значение наименования организации не должо быть пустой строкой";
    String ORG_V_NAME_MAX = "значение наименования организации не должно превышать 100 символов";
    String ORG_V_FULL_NAME_MIN = "значение полного наименования организации не должо быть пустой строкой";
    String ORG_V_FULL_NAME_MAX = "значение полного наименования организации не должно превышать 350 символов";
    String ORG_V_ADDRESS_MIN = "значение адреса организации не должо быть пустой строкой";
    String ORG_V_ADDRESS_MAX = "значение адреса организации не должно превышать 350 символов";
    String ORG_V_PHONE_MIN = "значение номера телефона организации не должо быть пустой строкой";
    String ORG_V_PHONE_MAX = "значение номера телефона организации не должно превышать 20 символов";
    String ORG_V_INN_PATTERN = "идентификационный номер налогоплательщика организации не соответсвует формату";
    String ORG_V_KPP_PATTERN = "код причины постановки на учет в налоговых органах не соответсвует формату";
    // organization DAO
    String ORG_NULL = "нет ссылки на тип объекта Organization";
    String ORG_ID_NULL = "отсуцтвует идентификатор организации";
    String ORG_NAME_NULL = "отсуцтвует название организации";
    String ORG_FULL_NAME_NULL = "отсуцтвует полное название организации";
    String ORG_ADDRESS_NULL = "отсуцтвует адрес организации";
    String ORG_INN_NULL = "отсуцтвует идентификационный номер налогоплательщика организации";
    String ORG_KPP_NULL = "отсуцтвует код причины постановки на учет в налоговых органах";
    String ORG_SQL_BY_ID_NO_RESULT = "при запросе по уникальному идентификатору, организация не найдена";

    // CountryCatalog catalog VIEW
    String COUNTRY_CATALOG_V_PATTERN = "Цифровой код страны по ISO 3166-1 не соответсвует формату";
    // CountryCatalog catalog DAO
    String COUNTRY_CATALOG_SQL_BY_ID_NO_RESULT = "при запросе по уникальному идентификатору, государство не найдено";
    String COUNTRY_CATALOG_SQL_BY_CODE_NO_RESULT = "при запросе по коду, государство не найдено";

    // Identification document catalog VIEW
    String IDENTIFICATION_DOCUMENT_CATALOG_V_PATTERN = "Код типа документа не соответсвует формату";
    // Identification document catalog DAO
    String IDENTIFICATION_DOCUMENT_CATALOG_SQL_BY_ID_NO_RESULT = "при запросе по уникальному идентификатору, тип документа удостоверяющий личность физического лица не найден";
    String IDENTIFICATION_DOCUMENT_CATALOG_SQL_BY_CODE_NO_RESULT = "при запросе по коду, тип документа удостоверяющий личность физического лица не найден";


    //general
    String ARG_NULL = "ожидаеый аргумет равен NULL";
}
