package ru.bellintegrator.school.personnelregistry.api.service;

import ru.bellintegrator.school.personnelregistry.api.view.OfficeView;

import java.util.List;

/**
 * Сервис управления информацией подразделения организации
 */
public interface OfficeServiceI {

    /**
     * Возвращает список подразделений по параметрам
     *
     * @param filter - фильтр
     *
     * @return {@OfficeView}
     */
    List<OfficeView> getList(OfficeView filter);

    /**
     * Возвращает подразделение по id
     *
     * @param id - уникальный идентификатор подразделения
     * @return {@OfficeView}
     */
    OfficeView getById(Integer id);

    /**
     * Сохраняет(создает) новое подразделение
     *
     * @return {@Boolean} при успешном добавлении значение - true
     */
    Boolean create(OfficeView param);

    /**
     * Обновиляет данные подразделения
     *
     * @return {@Boolean} при успешном обновлении значение - true
     */
    Boolean update(OfficeView param);
}
