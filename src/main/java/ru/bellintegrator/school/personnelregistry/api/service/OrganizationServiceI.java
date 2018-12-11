package ru.bellintegrator.school.personnelregistry.api.service;

import ru.bellintegrator.school.personnelregistry.api.view.OrganizationView;

import java.util.List;

/**
 * Сервис управления информацией организации
 */
public interface OrganizationServiceI {

    /**
     * Возвращает список организаций по параметрам
     *
     * @param filter - фильтр
     *
     * @return {@OfficeView}
     */
    List<OrganizationView> getList(OrganizationView filter);

    /**
     * Возвращает подразделение по id
     *
     * @param id - уникальный идентификатор организации
     * @return {@OfficeView}
     */
    OrganizationView getById(Integer id);

    /**
     * Сохраняет(создает) новую организацию
     *
     * @return {@Boolean} при успешном добавлении значение - true
     */
    Boolean create(OrganizationView param);

    /**
     * Обновиляет данные организации
     *
     * @return {@Boolean} при успешном обновлении значение - true
     */
    Boolean update(OrganizationView param);
}
