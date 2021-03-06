package ru.bellintegrator.school.personnelregistry.api.dao;

import ru.bellintegrator.school.personnelregistry.api.dao.exception.DaoException;
import ru.bellintegrator.school.personnelregistry.api.error.ErrorCode;
import ru.bellintegrator.school.personnelregistry.api.model.IdentificationDocumentCatalog;

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
        criteriaQuery
            .select(root);
        TypedQuery<IdentificationDocumentCatalog> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IdentificationDocumentCatalog getById(Integer id) throws DaoException {
        IdentificationDocumentCatalog identificationDocumentCatalog = em.find(IdentificationDocumentCatalog.class, id);
        if (Objects.isNull(identificationDocumentCatalog)) {
            throw new DaoException(ErrorCode.IDENTIFICATION_DOCUMENT_CATALOG_SQL_BY_ID_NO_RESULT);
        }
        return identificationDocumentCatalog;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IdentificationDocumentCatalog getByCode(String code) throws DaoException {
        try {
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
        } catch (NoResultException e) {
            throw new DaoException(ErrorCode.IDENTIFICATION_DOCUMENT_CATALOG_SQL_BY_CODE_NO_RESULT, e);
        }
    }
}
