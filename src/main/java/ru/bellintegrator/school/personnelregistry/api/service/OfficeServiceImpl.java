package ru.bellintegrator.school.personnelregistry.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bellintegrator.school.personnelregistry.api.dao.EmployeeDaoI;
import ru.bellintegrator.school.personnelregistry.api.dao.OfficeDaoI;
import ru.bellintegrator.school.personnelregistry.api.model.Employee;
import ru.bellintegrator.school.personnelregistry.api.model.Office;
import ru.bellintegrator.school.personnelregistry.api.model.Organization;
import ru.bellintegrator.school.personnelregistry.api.model.mapper.MapperFacade;
import ru.bellintegrator.school.personnelregistry.api.view.OfficeView;
import ru.bellintegrator.school.personnelregistry.api.view.UserView;
import ru.bellintegrator.school.personnelregistry.api.view.exception.ErrorMessage;

import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * {@inheritDoc}
 */
@Service
public class OfficeServiceImpl implements OfficeServiceI {

    // todo del del del del
    @Autowired
    private  EntityManager em;

    private final OfficeDaoI officeDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OfficeServiceImpl(OfficeDaoI officeDao, MapperFacade mapperFacade) {
        this.officeDao = officeDao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    public List<OfficeView> getList(OfficeView filter) {
        //заполним фильтр
        Map<String, Object> map = new HashMap<>();
        map.put("orgId", filter.getOrgId());
        map.put("name", filter.getName());
        map.put("phone", filter.getPhone());
        map.put("isActive", filter.getIsActive());

        List<Office> office = officeDao.getList(map);
        return mapperFacade.mapAsList(office, OfficeView.class);
    }

    /**
     * {@inheritDoc}
     */
    public OfficeView getById(Integer id) {
        Office office = officeDao.getById(id);
        return  mapperFacade.map(office, OfficeView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Boolean create(OfficeView param) {
        if (Objects.isNull(param.getOrgId())) {
            throw new NullPointerException(ErrorMessage.ORGANIZATION_ID_NULL);
        }

        Office officeNew = mapperFacade.map(param, Office.class);
        Office officePersist = officeDao.create(officeNew);

        // todo выполнять office в organizationDao
        Organization organization = em.find(Organization.class, param.getOrgId());

        if (Objects.nonNull(officePersist) && Objects.nonNull(organization)) {
            officePersist.setOrganization(organization);
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public Boolean update(OfficeView param) {
        Office office = mapperFacade.map(param, Office.class);
        Office officeUpdated = officeDao.update(office);

        if (Objects.nonNull(officeUpdated)) {
            return true;
        }
        return false;
    }
}
