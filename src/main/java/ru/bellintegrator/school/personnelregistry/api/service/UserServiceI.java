package ru.bellintegrator.school.personnelregistry.api.service;

import ru.bellintegrator.school.personnelregistry.api.model.Employee;
import ru.bellintegrator.school.personnelregistry.api.view.UserView;

import java.util.List;

/**
 * Сервис управления информацией об сотрудниках
 */
public interface UserServiceI {
    /**
     * Возвращает список сотрудников по параметрам
     *
     * @param filter - полям {@link UserView} являются фильтрами
     * @return коллекция {@link UserView}
     */
    List<UserView> getList(UserView filter);

    /**
     * Возвращает сотрудника по id
     *
     * @param id уникальный идентификатор сущности {@link Employee}
     * @return объект {@link UserView}
     */
    UserView getById(Integer id);

    /**
     * Сохраняет(создает) нового сотрудника
     *
     * @param param - {@link UserView}
     * @return при успешном добавлении значение - true
     * @see UserView
     */
    Boolean create(UserView param);

    /**
     * Обновиляет данные сотрудника
     *
     * @param param - {@link UserView}
     * @return при успешном обновлении значение - true
     */
    Boolean update(UserView param);
}
