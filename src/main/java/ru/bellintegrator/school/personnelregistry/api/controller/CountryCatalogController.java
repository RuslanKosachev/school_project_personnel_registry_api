package ru.bellintegrator.school.personnelregistry.api.controller;

import ru.bellintegrator.school.personnelregistry.api.service.CountryCatalogServiceI;
import ru.bellintegrator.school.personnelregistry.api.view.CountryCatalogView;
import ru.bellintegrator.school.personnelregistry.api.view.exception.ViewException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Controller управления информацией справочника стран
 */
@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class CountryCatalogController {

    private final CountryCatalogServiceI countryCatalogService;

    @Autowired
    public CountryCatalogController(CountryCatalogServiceI countryCatalogService) {
        this.countryCatalogService = countryCatalogService;
    }

    /**
     * Возвращает все страны из каталога
     *
     * @return
     */
    @PostMapping("/countries")
    public List<CountryCatalogView> getList() throws ViewException {
        return countryCatalogService.getList();
    }
}
