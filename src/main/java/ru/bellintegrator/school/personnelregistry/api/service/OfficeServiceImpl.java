package ru.bellintegrator.school.personnelregistry.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.school.personnelregistry.api.model.Office;
import ru.bellintegrator.school.personnelregistry.api.view.OfficeView;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class OfficeServiceImpl implements OfficeServiceI {

    private final EntityManager em;

    @Autowired
    public OfficeServiceImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    public List<OfficeView> getList(OfficeView filter) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public OfficeView getById(Integer id) {
        OfficeView sample = new OfficeView();
        sample.setId(id);
        sample.setName("Институт электронной техники и машиностроения");
        sample.setAddress("г.Саратов");
        sample.setOrgId(1);

        return sample;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Boolean create(OfficeView param) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public Boolean update(OfficeView param) {
        return true;
    }
}
