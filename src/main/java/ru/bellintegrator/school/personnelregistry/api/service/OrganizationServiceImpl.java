package ru.bellintegrator.school.personnelregistry.api.service;

import ru.bellintegrator.school.personnelregistry.api.dao.OrganizationDaoI;
import ru.bellintegrator.school.personnelregistry.api.dao.exception.DaoException;
import ru.bellintegrator.school.personnelregistry.api.error.ErrorCode;
import ru.bellintegrator.school.personnelregistry.api.model.Organization;
import ru.bellintegrator.school.personnelregistry.api.model.mapper.MapperFacade;
import ru.bellintegrator.school.personnelregistry.api.view.OrganizationView;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.school.personnelregistry.api.view.exception.ViewException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * {@inheritDoc}
 */
@Service
public class OrganizationServiceImpl implements OrganizationServiceI {

    private final OrganizationDaoI orgDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OrganizationServiceImpl(OrganizationDaoI orgDao, MapperFacade mapperFacade) {
        this.orgDao = orgDao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public List<OrganizationView> getList(OrganizationView filter) {
        //заполним фильтр
        Map<String, Object> map = new HashMap<>();
        if (Objects.nonNull(filter)) {
            map.put("name", filter.getName());
            map.put("inn", filter.getInn());
            map.put("isActive", filter.getIsActive());
        }
        List<Organization> office = orgDao.getList(map);
        return mapperFacade.mapAsList(office, OrganizationView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public OrganizationView getById(Integer id) throws DaoException {
        Organization office = orgDao.getById(id);
        return  mapperFacade.map(office, OrganizationView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public  Boolean create(OrganizationView param) throws ViewException, DaoException {
        if (Objects.isNull(param)) {
            throw new ViewException(ErrorCode.ORG_V_NULL);
        }

        Organization orgNew = mapperFacade.map(param, Organization.class);
        Organization orgPersist = orgDao.create(orgNew);

        if (Objects.nonNull(orgPersist)) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public Boolean update(OrganizationView param) throws ViewException, DaoException {
        if (Objects.isNull(param)) {
            throw new ViewException(ErrorCode.ORG_V_NULL);
        }

        Organization org = mapperFacade.map(param, Organization.class);
        Organization orgUpdated = orgDao.update(org);

        if (Objects.nonNull(orgUpdated)) {
            return true;
        }
        return false;
    }
}
