package ru.bellintegrator.school.personnelregistry.api.dao;

import ru.bellintegrator.school.personnelregistry.api.dao.exception.DaoException;
import ru.bellintegrator.school.personnelregistry.api.error.ErrorCode;
import ru.bellintegrator.school.personnelregistry.api.model.Employee;
import ru.bellintegrator.school.personnelregistry.api.model.Office;
import ru.bellintegrator.school.personnelregistry.api.model.EmployeeDocument;
import ru.bellintegrator.school.personnelregistry.api.model.IdentificationDocumentCatalog;
import ru.bellintegrator.school.personnelregistry.api.model.CountryCatalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.SetJoin;
import java.util.*;

/**
 * {@inheritDoc}
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDaoI {

    private final EntityManager em;
    private final CountryCatalogDaoI countryCatalogDao;
    private final IdentificationDocumentCatalogDaoI identificationDocumentCatalogDao;


    @Autowired
    public EmployeeDaoImpl(EntityManager em,
                           CountryCatalogDaoI countryCatalogDao,
                           IdentificationDocumentCatalogDaoI identificationDocumentCatalogDao) {
        this.em = em;
        this.countryCatalogDao = countryCatalogDao;
        this.identificationDocumentCatalogDao = identificationDocumentCatalogDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Employee> getList(Map<String, Object> filter) {
        // составляем запрос по JPA Criteria API
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = builder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        List<Predicate> predicates = new LinkedList<>();

        if (Objects.nonNull(filter.get("officeId"))) {
            SetJoin<Employee, Office> joinUserOffice = root.joinSet("offices");

            Office office = em.find(Office.class, filter.get("officeId"));
            predicates.add(joinUserOffice.in(office));
        }
        if (Objects.nonNull(filter.get("firstName"))) {
            predicates.add(builder.equal(root.get("firstName"), filter.get("firstName")));
        }
        if (Objects.nonNull(filter.get("secondName"))) {
            predicates.add(builder.equal(root.get("secondName"), filter.get("secondName")));
        }
        if (Objects.nonNull(filter.get("middleName"))) {
            predicates.add(builder.equal(root.get("middleName"), filter.get("middleName")));
        }
        if (Objects.nonNull(filter.get("position"))) {
            predicates.add(builder.equal(root.get("position"), filter.get("position")));
        }
        if (Objects.nonNull(filter.get("docCode"))) {
            predicates.add(builder.equal(root.get("employeeDocument").get("documentCatalog").get("code"),
                                         filter.get("docCode")));
        }
        if (Objects.nonNull(filter.get("citizenshipCode"))) {
            predicates.add(builder.equal(root.get("country").get("code"), filter.get("citizenshipCode")));
        }

        criteriaQuery
            .where(predicates.toArray(new Predicate[]{}))
            .select(root);
        TypedQuery<Employee> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee getById(Integer id) throws DaoException {
        Employee employee = em.find(Employee.class, id);
        if (Objects.isNull(employee)) {
            throw new DaoException(ErrorCode.EMPLOYEE_SQL_BY_ID_NO_RESULT);
        }
        return employee;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee create(Employee employee) throws DaoException {
        // обязательные проверки
        if (Objects.isNull(employee)) {
            throw new DaoException(ErrorCode.EMPLOYEE_NULL);
        }
        if (Objects.isNull(employee.getFirstName())) {
            throw new DaoException(ErrorCode.EMPLOYEE_FNAME_NULL);
        }
        if (Objects.isNull(employee.getPosition())) {
            throw new DaoException(ErrorCode.EMPLOYEE_POSITION_NULL);
        }

        // EmployeeDocument если есть документ то свяжем его с сотрудником
        EmployeeDocument employeeDocument = employee.getEmployeeDocument();
        if (Objects.nonNull(employeeDocument)) {
            // если указан тип документа удост. личность
            IdentificationDocumentCatalog identificationDocumentCatalog = employeeDocument.getDocumentCatalog();
            if (Objects.nonNull(identificationDocumentCatalog)) {
                identificationDocumentCatalog
                        = identificationDocumentCatalogDao.getByCode(identificationDocumentCatalog.getCode());
                employeeDocument.setDocumentCatalog(identificationDocumentCatalog);
            }
            // todo реализовать добавление документа к сотруднику
            employeeDocument.setEmployee(employee);
        }
        // если указано гражданство через код
        CountryCatalog countryCatalog = employee.getCountry();
        if (Objects.nonNull(countryCatalog)) {
            countryCatalog = countryCatalogDao.getByCode(countryCatalog.getCode());
            employee.setCountry(countryCatalog);
        }

        em.persist(employee);
        return employee;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee update(Employee employee) throws DaoException {
        // обязательные проверки
        if (Objects.isNull(employee)) {
            throw new DaoException(ErrorCode.EMPLOYEE_NULL);
        }
        if (Objects.isNull(employee.getId())) {
            throw new DaoException(ErrorCode.EMPLOYEE_ID_NULL);
        }
        // получим объект который нужно изменить
        Employee updatedEmployee = em.find(Employee.class, employee.getId());
        // обновляем updatedEmployee
        if (Objects.nonNull(updatedEmployee)) {
            // firstName
            if (Objects.nonNull(employee.getFirstName())) {
                updatedEmployee.setFirstName(employee.getFirstName().trim());
            }
            // secondName
            if (Objects.nonNull(employee.getSecondName())) {
                updatedEmployee.setSecondName(employee.getSecondName().trim());
            }
            // middleName
            if (Objects.nonNull(employee.getMiddleName())) {
                updatedEmployee.setMiddleName(employee.getMiddleName().trim());
            }
            // position
            if (Objects.nonNull(employee.getPosition())) {
                updatedEmployee.setPosition(employee.getPosition().trim());
            }
            // phone
            if (Objects.nonNull(employee.getPhone())) {
                updatedEmployee.setPhone(employee.getPhone().trim());
            }
            // isIdentified
            if (Objects.nonNull(employee.getIsIdentified())) {
                updatedEmployee.setIsIdentified(employee.getIsIdentified());
            }

            // EmployeeDocument
            EmployeeDocument employeeDocument = employee.getEmployeeDocument();
            if (Objects.nonNull(employeeDocument)) {
                EmployeeDocument updatedEmployeeDocument = updatedEmployee.getEmployeeDocument();
                // Создадим новый если нет документа
                if (Objects.isNull(updatedEmployeeDocument)) {
                    updatedEmployeeDocument = new EmployeeDocument();
                    em.persist(updatedEmployeeDocument);
                }
                // если указан тип документа удост. личность
                IdentificationDocumentCatalog identificationDocumentCatalog = employeeDocument.getDocumentCatalog();
                if (Objects.nonNull(identificationDocumentCatalog)) {
                    identificationDocumentCatalog
                            = identificationDocumentCatalogDao.getByCode(identificationDocumentCatalog.getCode());
                    updatedEmployeeDocument.setDocumentCatalog(identificationDocumentCatalog);
                }
                // docName
                if (Objects.nonNull(employeeDocument.getName())) {
                    updatedEmployeeDocument.setName(employeeDocument.getName());
                }
                // docNumber
                if (Objects.nonNull(employeeDocument.getNumber())) {
                    updatedEmployeeDocument.setNumber(employeeDocument.getNumber());
                }
                // docDate
                if (Objects.nonNull(employeeDocument.getDate())) {
                    updatedEmployeeDocument.setDate(employeeDocument.getDate());
                }
                // todo реализовать добавление документа к сотруднику
                updatedEmployeeDocument.setEmployee(updatedEmployee);
            }
            // CountryCatalog если указано гражданство через код государства
            CountryCatalog countryCatalog = employee.getCountry();
            if (Objects.nonNull(countryCatalog)) {
                countryCatalog = countryCatalogDao.getByCode(countryCatalog.getCode());
                updatedEmployee.setCountry(countryCatalog);
            }
            // фиксируем изменения
            return em.merge(updatedEmployee);
        } else {
            // todo нужна ошибка обновления сотрудника
            throw new DaoException(ErrorCode.EMPLOYEE_SQL_BY_ID_NO_RESULT);
        }
    }
}