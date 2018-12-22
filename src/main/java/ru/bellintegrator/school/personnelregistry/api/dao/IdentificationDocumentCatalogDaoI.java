package ru.bellintegrator.school.personnelregistry.api.dao;

import ru.bellintegrator.school.personnelregistry.api.model.IdentificationDocumentCatalog;

import java.util.List;

/**
 * DAO для работы с сущностями Сотрудника
 */
public interface IdentificationDocumentCatalogDaoI {
    /**
     * Возвращает список сущностей CountryCatalog
     *
     * @return коллекция {@link IdentificationDocumentCatalog}
     * @see IdentificationDocumentCatalog
     */
    List<IdentificationDocumentCatalog> getList();

    /**
     * Получить Сущность по идентификатору
     *
     * @param id уникальный идентификатор сущности
     * @return при успешном запросе - объект сущность {@link IdentificationDocumentCatalog},
     *         иначе null
     */
    IdentificationDocumentCatalog getById(Integer id);

    /**
     * Получить Сущность по полю code
     *
     * @param code код типа документа
     * @return при успешном запросе - объект сущность {@link IdentificationDocumentCatalog},
     *         иначе null
     */
    IdentificationDocumentCatalog getByCode(String code);
}
