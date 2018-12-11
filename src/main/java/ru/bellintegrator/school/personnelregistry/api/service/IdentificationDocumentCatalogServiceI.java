package ru.bellintegrator.school.personnelregistry.api.service;

import ru.bellintegrator.school.personnelregistry.api.view.IdentificationDocumentCatalogView;

import java.util.List;

/**
 * Сервис управления информацией справочника видов документов, удостоверяющих личность физического лица
 */
public interface IdentificationDocumentCatalogServiceI {

    /**
     * Возвращает список стран
     *
     * @return {@OfficeView}
     */
    List<IdentificationDocumentCatalogView> getList();
}
