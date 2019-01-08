package ru.bellintegrator.school.personnelregistry.api.controller;

import ru.bellintegrator.school.personnelregistry.api.dao.exception.DaoException;
import ru.bellintegrator.school.personnelregistry.api.service.OrganizationServiceI;
import ru.bellintegrator.school.personnelregistry.api.view.OrganizationView;
import ru.bellintegrator.school.personnelregistry.api.view.exception.ViewException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.Valid;
import java.util.List;

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
     * @param filter объект {@link OrganizationView} для отображения данных сотрудника
     * @return список объектов {@link OrganizationView}
     */
    @PostMapping("/list")
    public List<OrganizationView> getList(@Validated(OrganizationView.Filter.class) @RequestBody OrganizationView filter) {
        return organizationService.getList(filter);
    }

    /**
     * Возвращаетс организацию по id
     *
     * @param id - уникальный идентификатор организации
     * @return объект для отображения данных сотрудника {@link OrganizationView}
     */
    @GetMapping("/{id:[\\d]+}")
    public OrganizationView getById(@Valid @PathVariable("id") Integer id) throws DaoException {
        return organizationService.getById(id);
    }

    /**
     * Сохраняет новую организацию
     *
     * @param view объект {@link OrganizationView} для отображения данных организации
     * @return при успешном обновлении значение true
     */
    @PostMapping("/save")
    public Boolean create(@Validated(OrganizationView.Create.class) @RequestBody OrganizationView view)
            throws ViewException, DaoException {
        return organizationService.create(view);
    }

    /**
     * Обновляет данные организации
     *
     * @param view объект {@link OrganizationView} для отображения данных организация
     * @return при успешном обновлении значение - true
     */
    @PostMapping("/update")
    public Boolean update(@Validated(OrganizationView.Update.class) @RequestBody OrganizationView view)
            throws ViewException, DaoException {
        return organizationService.update(view);
    }
}
