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
import java.util.HashSet;
import java.util.Set;

/** Сущность подразделения */
@Entity
@Table(name = "office")
public class Office {

    /** Уникальный дентификатор*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Служебное поле hibernate */
    @Version
    private Integer version;

    /** Наименование */
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    /** Адрес */
    @Column(name = "address", length = 350, nullable = false)
    private String address;

    /** Телефон */
    @Column(name = "phone", length = 20)
    private String phone;

    /** Действующее подразделение или нет */
    @Column(name = "is_active")
    private Boolean isActive;

    /** Организация, которой принадлежит офис */
    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    /** сотрудники подразделения */
    @ManyToMany(
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(
        name = "employee_office",
        joinColumns = @JoinColumn(name = "employee_id"),
        inverseJoinColumns = @JoinColumn(name = "office_id"))
    private Set<User> users;

    /** Конструктор для hibernate */
    public Office() { }

    /** Добовляет сотрудника */
    public void addUser(User user) {
        users.add(user);
        user.getOffices().add(this);
    }

    /** Удаляет сотрудника */
    public void removeUser(User user) {
        users.remove(user);
        user.getOffices().remove(this);
    }

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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Set<User> getUsers() {
        if (users == null) {
            users = new HashSet<>();
        }
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


}
