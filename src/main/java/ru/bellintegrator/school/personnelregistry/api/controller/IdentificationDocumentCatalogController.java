package ru.bellintegrator.school.personnelregistry.api.controller;

import org.springframework.web.bind.annotation.*;
import ru.bellintegrator.school.personnelregistry.api.service.IdentificationDocumentCatalogServiceI;
import ru.bellintegrator.school.personnelregistry.api.view.IdentificationDocumentCatalogView;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Controller управления информацией справочника видов документов, удостоверяющих личность физического лица
 */
@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
public class IdentificationDocumentCatalogController {

    private final IdentificationDocumentCatalogServiceI identificationDocumentCatalogService;

    @Autowired
    public IdentificationDocumentCatalogController(IdentificationDocumentCatalogServiceI identificationDocumentCatalogService) {
        this.identificationDocumentCatalogService = identificationDocumentCatalogService;
    }

    /**
     * Возвращает все типы дкументов из каталога
     *
     * @return
     */
    @GetMapping("/docs")
    public List<IdentificationDocumentCatalogView> getList() {
        return identificationDocumentCatalogService.getList();
    }
}