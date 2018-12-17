package ru.bellintegrator.school.personnelregistry.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.school.personnelregistry.api.service.OrganizationServiceI;
import ru.bellintegrator.school.personnelregistry.api.view.OrganizationView;
import ru.bellintegrator.school.personnelregistry.api.view.exception.ErrorCode;
import ru.bellintegrator.school.personnelregistry.api.view.exception.ViewException;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Controller управления информацией организации
 */
@RestController
@RequestMapping(value = "/api/organization", produces = APPLICATION_JSON_VALUE)
public class OrganizationController {

    private final OrganizationServiceI organizationService;

    @Autowired
    public OrganizationController(OrganizationServiceI organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * Возвращаетс список организаций по указанным параметрам в теле запроса
     *
     * @param filter
     * @return
     */
    @PostMapping("/list")
    public List<OrganizationView> getList(@Valid @RequestBody OrganizationView filter) throws ViewException {
        if (Objects.isNull(filter.getName())) {
            throw new ViewException(ErrorCode.ORGANIZATION_NULL_NAME);
        }

        return organizationService.getList(filter);
    }

    /**
     * Возвращаетс организацию по id
     *
     * @param id - уникальный идентификатор подразделения
     * @return
     */
    @GetMapping("/{id:[\\d]+}")
    public OrganizationView getById(@Valid @PathVariable("id") Integer id) {
        return organizationService.getById(id);
    }

    /**
     * Сохраняет новую организацию
     *
     * @param view
     * @return {@code Boolean} при успешном добавлении значение - true
     */
    @PostMapping("/save")
    public Boolean create(@Valid @RequestBody OrganizationView view) throws ViewException {
        if (Objects.isNull(view.getName())) {
            throw new ViewException(ErrorCode.ORGANIZATION_NULL_NAME);
        }
        if (Objects.isNull(view.getFullName())) {
            throw new ViewException(ErrorCode.ORGANIZATION_NULL_FULL_NAME);
        }
        if (Objects.isNull(view.getInn())) {
            throw new ViewException(ErrorCode.ORGANIZATION_NULL_INN);
        }
        if (Objects.isNull(view.getKpp())) {
            throw new ViewException(ErrorCode.ORGANIZATION_NULL_KPP);
        }
        if (Objects.isNull(view.getAddress())) {
            throw new ViewException(ErrorCode.ORGANIZATION_NULL_ADDRESS);
        }

        return organizationService.create(view);
    }

    /**
     * Обновляет данные организации
     *
     * @param view
     * @return {@code Boolean} при успешном обновлении значение - true
     */
    @PostMapping("/update")
    public Boolean update(@Valid @RequestBody OrganizationView view) throws ViewException {
        if (Objects.isNull(view.getId())) {
            throw new ViewException(ErrorCode.ORGANIZATION_NULL_ID);
        }
        if (Objects.isNull(view.getName())) {
            throw new ViewException(ErrorCode.ORGANIZATION_NULL_NAME);
        }
        if (Objects.isNull(view.getFullName())) {
            throw new ViewException(ErrorCode.ORGANIZATION_NULL_FULL_NAME);
        }
        if (Objects.isNull(view.getInn())) {
            throw new ViewException(ErrorCode.ORGANIZATION_NULL_INN);
        }
        if (Objects.isNull(view.getKpp())) {

            throw new ViewException(ErrorCode.ORGANIZATION_NULL_KPP);
        }
        if (Objects.isNull(view.getAddress())) {
            throw new ViewException(ErrorCode.ORGANIZATION_NULL_ADDRESS);
        }

        return organizationService.update(view);
    }
}
