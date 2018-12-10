package ru.bellintegrator.school.personnelregistry.api.view;

import io.swagger.annotations.ApiModel;
import javax.validation.constraints.*;
import java.util.Date;

/**
 * сотрудник
 */
@ApiModel(description = "Сотрудник")
public class UserView {
    /**
     * Идентификатор
     */
    @Min(value = 1, message = "Id не должен быть меньше единицы")
    private Integer id;

    /**
     * Имя
     */
    @Size(max = 50, message = "Имя не должно превышать 50 символов")
    private String firstName;

    /**
     * Фамилия
     */
    @Size(max = 50, message = "Фамилия не должна превышать 50 символов")
    private String secondName;

    /**
     * Отчество
     */
    @Size(max = 50, message = "Отчество не должно превышать 50 символов")
    private String middleName;

    /**
     * Должность
     */
    @Size(max = 100, message = "Должность не должно превышать 100 символов")
    private String position;

    /**
     * Телефон
     */
    @Size(max = 20, message = "Номер телефона не должен превышать 20 символов")
    private String phone;

    /**
     * Код типа документа
     */
    @Size(max = 2, message = "Код документа не должен превышать 2-х символов")
    private String docCode;

    /**
     * Наименование документа
     */
    @Size(max = 250, message = "Наименование документа не может превышать 250 символов")
    private String docName;

    /**
     * Номер документа
     */
    @Size(max = 20, message = "Номер документа не может превышать 20 символов")
    private String docNumber;

    /**
     * Дата документа
     */
    @PastOrPresent(message = "Дата документа должна быть прошлым или настоящим")
    private Date docDate;

    /**
     * Наименование государства
     */
    private String citizenshipName;

    /**
     * Код государства. Трехзначный цифровой код
     */
    @Max(value = 999, message = "Цифровой код страны по ISO 3166-1 превышает значение(должен иметь 3 цифры)")
    private Integer citizenshipCode;

    /**
     * Работающий
     */
    private Boolean isIdentified;

    @Min(value = 1, message = "Id не должен быть меньше единицы")
    private Long officeId;

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

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public Date getDocDate() {
        return docDate;
    }

    public void setDocDate(Date docDate) {
        this.docDate = docDate;
    }

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
    }

    public Integer getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(Integer citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public Boolean isIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }
}