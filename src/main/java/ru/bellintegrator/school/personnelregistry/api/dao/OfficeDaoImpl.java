package ru.bellintegrator.school.personnelregistry.api.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.school.personnelregistry.api.model.*;
import ru.bellintegrator.school.personnelregistry.api.view.exception.ErrorMessage;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * {@inheritDoc}
 */
@Repository
public class OfficeDaoImpl implements OfficeDaoI {

    private final EntityManager em;

    @Autowired
    public OfficeDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Office> getList(Map<String, Object> filter) {
        // составляем запрос по JPA Criteria API
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = builder.createQuery(Office.class);
        Root<Office> rootEmployee = criteriaQuery.from(Office.class);
        List<Predicate> predicates = new LinkedList<>();

        if (Objects.nonNull(filter.get("orgId"))) {
            predicates.add(builder.equal(rootEmployee.get("organization").get("id"), filter.get("orgId")));
        }
        if (Objects.nonNull(filter.get("name"))) {
            predicates.add(builder.equal(rootEmployee.get("name"), filter.get("name")));
        }
        if (Objects.nonNull(filter.get("phone"))) {
            predicates.add(builder.equal(rootEmployee.get("phone"), filter.get("phone")));
        }
        if (Objects.nonNull(filter.get("isActive"))) {
            predicates.add(builder.equal(rootEmployee.get("isActive"), filter.get("isActive")));
        }

        criteriaQuery
            .where(predicates.toArray(new Predicate[]{}))
            .select(rootEmployee);
        TypedQuery<Office> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office getById(Integer id) {
        return em.find(Office.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office create(Office office) {
        // обязательные проверки
        if (Objects.isNull(office)) {
            throw new NullPointerException(ErrorMessage.ARG_NULL);
        }
        if (Objects.isNull(office.getName())) {
            throw new NullPointerException(ErrorMessage.OFFICE_NAME_NULL);
        }
        if (Objects.isNull(office.getAddress())) {
            throw new NullPointerException(ErrorMessage.OFFICE_ADDRESS_NULL);
        }

        em.persist(office);
        return office;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office update(Office office) {
        // обязательные проверки
        if (Objects.isNull(office)) {
            throw new NullPointerException(ErrorMessage.ARG_NULL);
        }
        if (Objects.isNull(office.getId())) {
            throw new NullPointerException(ErrorMessage.OFFICE_ID_NULL);
        }
        // получим объект который нужно изменить
        Office updatedOffice = em.find(Office.class, office.getId());
        // обновляем updatedEmployee
        if (Objects.nonNull(updatedOffice)) {
            // setName
            if (Objects.nonNull(office.getName())) {
                updatedOffice.setName(office.getName());
            }
            // setAddress
            if (Objects.nonNull(office.getAddress())) {
                updatedOffice.setAddress(office.getAddress());
            }
            // setActive
            if (Objects.nonNull(office.getActive())) {
                updatedOffice.setActive(office.getActive());
            }
            // setPhone
            if (Objects.nonNull(office.getPhone())) {
                updatedOffice.setPhone(office.getPhone());
            }

            // фиксируем изменения
            em.merge(updatedOffice);
        } else {
            throw new NullPointerException("не найден объект запроса Office");
        }

        return updatedOffice;
    }
}
