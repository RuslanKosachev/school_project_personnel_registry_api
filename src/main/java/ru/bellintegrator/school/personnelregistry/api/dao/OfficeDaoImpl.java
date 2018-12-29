package ru.bellintegrator.school.personnelregistry.api.dao;

import ru.bellintegrator.school.personnelregistry.api.dao.exception.DaoException;
import ru.bellintegrator.school.personnelregistry.api.error.ErrorCode;
import ru.bellintegrator.school.personnelregistry.api.model.Office;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;
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
        Root<Office> root = criteriaQuery.from(Office.class);
        List<Predicate> predicates = new LinkedList<>();

        if (Objects.nonNull(filter.get("orgId"))) {
            predicates.add(builder.equal(root.get("organization").get("id"), filter.get("orgId")));
        }
        if (Objects.nonNull(filter.get("name"))) {
            predicates.add(builder.equal(root.get("name"), filter.get("name")));
        }
        if (Objects.nonNull(filter.get("phone"))) {
            predicates.add(builder.equal(root.get("phone"), filter.get("phone")));
        }
        if (Objects.nonNull(filter.get("isActive"))) {
            predicates.add(builder.equal(root.get("isActive"), filter.get("isActive")));
        }
        criteriaQuery
            .where(predicates.toArray(new Predicate[]{}))
            .select(root);
        TypedQuery<Office> query = em.createQuery(criteriaQuery);

        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office getById(Integer id) throws DaoException {
        Office office = em.find(Office.class, id);
        if (Objects.isNull(office)) {
            throw new DaoException(ErrorCode.OFFICE_SQL_BY_ID_NO_RESULT);
        }
        return office;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office create(Office office) throws DaoException {
        // обязательные проверки
        if (Objects.isNull(office)) {
            throw new DaoException(ErrorCode.OFFICE_NULL);
        }
        if (Objects.isNull(office.getName())) {
            throw new DaoException(ErrorCode.OFFICE_NAME_NULL);
        }
        if (Objects.isNull(office.getAddress())) {
            throw new DaoException(ErrorCode.OFFICE_ADDRESS_NULL);
        }
        em.persist(office);
        return office;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Office update(Office office) throws DaoException {
        // обязательные проверки
        if (Objects.isNull(office)) {
            throw new DaoException(ErrorCode.OFFICE_NULL);
        }
        if (Objects.isNull(office.getId())) {
            throw new DaoException(ErrorCode.OFFICE_ID_NULL);
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
            if (Objects.nonNull(office.getIsActive())) {
                updatedOffice.setIsActive(office.getIsActive());
            }
            // setPhone
            if (Objects.nonNull(office.getPhone())) {
                updatedOffice.setPhone(office.getPhone());
            }
            // фиксируем изменения
            return em.merge(updatedOffice);
        } else {
            throw new DaoException(ErrorCode.OFFICE_SQL_BY_ID_NO_RESULT);
        }
    }
}
