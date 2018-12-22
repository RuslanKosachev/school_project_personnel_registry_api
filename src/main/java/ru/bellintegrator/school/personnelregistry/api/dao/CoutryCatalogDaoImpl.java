package ru.bellintegrator.school.personnelregistry.api.dao;

import ru.bellintegrator.school.personnelregistry.api.model.CountryCatalog;

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
    public CountryCatalog getById(Integer id) {
        return em.find(CountryCatalog.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CountryCatalog getByCode(String code) {
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
    }
}
