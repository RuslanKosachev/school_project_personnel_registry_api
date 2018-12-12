package ru.bellintegrator.school.personnelregistry.api.service;

import org.springframework.stereotype.Service;
import ru.bellintegrator.school.personnelregistry.api.view.OfficeView;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class OfficeServiceImpl implements OfficeServiceI {

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
