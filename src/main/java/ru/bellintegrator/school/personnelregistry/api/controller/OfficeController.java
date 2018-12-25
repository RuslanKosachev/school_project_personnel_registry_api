package ru.bellintegrator.school.personnelregistry.api.controller;

import ru.bellintegrator.school.personnelregistry.api.dao.exception.DaoException;
import ru.bellintegrator.school.personnelregistry.api.service.OfficeServiceI;
import ru.bellintegrator.school.personnelregistry.api.view.OfficeView;
import ru.bellintegrator.school.personnelregistry.api.error.ErrorCode;
import ru.bellintegrator.school.personnelregistry.api.view.exception.ViewException;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Controller управления информацией подразделения организации
 */
@RestController
@RequestMapping(value = "/api/office", produces = APPLICATION_JSON_VALUE)
public class OfficeController {

    private final OfficeServiceI officeService;

    @Autowired
    public OfficeController(OfficeServiceI officeService) {
        this.officeService = officeService;
    }

    /**
     * Получает список подразделений по указанным параметрам в теле запроса
     *
     * @param filter объект {@link OfficeView} для отображения данных сотрудника
     * @return список объектов {@link OfficeView}
     */
    @PostMapping("/list")
    public List<OfficeView> getList(@Valid @RequestBody OfficeView filter) throws ViewException {
        if (Objects.isNull(filter.getOrgId())) {
            throw new ViewException(ErrorCode.ORG_V_ID_NULL);
        }
        return officeService.getList(filter);
    }

    /**
     * Возвращает подразделение по id
     *
     * @param id - уникальный идентификатор подразделения
     * @return объект для отображения данных сотрудника {@link OfficeView}
     */
    @GetMapping("/{id:[\\d]+}")
    public OfficeView getById(@Valid @PathVariable("id") Integer id) throws DaoException {
        return officeService.getById(id);
    }

    /**
     * Сохраняет нового сотрудника
     *
     * @param view объект {@link OfficeView} для отображения данных подразделения
     * @return при успешном обновлении значение true
     */
    @PostMapping("/save")
    public Boolean create(@Valid @RequestBody OfficeView view) throws ViewException, DaoException {
        if (Objects.isNull(view.getOrgId())) {
            throw new ViewException(ErrorCode.ORG_V_ID_NULL);
        }
        if (Objects.isNull(view.getName())) {
            throw new ViewException(ErrorCode.OFFICE_V_NAME_NULL);
        }
        if (Objects.isNull(view.getAddress())) {
            throw new ViewException(ErrorCode.OFFICE_V_ADDRESS_NULL);
        }

        return officeService.create(view);
    }

    /**
     * Обновляет данные подразделения
     *
     * @param view объект {@link OfficeView} для отображения данных подразделения
     * @return при успешном обновлении значение - true
     */
    @PostMapping("/update")
    public Boolean update(@Valid @RequestBody OfficeView view) throws ViewException, DaoException {
        if (Objects.isNull(view.getId())) {
            throw new ViewException(ErrorCode.OFFICE_V_ID_NULL);
        }
        if (Objects.isNull(view.getName())) {
            throw new ViewException(ErrorCode.OFFICE_V_NAME_NULL);
        }
        if (Objects.isNull(view.getAddress())) {
            throw new ViewException(ErrorCode.OFFICE_V_ADDRESS_NULL);
        }

        return officeService.update(view);
    }
}
