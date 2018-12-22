package ru.bellintegrator.school.personnelregistry.api.dao;

import ru.bellintegrator.school.personnelregistry.api.model.IdentificationDocumentCatalog;

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
public class IdentificationDocumentCatalogDaoImpl implements IdentificationDocumentCatalogDaoI {

    private final EntityManager em;

    @Autowired
    public IdentificationDocumentCatalogDaoImpl(EntityManager em) {
        this.em = em;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<IdentificationDocumentCatalog> getList() {
        // составляем запрос по JPA Criteria API
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<IdentificationDocumentCatalog> criteriaQuery
                = builder.createQuery(IdentificationDocumentCatalog.class);
        Root<IdentificationDocumentCatalog> root = criteriaQuery.from(IdentificationDocumentCatalog.class);
        List<Predicate> predicates = new LinkedList<>();
        criteriaQuery
            .select(root);
        TypedQuery<IdentificationDocumentCatalog> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IdentificationDocumentCatalog getById(Integer id) {
        return em.find(IdentificationDocumentCatalog.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IdentificationDocumentCatalog getByCode(String code) {
        // составляем запрос по JPA Criteria API
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<IdentificationDocumentCatalog> criteriaQuery = builder.createQuery(IdentificationDocumentCatalog.class);
        Root<IdentificationDocumentCatalog> root = criteriaQuery.from(IdentificationDocumentCatalog.class);
        List<Predicate> predicates = new LinkedList<>();
        criteriaQuery
                .where(builder.equal(root.get("code"), code.trim()))
                .select(root);
        TypedQuery<IdentificationDocumentCatalog> query = em.createQuery(criteriaQuery);
        return query.getSingleResult();
    }
}
