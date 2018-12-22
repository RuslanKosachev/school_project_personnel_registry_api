package ru.bellintegrator.school.personnelregistry.api.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.bellintegrator.school.personnelregistry.api.view.exception.ErrorMessage;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
    @Min(value = 1,
         message = ErrorMessage.ORGANIZATION_ID_MIN)
    private Integer id;

    /**
     * Короткое наименование
     */
    @Size(max = 100,
          message = "Наименование организации не должно превышать 100 символов")
    private String name;

    /**
     * Полное наименование
     */
    @Size(max = 350,
          message = "Полное наименование организации не должно превышать 350 символов")
    private String fullName;

    /**
     * Идентификационный номер налогоплательщика
     */
    @Pattern(regexp = "\\d{10,12}",
             message = ErrorMessage.ORGANIZATION_INN_PATTERN)
    private String inn;

    /**
     *  Код причины постановки на учет в налоговых органах
     */
    @Pattern(regexp = "\\d{9}",
            message = ErrorMessage.ORGANIZATION_KPP_PATTERN)
    private String kpp;

    /**
     * Адрес
     */
    @Size(max = 255, message = "Адрес не должен превышать 350 символов")
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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
