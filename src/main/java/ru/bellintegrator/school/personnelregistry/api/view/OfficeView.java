package ru.bellintegrator.school.personnelregistry.api.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * подразделение организации
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfficeView {
    /**
     * Идентификатор
     */
    @Min(value = 1, message = "id не должен быть меньше единицы")
    private Integer id;

    /**
     * Наименование
     */
    @Size(max = 100, message = "Наименование офиса не должно превышать 100 символов")
    private String name;

    /**
     * Адрес
     */
    @Size(max = 350, message = "Адрес не должен превышать 350 символов")
    private String address;

    /**
     * Телефон
     */
    @Size(max = 20, message = "Номер телефона не должен превышать 20 символов")
    private String phone;

    /**
     * Действующее подразделение
     */
    private Boolean isActive;

    /**
     * Id организации офиса
     */
    private Integer orgId;

    public OfficeView() {
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }
}
