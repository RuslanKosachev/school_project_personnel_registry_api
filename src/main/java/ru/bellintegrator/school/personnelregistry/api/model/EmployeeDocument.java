package ru.bellintegrator.school.personnelregistry.api.model;

import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.JoinTable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/** Сущность документа удостоверяющего личность - относится к одному сотруднику */
@Entity
@Table(name = "employee_document")
public class EmployeeDocument {

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
     * Наименование документа
     */
    @Column(name = "name", length = 250)
    private String name;

    /**
     * Номер документа
     */
    @Column(name = "number", length = 20)
    private String number;

    /**
     * Дата выдачи документа
     */
    @Column(name = "date", length = 250)
    @Temporal(TemporalType.DATE)
    private Date date;

    /** Тип докумета */
    @ManyToOne
    @JoinColumn(name = "identification_document_catalog_id")
    private IdentificationDocumentCatalog documentCatalog;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /** Конструктор для hibernate */
    public EmployeeDocument() { }

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public IdentificationDocumentCatalog getDocumentCatalog() {
        return documentCatalog;
    }

    public void setDocumentCatalog(IdentificationDocumentCatalog documentCatalog) {
        this.documentCatalog = documentCatalog;
    }
}