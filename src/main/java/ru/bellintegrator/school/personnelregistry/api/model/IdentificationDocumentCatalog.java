package ru.bellintegrator.school.personnelregistry.api.model;

import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** Сущность типа документа удостоверяющего личность */
@Entity
@Table(name = "identification_document_catalog")
public class IdentificationDocumentCatalog {

    /**
     * Уникальный дентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Служебное поле hibernate
     */
    @Version
    private Integer version;

    /**
     * Наименование типа документа
     */
    @Column(name = "name", length = 250, nullable = false)
    private String name;

    /**
     * Код типа документа
     */
    @Column(name = "code", length = 3, nullable = false)
    private String code;

    /** Конструктор для hibernate */
    public IdentificationDocumentCatalog() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}