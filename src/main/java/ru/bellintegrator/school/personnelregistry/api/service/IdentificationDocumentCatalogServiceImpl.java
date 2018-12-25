package ru.bellintegrator.school.personnelregistry.api.service;

import ru.bellintegrator.school.personnelregistry.api.dao.IdentificationDocumentCatalogDaoI;
import ru.bellintegrator.school.personnelregistry.api.model.IdentificationDocumentCatalog;
import ru.bellintegrator.school.personnelregistry.api.model.mapper.MapperFacade;
import ru.bellintegrator.school.personnelregistry.api.view.IdentificationDocumentCatalogView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class IdentificationDocumentCatalogServiceImpl implements IdentificationDocumentCatalogServiceI {

    private final IdentificationDocumentCatalogDaoI identDocCatalogDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public IdentificationDocumentCatalogServiceImpl(IdentificationDocumentCatalogDaoI identDocCatalogDao, MapperFacade mapperFacade) {
        this.identDocCatalogDao = identDocCatalogDao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public List<IdentificationDocumentCatalogView> getList() {
        List<IdentificationDocumentCatalog> identDocCatalog = identDocCatalogDao.getList();
        return mapperFacade.mapAsList(identDocCatalog, IdentificationDocumentCatalogView.class);
    }
}
