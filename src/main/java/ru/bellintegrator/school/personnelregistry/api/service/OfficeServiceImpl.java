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

        return sample;
    }

    /**
     * {@inheritDoc}
     */
    public Boolean create(OfficeView param) {
        Boolean result = false;
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public Boolean update(OfficeView param) {
        return false;
    }
}
