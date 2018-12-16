package ru.bellintegrator.school.personnelregistry.api.controller;

import ru.bellintegrator.school.personnelregistry.api.view.UserView;
import ru.bellintegrator.school.personnelregistry.api.view.exception.ErrorCode;
import ru.bellintegrator.school.personnelregistry.api.view.exception.ViewException;
import ru.bellintegrator.school.personnelregistry.api.service.UserServiceI;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

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
     * @param view
     * @return
     */
    @PostMapping("/list")
    public List<UserView> getList(@Valid @RequestBody UserView view) throws Exception {
        if (Objects.isNull(view.getOfficeId())) {
            throw new ViewException(ErrorCode.OFFICE_NULL_ID);
        }

        return userService.getList(view);
    }

    /**
     * Возвращает сотрудника по id
     *
     * @param id - уникальный идентификатор сотрудника
     * @return
     */
    @GetMapping("/{id:[\\d]+}")
    public UserView getById(@Valid @PathVariable("id") Integer id) {
        return userService.getById(id);
    }

    /**
     * Сохраняет нового сотрудника
     *
     * @param view
     * @return true при успешном обновлении значение
     */
    @PostMapping("/save")
    public Boolean create(@Valid @RequestBody UserView view) throws ViewException {
        if (Objects.isNull(view.getOfficeId())) {
            throw new ViewException(ErrorCode.OFFICE_NULL_ID);
        }
        if (Objects.isNull(view.getFirstName())) {
            throw new ViewException(ErrorCode.USER_NULL_FIRST_NAME);
        }
        if (Objects.isNull(view.getPosition())) {
            throw new ViewException(ErrorCode.USER_NULL_POSITION);
        }

        return userService.create(view);
    }

    /**
     * Обновляет данные сотрудника
     *
     * @param view
     * @return true при успешном обновлении значение
     */
    @PostMapping("/update")
    public Boolean update(@Valid @RequestBody UserView view) throws Exception {
        if (Objects.isNull(view.getId())) {
            throw new ViewException(ErrorCode.USER_NULL_ID);
        }
        if (Objects.isNull(view.getFirstName())) {
            throw new ViewException(ErrorCode.USER_NULL_FIRST_NAME);
        }
        if (Objects.isNull(view.getPosition())) {
            throw new ViewException(ErrorCode.USER_NULL_POSITION);
        }

        return userService.update(view);
    }
}
