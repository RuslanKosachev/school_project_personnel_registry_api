package ru.bellintegrator.school.personnelregistry.api.service;

import ru.bellintegrator.school.personnelregistry.api.dao.CountryCatalogDaoI;
import ru.bellintegrator.school.personnelregistry.api.model.CountryCatalog;
import ru.bellintegrator.school.personnelregistry.api.model.mapper.MapperFacade;
import ru.bellintegrator.school.personnelregistry.api.view.CountryCatalogView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class CountryCatalogServiceImpl implements CountryCatalogServiceI {

    private final CountryCatalogDaoI countryCatalogDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public CountryCatalogServiceImpl(CountryCatalogDaoI countryCatalogDao, MapperFacade mapperFacade) {
        this.countryCatalogDao = countryCatalogDao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public List<CountryCatalogView> getList() {
        List<CountryCatalog> countryCatalog = countryCatalogDao.getList();
        return mapperFacade.mapAsList(countryCatalog, CountryCatalogView.class);
    }
}

