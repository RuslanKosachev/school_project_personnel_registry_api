package ru.bellintegrator.school.personnelregistry.api.model;

import javax.persistence.Version;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** Сущность организации */
@Entity
@Table(name = "organization")
public class Organization {

    /** Уникальный дентификатор */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Служебное поле hibernate */
    @Version
    private Integer version;

    /** Короткое наименование организации */
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    /** Полное наименование организации */
    @Column(name = "full_name", length = 350, nullable = false)
    private String fullName;

    /** ИНН организаци */
    @Column(name = "inn", length = 12, nullable = false)
    private String inn;

    /** КПП организации */
    @Column(name = "kpp", length = 9, nullable = false)
    private String kpp;

    /** Адрес организации */
    @Column(name = "address", length = 350, nullable = false)
    private String address;

    /** Телефон организации */
    @Column(name = "phone", length = 20)
    private String phone;

    /** Действующая организация или нет */
    @Column(name = "is_active")
    private Boolean isActive;

    /** Конструктор для hibernate */
    public Organization() { }

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
