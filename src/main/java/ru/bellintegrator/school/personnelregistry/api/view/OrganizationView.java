package ru.bellintegrator.school.personnelregistry.api.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * Организация
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizationView {
    /**
     * Идентификатор
     */
    @Min(value = 1, message = "id не должен быть меньше единицы")
    private Integer id;

    /**
     * Короткое наименование
     */
    @Size(max = 100, message = "Наименование организации не должно превышать 100 символов")
    private String name;

    /**
     * Полное наименование
     */
    @Size(max = 350, message = "Полное наименование организации не должно превышать 350 символов")
    private String fullName;

    /**
     * Идентификационный номер налогоплательщика
     */
    @Size(max = 10, min = 10, message = "Длина ИНН должна быть 10 символов")
    private String inn;

    /**
     *  Код причины постановки на учет в налоговых органах
     */
    @Size(max = 9, min = 9, message = "Длина КПП должна быть 9 символов")
    private String kpp;

    /**
     * Адрес
     */
    @Size(max = 255, message = "Адрес не должен превышать 255 символов")
    private String address;

    /**
     * Телофон
     */
    @Size(max = 20, message = "Номер телефона не должен превышать 20 символов")
    private String phone;

    /**
     * Действующая организация
     */
    private Boolean isActive;

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

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
