package ru.bellintegrator.school.personnelregistry.api.service;

import ru.bellintegrator.school.personnelregistry.api.dao.exception.DaoException;
import ru.bellintegrator.school.personnelregistry.api.view.OfficeView;
import ru.bellintegrator.school.personnelregistry.api.model.Office;
import ru.bellintegrator.school.personnelregistry.api.view.exception.ViewException;

import java.util.List;

/**
 * Сервис управления информацией подразделения организации
 */
public interface OfficeServiceI {

    /**
     * Возвращает список подразделений по параметрам
     *
     * @param filter - полям {@link OfficeView} являются фильтрами
     * @return коллекция {@link OfficeView}
     */
    List<OfficeView> getList(OfficeView filter);

    /**
     * Возвращает подразделение по id
     *
     * param id уникальный идентификатор сущности {@link Office}
     * @return объект {@link OfficeView}
     */
    OfficeView getById(Integer id) throws DaoException;

    /**
     * Сохраняет(создает) новое подразделение
     *
     * @param param - {@link OfficeView}
     * @return при успешном добавлении значение - true
     */
    Boolean create(OfficeView param) throws DaoException, ViewException;

    /**
     * Обновиляет данные подразделения
     *
     * @param param - {@link OfficeView}
     * @return при успешном обновлении значение - true
     */
    Boolean update(OfficeView param) throws DaoException, ViewException;
}
