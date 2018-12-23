package ru.bellintegrator.school.personnelregistry.api.dao;

import ru.bellintegrator.school.personnelregistry.api.model.Employee;
import ru.bellintegrator.school.personnelregistry.api.model.Office;
import ru.bellintegrator.school.personnelregistry.api.model.EmployeeDocument;
import ru.bellintegrator.school.personnelregistry.api.model.IdentificationDocumentCatalog;
import ru.bellintegrator.school.personnelregistry.api.model.CountryCatalog;
import ru.bellintegrator.school.personnelregistry.api.view.exception.ErrorMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.SetJoin;
import javax.persistence.NoResultException;
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
    public EmployeeDaoImpl(EntityManager em, CountryCatalogDaoI countryCatalogDao, IdentificationDocumentCatalogDaoI identificationDocumentCatalogDao) {
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
        Root<Employee> rootEmployee = criteriaQuery.from(Employee.class);
        List<Predicate> predicates = new LinkedList<>();

        if (Objects.nonNull(filter.get("officeId"))) {
            // todo нужно оптимизировать
            SetJoin<Employee, Office> joinUserOffice = rootEmployee.joinSet("offices");

            Office office = em.find(Office.class, filter.get("officeId"));
            predicates.add(joinUserOffice.in(office));
        }
        if (Objects.nonNull(filter.get("firstName"))) {
            predicates.add(builder.equal(rootEmployee.get("firstName"), filter.get("firstName")));
        }
        if (Objects.nonNull(filter.get("secondName"))) {
            predicates.add(builder.equal(rootEmployee.get("secondName"), filter.get("secondName")));
        }
        if (Objects.nonNull(filter.get("middleName"))) {
            predicates.add(builder.equal(rootEmployee.get("middleName"), filter.get("middleName")));
        }
        if (Objects.nonNull(filter.get("position"))) {
            predicates.add(builder.equal(rootEmployee.get("position"), filter.get("position")));
        }
        if (Objects.nonNull(filter.get("docCode"))) {
            predicates.add(builder.equal(rootEmployee.get("employeeDocument").get("documentCatalog").get("code"),
                                         filter.get("docCode")));
        }
        if (Objects.nonNull(filter.get("citizenshipCode"))) {
            predicates.add(builder.equal(rootEmployee.get("country").get("code"), filter.get("citizenshipCode")));
        }

        criteriaQuery
            .where(predicates.toArray(new Predicate[]{}))
            .select(rootEmployee);
        TypedQuery<Employee> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee getById(Integer id) {
        return em.find(Employee.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee create(Employee employee) {
        // обязательные проверки
        if (Objects.isNull(employee)) {
            throw new NullPointerException(ErrorMessage.ARG_NULL);
        }
        if (Objects.isNull(employee.getFirstName())) {
            throw new NullPointerException(ErrorMessage.USER_FIRST_NAME_NULL);
        }
        if (Objects.isNull(employee.getPosition())) {
            throw new NullPointerException(ErrorMessage.USER_POSITION_NULL);
        }

        // EmployeeDocument если есть документ то свяжем его с сотрудником
        EmployeeDocument employeeDocument = employee.getEmployeeDocument();
        if (Objects.nonNull(employeeDocument)) {
            // если указан тип документа удост. личность
            IdentificationDocumentCatalog identificationDocumentCatalog = employeeDocument.getDocumentCatalog();
            if (Objects.nonNull(identificationDocumentCatalog)) {
                identificationDocumentCatalog
                        = identificationDocumentCatalogDao.getByCode(identificationDocumentCatalog.getCode());
                if (Objects.nonNull(identificationDocumentCatalog)) {
                    employeeDocument.setDocumentCatalog(identificationDocumentCatalog);
                } else {
                    throw new NullPointerException(" не найден объект запроса IdentificationDocumentCatalog");
                }
            }
            // todo реализовать добавление документа к сотруднику
            employeeDocument.setEmployee(employee);
        }
        // если указано гражданство через код
        CountryCatalog countryCatalog = employee.getCountry();
        if (Objects.nonNull(countryCatalog)) {
            countryCatalog = countryCatalogDao.getByCode(countryCatalog.getCode());
            if (Objects.nonNull(countryCatalog)) {
                employee.setCountry(countryCatalog);
            } else {
                throw new NullPointerException("не найден объект запроса CountryCatalog");
            }
        }

        em.persist(employee);
        return employee;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Employee update(Employee employee) {
        // обязательные проверки
        if (Objects.isNull(employee)) {
            throw new NullPointerException(ErrorMessage.ARG_NULL);
        }
        if (Objects.isNull(employee.getId())) {
            throw new NullPointerException(ErrorMessage.USER_ID_NULL);
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
                    if (Objects.nonNull(identificationDocumentCatalog)) {
                        updatedEmployeeDocument.setDocumentCatalog(identificationDocumentCatalog);
                    } else {
                        throw new NullPointerException(" не найден объект запроса IdentificationDocumentCatalog");
                    }
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
            // CountryCatalog если указано гражданство через код
            CountryCatalog countryCatalog = employee.getCountry();
            if (Objects.nonNull(countryCatalog)) {
                countryCatalog = countryCatalogDao.getByCode(countryCatalog.getCode());
                if (Objects.nonNull(countryCatalog)) {
                    updatedEmployee.setCountry(countryCatalog);
                } else {
                    throw new NullPointerException("не найден объект запроса IdentificationDocumentCatalog");
                }
            }
            // фиксируем изменения
            em.merge(updatedEmployee);
        } else {
            throw new NullPointerException("не найден объект запроса Employee");
        }

        return updatedEmployee;
    }
}