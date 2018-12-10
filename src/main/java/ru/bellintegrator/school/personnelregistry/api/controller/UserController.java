package ru.bellintegrator.school.personnelregistry.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.school.personnelregistry.api.view.UserView;
import ru.bellintegrator.school.personnelregistry.api.service.UserServiceI;
import ru.bellintegrator.school.personnelregistry.api.view.exception.ErrorCode;
import ru.bellintegrator.school.personnelregistry.api.view.exception.ViewException;

import javax.validation.Valid;
import javax.validation.Validator;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Controller управления информацией об сотрудниках
 */
@RestController
@RequestMapping(value = "/api/user", produces = APPLICATION_JSON_VALUE)
public class UserController {

    private final UserServiceI UserServiceI;

    @Autowired
    public UserController(UserServiceI UserServiceI, Validator validator, Validator validator1) {
        this.UserServiceI = UserServiceI;
    }

    /**
     * Получает всех сотрудников по указанным параметрам
     *
     * @param userView
     * @return
     */
    @PostMapping("/list")
    public List<UserView> getList(@Valid @RequestBody UserView userView) throws Exception {
        if (Objects.isNull(userView.getOfficeId())) {
            throw new ViewException(ErrorCode.OFFICE_NULL_ID);
        }

        return UserServiceI.getList(userView);
    }

    /**
     * Получает сотрудника по id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id:[\\d]+}")
    public UserView getById(@Valid @PathVariable("id") Integer id) {
        return UserServiceI.getById(id);
    }

    /**
     * Сохраняет нового сотрудника
     *
     * @param userView
     * @return Boolean
     */
    @PostMapping("/save")
    public Boolean create(@Valid @RequestBody UserView userView) throws ViewException, ParseException {
        if (Objects.isNull(userView.getOfficeId())) {
            throw new ViewException(ErrorCode.OFFICE_NULL_ID);
        }
        if (Objects.isNull(userView.getFirstName())) {
            throw new ViewException(ErrorCode.USER_NULL_FIRST_NAME);
        }
        if (Objects.isNull(userView.getPosition())) {
            throw new ViewException(ErrorCode.USER_NULL_POSITION);
        }

        return UserServiceI.create(userView);
    }

    /**
     * Обновляет данные запрашиваемого сотрудника
     *
     * @param userView
     * @return
     */
    @PostMapping("/update")
    public void update(@Valid @RequestBody UserView userView) throws Exception {
        if (Objects.isNull(userView.getId())) {
            throw new ViewException(ErrorCode.USER_NULL_ID);
        }
        if (Objects.isNull(userView.getFirstName())) {
            throw new ViewException(ErrorCode.USER_NULL_FIRST_NAME);
        }
        if (Objects.isNull(userView.getPosition())) {
            throw new ViewException(ErrorCode.USER_NULL_POSITION);
        }

        UserServiceI.update(userView);
    }
}
