package ru.bellintegrator.school.personnelregistry.api.dao;

import ru.bellintegrator.school.personnelregistry.api.model.Employee;
import ru.bellintegrator.school.personnelregistry.api.model.CountryCatalog;

import java.util.List;
import java.util.Map;

/**
 * DAO для работы с сущностями Сотрудника
 */
public interface CountryCatalogDaoI {
    /**
     * Возвращает список сущностей CountryCatalog
     *
     * @return коллекция {@link CountryCatalog}
     * @see CountryCatalog
     */
    List<CountryCatalog> getList();

    /**
     * Получить Сущность по идентификатору
     *
     * @param id уникальный идентификатор сущности
     * @return при успешном запросе - объект сущность {@link CountryCatalog},
     *         иначе null
     */
    CountryCatalog getById(Integer id);

    /**
     * Получить Сущность по полю code
     *
     * @param code код государства
     * @return при успешном запросе - объект сущность {@link CountryCatalog},
     *         иначе null
     */
    CountryCatalog getByCode(String code);
}
