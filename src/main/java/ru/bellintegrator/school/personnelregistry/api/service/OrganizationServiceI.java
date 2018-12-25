package ru.bellintegrator.school.personnelregistry.api.service;

import ru.bellintegrator.school.personnelregistry.api.dao.exception.DaoException;
import ru.bellintegrator.school.personnelregistry.api.view.OrganizationView;
import ru.bellintegrator.school.personnelregistry.api.model.Organization;
import ru.bellintegrator.school.personnelregistry.api.view.exception.ViewException;

import java.util.List;

/**
 * Сервис управления информацией организации
 */
public interface OrganizationServiceI {

    /**
     * Возвращает список организаций по параметрам
     *
     * @param filter - полям {@link OrganizationView} являются фильтрами
     * @return коллекция {@link OrganizationView}
     */
    List<OrganizationView> getList(OrganizationView filter);

    /**
     * Возвращает подразделение по id
     *
     * param id уникальный идентификатор сущности {@link Organization}
     * @return объект {@link OrganizationView}
     */
    OrganizationView getById(Integer id) throws DaoException;

    /**
     * Сохраняет(создает) новую организацию
     *
     * @param param - {@link OrganizationView}
     * @return при успешном добавлении значение - true
     */
    Boolean create(OrganizationView param) throws ViewException, DaoException;

    /**
     * Обновиляет данные организации
     *
     * @param param - {@link OrganizationView}
     * @return при успешном обновлении значение - true
     */
    Boolean update(OrganizationView param) throws ViewException, DaoException;
}
