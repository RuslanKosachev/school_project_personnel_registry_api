package ru.bellintegrator.school.personnelregistry.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.school.personnelregistry.api.model.Office;
import ru.bellintegrator.school.personnelregistry.api.view.OrganizationView;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class OrganizationServiceImpl implements OrganizationServiceI {

    private final EntityManager em;

    @Autowired
    public OrganizationServiceImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    public List<OrganizationView> getList(OrganizationView filter) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public OrganizationView getById(Integer id) {
        OrganizationView sample = new OrganizationView();
        sample.setName("СГТУ имени Гагарина Ю.А.");
        sample.setFullName("ФГБОУ ВО Саратовский государственный технический университет");
        sample.setInn("8894103143");
        sample.setKpp("794561321");
        sample.setAddress("г.Саратов");
        sample.setId(id);

        return sample;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public  Boolean create(OrganizationView param) {
        Boolean result = false;
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Boolean update(OrganizationView param) {
        return false;
    }
}
