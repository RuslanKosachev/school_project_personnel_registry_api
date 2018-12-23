package ru.bellintegrator.school.personnelregistry.api.dao;

import ru.bellintegrator.school.personnelregistry.api.model.Organization;
import ru.bellintegrator.school.personnelregistry.api.view.exception.ErrorMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * {@inheritDoc}
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDaoI {

    private final EntityManager em;

    @Autowired
    public OrganizationDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> getList(Map<String, Object> filter) {
        // составляем запрос по JPA Criteria API
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = builder.createQuery(Organization.class);
        Root<Organization> rootEmployee = criteriaQuery.from(Organization.class);
        List<Predicate> predicates = new LinkedList<>();

        if (Objects.nonNull(filter.get("name"))) {
            predicates.add(builder.equal(rootEmployee.get("name"), filter.get("name")));
        }
        if (Objects.nonNull(filter.get("inn"))) {
            predicates.add(builder.equal(rootEmployee.get("inn"), filter.get("inn")));
        }
        if (Objects.nonNull(filter.get("isActive"))) {
            predicates.add(builder.equal(rootEmployee.get("isActive"), filter.get("isActive")));
        }

        criteriaQuery
            .where(predicates.toArray(new Predicate[]{}))
            .select(rootEmployee);
        TypedQuery<Organization> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization getById(Integer id) {
        return em.find(Organization.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization create(Organization org) {
        // обязательные проверки
        if (Objects.isNull(org)) {
            throw new NullPointerException(ErrorMessage.ARG_NULL);
        }
        if (Objects.isNull(org.getName())) {
            throw new NullPointerException(ErrorMessage.ORGANIZATION_NAME_NULL);
        }
        if (Objects.isNull(org.getFullName())) {
            throw new NullPointerException(ErrorMessage.ORGANIZATION_FULL_NAME_NULL);
        }
        if (Objects.isNull(org.getInn())) {
            throw new NullPointerException(ErrorMessage.ORGANIZATION_INN_NULL);
        }
        if (Objects.isNull(org.getKpp())) {
            throw new NullPointerException(ErrorMessage.ORGANIZATION_KPP_NULL);
        }
        if (Objects.isNull(org.getAddress())) {
            throw new NullPointerException(ErrorMessage.ORGANIZATION_ADDRESS_NULL);
        }
        em.persist(org);
        return org;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization update(Organization org) {
        // обязательные проверки
        if (Objects.isNull(org)) {
            throw new NullPointerException(ErrorMessage.ARG_NULL);
        }
        if (Objects.isNull(org.getId())) {
            throw new NullPointerException(ErrorMessage.OFFICE_ID_NULL);
        }
        // получим объект который нужно изменить
        Organization updatedOrg = em.find(Organization.class, org.getId());
        // обновляем updatedEmployee
        if (Objects.nonNull(updatedOrg)) {
            // setName
            if (Objects.nonNull(org.getName())) {
                updatedOrg.setName(org.getName());
            }
            // FullName
            if (Objects.nonNull(org.getFullName())) {
                updatedOrg.setFullName(org.getFullName());
            }
            // Inn
            if (Objects.nonNull(org.getInn())) {
                updatedOrg.setInn(org.getInn());
            }
            // Kpp
            if (Objects.nonNull(org.getKpp())) {
                updatedOrg.setKpp(org.getKpp());
            }
            // Address
            if (Objects.nonNull(org.getAddress())) {
                updatedOrg.setAddress(org.getAddress());
            }
            // Active
            if (Objects.nonNull(org.getIsActive())) {
                updatedOrg.setIsActive(org.getIsActive());
            }
            // Phone
            if (Objects.nonNull(org.getPhone())) {
                updatedOrg.setPhone(org.getPhone());
            }

            // фиксируем изменения
            em.merge(updatedOrg);
        } else {
            throw new NullPointerException("не найден объект запроса Organization");
        }

        return updatedOrg;
    }
}
