package ru.bellintegrator.school.personnelregistry.api.controller;

import ru.bellintegrator.school.personnelregistry.api.service.OfficeServiceI;
import ru.bellintegrator.school.personnelregistry.api.view.OfficeView;
import ru.bellintegrator.school.personnelregistry.api.view.exception.ErrorCode;
import ru.bellintegrator.school.personnelregistry.api.view.exception.ViewException;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Controller управления информацией подразделения организации
 */
@RestController
@RequestMapping(value = "/api/Office", produces = APPLICATION_JSON_VALUE)
public class OfficeController {

    private final OfficeServiceI officeService;

    @Autowired
    public OfficeController(OfficeServiceI officeService) {
        this.officeService = officeService;
    }

    /**
     * Получает список подразделений по указанным параметрам в теле запроса
     *
     * @param view - фильтр
     * @return
     */
    @PostMapping("/list")
    public List<OfficeView> getList(@Valid @RequestBody OfficeView view) throws ViewException {
        if (Objects.isNull(view.getOrgId())) {
            throw new ViewException(ErrorCode.ORGANIZATION_NULL_ID);
        }

        return officeService.getList(view);
    }

    /**
     * Возвращает подразделение по id
     *
     * @param id - уникальный идентификатор подразделения
     * @return
     */
    @GetMapping("/{id:[\\d]+}")
    public OfficeView getById(@Valid @PathVariable("id") Integer id) {
        return officeService.getById(id);
    }

    /**
     * Сохраняет нового сотрудника
     *
     * @param view
     * @return {@code Boolean} при успешном добавлении значение - true
     */
    @PostMapping("/save")
    public Boolean create(@Valid @RequestBody OfficeView view) throws ViewException {
        if (Objects.isNull(view.getOrgId())) {
            throw new ViewException(ErrorCode.ORGANIZATION_NULL_ID);
        }
        if (Objects.isNull(view.getName())) {
            throw new ViewException(ErrorCode.OFFICE_NULL_NAME);
        }
        if (Objects.isNull(view.getAddress())) {
            throw new ViewException(ErrorCode.OFFICE_NULL_ADDRESS);
        }

        return officeService.create(view);
    }

    /**
     * Обновляет данные подразделения
     *
     * @param view
     * @return {@code Boolean} при успешном обновлении значение - true
     */
    @PostMapping("/update")
    public Boolean update(@Valid @RequestBody OfficeView view) throws ViewException {
        if (Objects.isNull(view.getId())) {
            throw new ViewException(ErrorCode.OFFICE_NULL_ID);
        }
        if (Objects.isNull(view.getName())) {
            throw new ViewException(ErrorCode.OFFICE_NULL_NAME);
        }
        if (Objects.isNull(view.getAddress())) {
            throw new ViewException(ErrorCode.OFFICE_NULL_ADDRESS);
        }

        return officeService.update(view);
    }
}
