package ru.bellintegrator.school.personnelregistry.api.service;

import org.springframework.validation.annotation.Validated;
import ru.bellintegrator.school.personnelregistry.api.view.UserView;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

/**
 * Сервис
 */
@Validated
public interface UserServiceI {

    /**
     * Возвращает список сотрудников по параметрам
     *
     * @return {@UserView}
     */
    List<UserView> getList(@Valid UserView filter);

    /**
     * Вернуть сотрудника по id
     *
     * @return {@UserView}
     */
    UserView getById(Integer id);

    /**
     * Сохранить(создать) нового сотрудника
     */
    Boolean create(@Valid UserView param) throws ParseException;

    /**
     * Обновить данные сотрудника
     */
    boolean update(@Valid UserView param) throws ParseException;
}
