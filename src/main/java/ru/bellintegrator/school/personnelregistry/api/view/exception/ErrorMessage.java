package ru.bellintegrator.school.personnelregistry.api.view.exception;

/**
 * Содержит сообщения ошибок при валиидациях полей
 * */
public interface ErrorMessage {

    // User
    public static final String USER_ID_NULL = "отсуцтвует идентификатор сотрудника";
    public static final String USER_FIRST_NAME_NULL = "отсуцтвует имя сотрудника";
    public static final String USER_POSITION_NULL = "отсуцтвует должность сотрудника";

    // Office
    public static final String OFFICE_ID_NULL = "отсуцтвует идентификатор офиса";
    public static final String OFFICE_NAME_NULL = "отсуцтвует названия офиса";
    public static final String OFFICE_ADDRESS_NULL = "отсуцтвует адрес офиса";

    // organization
    public static final String ORGANIZATION_ID_NULL = "отсуцтвует идентификатор организации";
    public static final String ORGANIZATION_NAME_NULL = "отсуцтвует название организации";
    public static final String ORGANIZATION_FULL_NAME_NULL = "отсуцтвует полное название организации";
    public static final String ORGANIZATION_ADDRESS_NULL = "отсуцтвует адрес организации";
    public static final String ORGANIZATION_INN_NULL = "отсуцтвует идентификационный номер налогоплательщика организации";
    public static final String ORGANIZATION_KPP_NULL = "отсуцтвует код причины постановки на учет в налоговых органах";

    public static final String ORGANIZATION_ID_MIN = "id не должен быть меньше единицы";
    public static final String ORGANIZATION_INN_PATTERN = "идентификационный номер налогоплательщика организации не соответсвует формату";
    public static final String ORGANIZATION_KPP_PATTERN = "код причины постановки на учет в налоговых органах не соответсвует формату";

    // Country catalog
    public static final String COUNTRY_CATALOG_PATTERN = "Цифровой код страны по ISO 3166-1 не соответсвует формату";

    // Identification document catalog
    public static final String IDENTIFICATION_DOCUMENT_CATALOG_PATTERN = "Код типа документа не соответсвует формату";
}
