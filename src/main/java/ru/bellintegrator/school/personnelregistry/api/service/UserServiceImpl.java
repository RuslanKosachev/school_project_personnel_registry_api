package ru.bellintegrator.school.personnelregistry.api.service;

import ru.bellintegrator.school.personnelregistry.api.dao.EmployeeDaoI;
import ru.bellintegrator.school.personnelregistry.api.dao.OfficeDaoI;
import ru.bellintegrator.school.personnelregistry.api.dao.exception.DaoException;
import ru.bellintegrator.school.personnelregistry.api.error.ErrorCode;
import ru.bellintegrator.school.personnelregistry.api.model.Employee;
import ru.bellintegrator.school.personnelregistry.api.model.Office;
import ru.bellintegrator.school.personnelregistry.api.model.mapper.MapperFacade;
import ru.bellintegrator.school.personnelregistry.api.view.UserView;
import ru.bellintegrator.school.personnelregistry.api.view.exception.ViewException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * {@inheritDoc}
 */
@Service
public class UserServiceImpl implements UserServiceI {

    private final EmployeeDaoI employeeDao;
    private final OfficeDaoI officeDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public UserServiceImpl(EmployeeDaoI employeeDao, OfficeDaoI officeDao, MapperFacade mapperFacade) {
        this.employeeDao = employeeDao;
        this.officeDao = officeDao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    public List<UserView> getList(UserView filter) {
        //заполним фильтр
        Map<String, Object> map = new HashMap<>();
        if (Objects.nonNull(filter)) {
            map.put("officeId", filter.getOfficeId());
            map.put("firstName", filter.getFirstName());
            map.put("secondName", filter.getSecondName());
            map.put("middleName", filter.getMiddleName());
            map.put("position", filter.getPosition());
            map.put("docCode", filter.getDocCode());
            map.put("citizenshipCode", filter.getCitizenshipCode());
        }
        List<Employee> employees = employeeDao.getList(map);
        return mapperFacade.mapAsList(employees, UserView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public UserView getById(Integer id) throws DaoException {
        Employee employee = employeeDao.getById(id);
        return  mapperFacade.map(employee, UserView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Boolean create(UserView param) throws DaoException, ViewException {
        if (Objects.isNull(param)) {
            throw new ViewException(ErrorCode.USER_V_NULL);
        }

        Office office = officeDao.getById(param.getOfficeId());

        Employee employeeNew = mapperFacade.map(param, Employee.class);
        Employee employeePersist = employeeDao.create(employeeNew);
        office.addEmployee(employeePersist);

        if (Objects.nonNull(employeePersist)) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public Boolean update(UserView userView) throws DaoException, ViewException {
        if (Objects.isNull(userView)) {
            throw new ViewException(ErrorCode.USER_V_NULL);
        }

        Employee employee = mapperFacade.map(userView, Employee.class);
        Employee updatedEmployee = employeeDao.update(employee);

        if (Objects.nonNull(updatedEmployee)) {
            return true;
        }
        return false;
    }
}
