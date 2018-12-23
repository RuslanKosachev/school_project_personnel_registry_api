package ru.bellintegrator.school.personnelregistry.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.bellintegrator.school.personnelregistry.api.dao.IdentificationDocumentCatalogDaoI;
import ru.bellintegrator.school.personnelregistry.api.model.IdentificationDocumentCatalog;
import ru.bellintegrator.school.personnelregistry.api.model.mapper.MapperFacade;
import ru.bellintegrator.school.personnelregistry.api.view.IdentificationDocumentCatalogView;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
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
        IdentificationDocumentCatalogView sampleDocument1 = new IdentificationDocumentCatalogView();
        sampleDocument1.setId(1);
        sampleDocument1.setCode("3");
        sampleDocument1.setName("Свидетельство о рождении");

        IdentificationDocumentCatalogView sampleDocument2 = new IdentificationDocumentCatalogView();
        sampleDocument2.setId(2);
        sampleDocument2.setCode("7");
        sampleDocument2.setName("Военный билет");

        IdentificationDocumentCatalogView sampleDocument3 = new IdentificationDocumentCatalogView();
        sampleDocument3.setId(10);
        sampleDocument3.setCode("21");
        sampleDocument3.setName("Паспорт гражданина Российской Федерации");

        List<IdentificationDocumentCatalogView> listCountries = new LinkedList<>();
        listCountries.add(0, sampleDocument1);
        listCountries.add(1, sampleDocument2);
        listCountries.add(2, sampleDocument3);

        List<IdentificationDocumentCatalog> identDocCatalog = identDocCatalogDao.getList();
        return mapperFacade.mapAsList(identDocCatalog, IdentificationDocumentCatalogView.class);
    }
}
