package ru.bellintegrator.school.personnelregistry.api.service;

import ru.bellintegrator.school.personnelregistry.api.view.UserView;

import java.util.List;

/**
 * Сервис управления информацией об сотрудниках
 */
public interface UserServiceI {

    /**
     * Возвращает список сотрудников по параметрам
     *
     * @return {@UserView}
     */
    List<UserView> getList(UserView filter);

    /**
     * Возвращает сотрудника по id
     *
     * @return {@UserView}
     */
    UserView getById(Integer id);

    /**
     * Сохраняет(создает) нового сотрудника
     *
     * @return {@Boolean} при успешном добавлении значение - true
     */
    Boolean create(UserView param);

    /**
     * Обновиляет данные сотрудника
     *
     * @return {@Boolean} при успешном обновлении значение - true
     */
    Boolean update(UserView param);
}
