package ru.bellintegrator.school.personnelregistry.api.service;

import ru.bellintegrator.school.personnelregistry.api.dao.EmployeeDao;
import ru.bellintegrator.school.personnelregistry.api.model.Employee;
import ru.bellintegrator.school.personnelregistry.api.model.mapper.MapperFacade;
import ru.bellintegrator.school.personnelregistry.api.view.UserView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * {@inheritDoc}
 */
@Service
public class UserServiceImpl implements UserServiceI {

    private final EmployeeDao employeeDao;
    private final MapperFacade mapperFacade;

    @Autowired
    public UserServiceImpl(EmployeeDao employeeDao, MapperFacade mapperFacade) {
        this.employeeDao = employeeDao;
        this.mapperFacade = mapperFacade;
    }

    /**
     * {@inheritDoc}
     */
    public List<UserView> getList(UserView filter) {
        //заполним фильтр
        Map<String, Object> map = new HashMap<>();
        map.put("officeId", filter.getOfficeId());
        map.put("firstName", filter.getFirstName());
        map.put("secondName", filter.getSecondName());
        map.put("middleName", filter.getMiddleName());
        map.put("position(", filter.getPosition());
        map.put("docCode", filter.getDocCode());
        map.put("citizenshipCode", filter.getCitizenshipCode());

        List<Employee> employees = employeeDao.getList(map);
        return mapperFacade.mapAsList(employees, UserView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public UserView getById(Integer id) {
        Employee employee = employeeDao.getById(id);
        return  mapperFacade.map(employee, UserView.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Boolean create(UserView userView) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    public Boolean update(UserView userView) {
        Employee employee = mapperFacade.map(userView, Employee.class);

        Employee updatedEmployee = employeeDao.update(employee);

        if (Objects.nonNull(updatedEmployee)) {
            return true;
        }
        return false;
    }
}
