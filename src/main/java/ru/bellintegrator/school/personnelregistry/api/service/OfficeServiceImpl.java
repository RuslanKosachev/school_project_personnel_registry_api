package ru.bellintegrator.school.personnelregistry.api.service;

import ru.bellintegrator.school.personnelregistry.api.dao.OfficeDaoI;
import ru.bellintegrator.school.personnelregistry.api.dao.OrganizationDaoI;
import ru.bellintegrator.school.personnelregistry.api.dao.exception.DaoException;
import ru.bellintegrator.school.personnelregistry.api.error.ErrorCode;
import ru.bellintegrator.school.personnelregistry.api.model.Office;
import ru.bellintegrator.school.personnelregistry.api.model.Organization;
import ru.bellintegrator.school.personnelregistry.api.model.mapper.MapperFacade;
import ru.bellintegrator.school.personnelregistry.api.view.OfficeView;

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
public class OfficeServiceImpl implements OfficeServiceI {

    private final OfficeDaoI officeDao;
    private final OrganizationDaoI orgDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OfficeServiceImpl(MapperFacade mapperFacade, OfficeDaoI officeDao, OrganizationDaoI orgDao) {
        this.officeDao = officeDao;
        this.orgDao = orgDao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public List<OfficeView> getList(OfficeView filter) {
        //заполним фильтр
        Map<String, Object> map = new HashMap<>();
        if(Objects.nonNull(filter)) {
            map.put("orgId", filter.getOrgId());
            map.put("name", filter.getName());
            map.put("phone", filter.getPhone());
            map.put("isActive", filter.getIsActive());
        }
        List<Office> office = officeDao.getList(map);
        return mapperFacade.mapAsList(office, OfficeView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public OfficeView getById(Integer id) throws DaoException {
        Office office = officeDao.getById(id);
        return  mapperFacade.map(office, OfficeView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Boolean create(OfficeView param) throws DaoException, ViewException {
        if (Objects.isNull(param)) {
            throw new ViewException(ErrorCode.OFFICE_V_NULL);
        }

        Organization organization = orgDao.getById(param.getOrgId());

        Office officeNew = mapperFacade.map(param, Office.class);
        officeNew.setOrganization(organization);

        Office officePersist = officeDao.create(officeNew);
        if ( Objects.nonNull(officePersist)) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public Boolean update(OfficeView param) throws DaoException, ViewException {
        if (Objects.isNull(param)) {
            throw new ViewException(ErrorCode.OFFICE_V_NULL);
        }

        Office office = mapperFacade.map(param, Office.class);
        Office officeUpdated = officeDao.update(office);

        if (Objects.nonNull(officeUpdated)) {
            return true;
        }
        return false;
    }
}
