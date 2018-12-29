package ru.bellintegrator.school.personnelregistry.api.view;

import ru.bellintegrator.school.personnelregistry.api.error.ErrorMessage;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * подразделение организации
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfficeView {
    /**
     * Идентификатор
     */
    @Min(value = 1, message = ErrorMessage.OFFICE_V_ID_NEGATIVE_OR_ZERO)
    private Integer id;

    /**
     * Наименование
     */
    @Size(min = 1, message = ErrorMessage.OFFICE_V_NAME_MIN)
    @Size(max = 100, message = ErrorMessage.OFFICE_V_NAME_MAX)
    private String name;

    /**
     * Адрес
     */
    @Size(min = 1, message = ErrorMessage.OFFICE_V_ADDRESS_MIN)
    @Size(max = 350, message = ErrorMessage.OFFICE_V_ADDRESS_MAX)
    private String address;

    /**
     * Телефон
     */
    @Size(min = 1, message = ErrorMessage.OFFICE_V_PHONE_MIN)
    @Size(max = 20, message = ErrorMessage.OFFICE_V_PHONE_MAX)
    private String phone;

    /**
     * Действующее подразделение
     */
    private Boolean isActive;

    /**
     * идентификатор организации
     */
    @Min(value = 1, message = ErrorMessage.ORG_V_ID_NEGATIVE_OR_ZERO)
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
        if (Objects.nonNull(name)) {
            this.name = name.trim();
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (Objects.nonNull(address)) {
            this.address = address.trim();
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (Objects.nonNull(phone)) {
            this.phone = phone.trim();
        }
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }
}
