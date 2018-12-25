package ru.bellintegrator.school.personnelregistry.api.dao;

import ru.bellintegrator.school.personnelregistry.api.dao.exception.DaoException;
import ru.bellintegrator.school.personnelregistry.api.error.ErrorCode;
import ru.bellintegrator.school.personnelregistry.api.model.CountryCatalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * {@inheritDoc}
 */
@Repository
public class CoutryCatalogDaoImpl implements CountryCatalogDaoI {

    private final EntityManager em;

    @Autowired
    public CoutryCatalogDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CountryCatalog> getList() {
        // составляем запрос по JPA Criteria API
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<CountryCatalog> criteriaQuery = builder.createQuery(CountryCatalog.class);
        Root<CountryCatalog> root = criteriaQuery.from(CountryCatalog.class);
        List<Predicate> predicates = new LinkedList<>();
        criteriaQuery
            .select(root);
        TypedQuery<CountryCatalog> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryCatalog getById(Integer id) throws DaoException {
        CountryCatalog countryCatalog = em.find(CountryCatalog.class, id);
        if (Objects.isNull(countryCatalog)) {
            throw new DaoException(ErrorCode.COUNTRY_CATALOG_SQL_BY_ID_NO_RESULT);
        }
        return countryCatalog;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryCatalog getByCode(String code) throws DaoException {
        try {
            // составляем запрос по JPA Criteria API
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<CountryCatalog> criteriaQuery = builder.createQuery(CountryCatalog.class);
            Root<CountryCatalog> root = criteriaQuery.from(CountryCatalog.class);
            List<Predicate> predicates = new LinkedList<>();
            criteriaQuery
                    .where(builder.equal(root.get("code"), code.trim()))
                    .select(root);
            TypedQuery<CountryCatalog> query = em.createQuery(criteriaQuery);
            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new DaoException(ErrorCode.COUNTRY_CATALOG_SQL_BY_CODE_NO_RESULT, e);
        }
    }
}
