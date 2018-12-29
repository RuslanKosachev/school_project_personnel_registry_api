package ru.bellintegrator.school.personnelregistry.api.view;

import ru.bellintegrator.school.personnelregistry.api.error.ErrorMessage;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Организация
 *
 * Обьекты даноно класса служат для передачи данных между слоями контроллер - модель,
 * и представляет данные для модели организации
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizationView {
    /**
     * Идентификатор
     */
    @Min(value = 1, message = ErrorMessage.ORG_V_ID_NEGATIVE_OR_ZERO)
    private Integer id;

    /**
     * Короткое наименование
     */
    @Size(min = 1, message = ErrorMessage.ORG_V_NAME_MIN)
    @Size(max = 100, message = ErrorMessage.ORG_V_NAME_MAX)
    private String name;

    /**
     * Полное наименование
     */
    @Size(min = 1, message = ErrorMessage.ORG_V_FULL_NAME_MIN)
    @Size(max = 350, message = ErrorMessage.ORG_V_FULL_NAME_MAX)
    private String fullName;

    /**
     * Идентификационный номер налогоплательщика
     */
    @Pattern(regexp = "\\d{10,12}",
             message = ErrorMessage.ORG_V_INN_PATTERN)
    private String inn;

    /**
     *  Код причины постановки на учет в налоговых органах
     */
    @Pattern(regexp = "\\d{9}",
            message = ErrorMessage.ORG_V_KPP_PATTERN)
    private String kpp;

    /**
     * Адрес
     */
    @Size(min = 1, message = ErrorMessage.ORG_V_ADDRESS_MIN)
    @Size(max = 350, message = ErrorMessage.ORG_V_ADDRESS_MAX)
    private String address;

    /**
     * Телофон
     */
    @Size(min = 1, message = ErrorMessage.ORG_V_PHONE_MIN)
    @Size(max = 20, message = ErrorMessage.ORG_V_PHONE_MAX)
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
        if (Objects.nonNull(name)) {
            this.name = name.trim();
        }
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (Objects.nonNull(fullName)) {
            this.fullName = fullName.trim();
        }
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        if (Objects.nonNull(inn)) {
            this.inn = inn.trim();
        }
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        if (Objects.nonNull(kpp)) {
            this.kpp = kpp.trim();
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
}
