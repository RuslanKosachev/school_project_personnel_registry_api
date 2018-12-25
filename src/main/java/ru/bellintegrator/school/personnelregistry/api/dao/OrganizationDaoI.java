package ru.bellintegrator.school.personnelregistry.api.dao;

import ru.bellintegrator.school.personnelregistry.api.dao.exception.DaoException;
import ru.bellintegrator.school.personnelregistry.api.model.Office;
import ru.bellintegrator.school.personnelregistry.api.model.Organization;

import java.util.List;
import java.util.Map;

/**
 * DAO для работы с сущностями Сотрудника
 */
public interface OrganizationDaoI {
    /**
     * Возвращает список сущностей Employee по параметрам
     *
     * @param filter - фильтрры по полям сущности для получения списска
     * @return коллекция {@link Organization}
     */
    List<Organization> getList(Map<String, Object> filter);

    /**
     * Получить Сущность по идентификатору
     *
     * @param id уникальный идентификатор сущности
     * @return при успешном запросе - объект сущность {@link Organization},
     *         иначе null
     */
    Organization getById(Integer id) throws DaoException;

    /**
     * Сохранить сущность
     *
     * @param employee - {@link Office}
     * @return при успешном сохранении возвращает экземпляр обновленного объекта
     */
    Organization create(Organization employee) throws DaoException;

    /**
     * Обновиляет данные сущности
     *
     * @param employee - {@link Organization}
     * @return при успешном обновлении возвращает экземпляр обновленного объекта
     */
    Organization update(Organization employee) throws DaoException;
}
