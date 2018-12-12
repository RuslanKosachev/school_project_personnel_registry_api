package ru.bellintegrator.school.personnelregistry.api.service;

import org.springframework.stereotype.Service;
import ru.bellintegrator.school.personnelregistry.api.view.OrganizationView;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class OrganizationServiceImpl implements OrganizationServiceI {

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
