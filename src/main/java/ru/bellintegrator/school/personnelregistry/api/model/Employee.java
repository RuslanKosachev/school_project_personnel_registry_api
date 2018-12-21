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

/** Сущность сотрудника */
@Entity
@Table(name = "employee")
public class Employee {

    /** Уникальный дентификатор*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Служебное поле hibernate */
    @Version
    private Integer version;

    /** Имя */
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    /** Фамилия */
    @Column(name = "second_name", length = 50)
    private String secondName;

    /** Отчество */
    @Column(name = "middle_name", length = 50)
    private String middleName;

    /** Отчество */
    @Column(name = "position", length = 100)
    private String position;

    /** Телефон */
    @Column(name = "phone", length = 20)
    private String phone;

    /** Работающий сотрудник или нет */
    @Column(name = "is_identified")
    private Boolean isIdentified;

    /** Подразделения в которой работает сотрудник */
    @ManyToMany(mappedBy = "employees",
            //todo test test test test testtest test test test test
            fetch=FetchType.EAGER)
    private Set<Office> offices;

    /** Документ удостоверяющий личность сотруднка */
    @OneToOne(
        cascade = CascadeType.ALL,
        mappedBy = "employee")
    private EmployeeDocument employeeDocument;

    /** Страна */
    @ManyToOne
    @JoinColumn(name = "country_catalog_id")
    private CountryCatalog country;

    /** Конструктор для hibernate */
    public Employee() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsIdentified() {
        return isIdentified;
    }

    public void setIsIdentified(Boolean identified) {
        isIdentified = identified;
    }

    public Set<Office> getOffices() {
        if (offices == null) {
            offices = new HashSet<>();
        }
        return offices;
    }

    public void setOffices(Set<Office> offices) {
        this.offices = offices;
    }

    public EmployeeDocument getEmployeeDocument() {
        return employeeDocument;
    }

    public void setEmployeeDocument(EmployeeDocument employeeDocument) {
        this.employeeDocument = employeeDocument;
    }


    public CountryCatalog getCountry() {
        return country;
    }

    public void setCountry(CountryCatalog country) {
        this.country = country;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", version=" + version +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", position='" + position + '\'' +
                ", phone='" + phone + '\'' +
                ", isIdentified=" + isIdentified +
                '}';
    }
}