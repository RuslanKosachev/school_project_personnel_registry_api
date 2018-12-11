package ru.bellintegrator.school.personnelregistry.api.service;

import ru.bellintegrator.school.personnelregistry.api.view.CountryCatalogView;

import java.util.List;

/**
 * Сервис управления информацией справочника стран
 */
public interface CountryCatalogServiceI {

    /**
     * Возвращает список стран
     *
     * @return {@OfficeView}
     */
    List<CountryCatalogView> getList();
}
