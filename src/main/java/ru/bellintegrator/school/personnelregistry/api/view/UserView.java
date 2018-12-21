package ru.bellintegrator.school.personnelregistry.api.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import ru.bellintegrator.school.personnelregistry.api.view.exception.ErrorMessage;

import javax.validation.constraints.*;
import java.util.Date;

/**
 * сотрудник
 *
 * Обьекты даноно класса служат для передачи данных между слоями контроллер - модель,
 * и представляет данные для модели сотрудника
 */
@ApiModel(description = "Сотрудник")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserView {
    /**
     * Идентификатор
     */
    @Min(value = 1, message = "id не должен быть меньше единицы")
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
     * Код типа документа согласно приложению №3
     * приказа ФНС России от 25.01.2012 N ММВ-7-6/25@ (ред. от 25.05.2016)
     */
    @Pattern(regexp = "\\d{1,2}",
             message = ErrorMessage.IDENTIFICATION_DOCUMENT_CATALOG_PATTERN)
    private String docCode;

    /**
     * Наименование документа
     */
    @Size(max = 250, message = "Наименование документа не может превышать 250 символов")
    private String docName;

    /**
     * Наименование документа согласно приложению №3
     * приказа ФНС России от 25.01.2012 N ММВ-7-6/25@ (ред. от 25.05.2016)
     */
    @Size(max = 250, message = "Наименование документа не может превышать 250 символов")
    private String docNameCatalog;

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
    @Pattern(regexp = "\\d{1,3}",
             message = ErrorMessage.COUNTRY_CATALOG_PATTERN)
    private String citizenshipCode;

    /**
     * Работающий
     */
    private Boolean isIdentified;

    @Min(value = 1, message = "Id не должен быть меньше единицы")
    private Integer officeId;

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

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public String getDocNameCatalog() {
        return docNameCatalog;
    }

    public void setDocNameCatalog(String docNameCatalog) {
        this.docNameCatalog = docNameCatalog;
    }
}