package ru.bellintegrator.school.personnelregistry.api.controller;

import ru.bellintegrator.school.personnelregistry.api.dao.exception.DaoException;
import ru.bellintegrator.school.personnelregistry.api.view.UserView;
import ru.bellintegrator.school.personnelregistry.api.view.exception.ViewException;
import ru.bellintegrator.school.personnelregistry.api.service.UserServiceI;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Controller управления информацией об сотрудниках
 */
@RestController
@RequestMapping(value = "/api/user", produces = APPLICATION_JSON_VALUE)
public class UserController {

    private final UserServiceI userService;

    @Autowired
    public UserController(UserServiceI userService) {
        this.userService = userService;
    }

    /**
     * Возвращает список сотрудников по указанным параметрам в теле запроса
     *
     * @param view объект {@link UserView} для отображения данных сотрудника
     * @return список объектов {@link UserView}
     */
    @PostMapping("/list")
    public List<UserView> getList(@Validated(UserView.Filter.class) @RequestBody UserView view) throws ViewException {
        return userService.getList(view);
    }

    /**
     * Возвращает сотрудника по id
     *
     * @param id - уникальный идентификатор сотрудника
     * @return объект для отображения данных сотрудника {@link UserView}
     */
    @GetMapping("/{id}")
    public UserView getById(@PathVariable("id") Integer id) throws DaoException {
        return userService.getById(id);
    }

    /**
     * Сохраняет нового сотрудника
     *
     * @param view объект {@link UserView} для отображения данных сотрудника
     * @return при успешном обновлении значение - true
     */
    @PostMapping("/save")
    public Boolean create(@Validated(UserView.Create.class) @RequestBody UserView view) throws ViewException, DaoException {
        return userService.create(view);
    }

    /**
     * Обновляет данные сотрудника
     *
     * @param view объект {@link UserView} для отображения данных сотрудника
     * @return при успешном обновлении значение - true
     */
    @PostMapping("/update")
    public Boolean update(@Validated(UserView.Update.class) @RequestBody UserView view) throws ViewException, DaoException {
        return userService.update(view);
    }
}
