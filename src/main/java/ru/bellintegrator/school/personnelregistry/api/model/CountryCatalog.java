package ru.bellintegrator.school.personnelregistry.api.model;

import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
        @NamedQuery(
                name = "getCountryCatalogByCode",
                query = "SELECT cc FROM CountryCatalog as cc WHERE cc.code = :code"
        )
})

/** Сущность страны */
@Entity
@Table(name = "country_catalog")
public class CountryCatalog {

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
     * Название страны
     */
    @Column(name = "name", length = 150, nullable = false)
    private String name;

    /**
     * Цифровой код страны по ISO 3166-1
     */
    @Column(name = "code", length = 3, nullable = false)
    private String code;

    /** Конструктор для hibernate */
    public CountryCatalog() { }

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