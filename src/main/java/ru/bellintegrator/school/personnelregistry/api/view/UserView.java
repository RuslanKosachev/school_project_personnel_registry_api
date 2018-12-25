package ru.bellintegrator.school.personnelregistry.api.view;

import ru.bellintegrator.school.personnelregistry.api.error.ErrorMessage;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

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
    @Min(value = 1, message = ErrorMessage.USER_V_ID_NEGATIVE_OR_ZERO)
    private Integer id;

    /**
     * Имя
     */
    @Size(min = 1, message = ErrorMessage.USER_V_FNAME_MIN)
    @Size(max = 50, message = ErrorMessage.USER_V_FNAME_MAX)
    private String firstName;

    /**
     * Фамилия
     */
    @Size(min = 1, message = ErrorMessage.USER_V_SNAME_MIN)
    @Size(max = 50, message = ErrorMessage.USER_V_SNAME_MAX)
    private String secondName;

    /**
     * Отчество
     */
    @Size(min = 1, message = ErrorMessage.USER_V_MNAME_MIN)
    @Size(max = 50, message = ErrorMessage.USER_V_MNAME_MAX)
    private String middleName;

    /**
     * Должность
     */
    @Size(min = 1, message = ErrorMessage.USER_V_POSITION_MIN)
    @Size(max = 100, message = ErrorMessage.USER_V_POSITION_MAX)
    private String position;

    /**
     * Телефон
     */
    @Size(min = 1, message = ErrorMessage.USER_V_PHONE_MIN)
    @Size(max = 20, message = ErrorMessage.USER_V_PHONE_MAX)
    private String phone;

    /**
     * Код типа документа удостоверяющего личность согласно приложению №3
     * приказа ФНС России от 25.01.2012 N ММВ-7-6/25@ (ред. от 25.05.2016)
     */
    @Pattern(regexp = "\\d{1,2}",
             message = ErrorMessage.IDENTIFICATION_DOCUMENT_CATALOG_V_PATTERN)
    private String docCode;

    /**
     * Наименование документа удостоверяющего личность
     */
    @Size(min = 1, message = ErrorMessage.USER_V_DOC_NAME_MIN)
    @Size(max = 250, message = ErrorMessage.USER_V_DOC_NAME_MAX)
    private String docName;

    /**
     * Наименование документа удостоверяющего личность согласно приложению №3
     * приказа ФНС России от 25.01.2012 N ММВ-7-6/25@ (ред. от 25.05.2016)
     */
    private String docNameCatalog;

    /**
     * Номер документа удостоверяющего личность
     */
    @Size(min = 1, message = ErrorMessage.USER_V_DOC_NUMBER_MIN)
    @Size(max = 20, message = ErrorMessage.USER_V_DOC_NUMBER_MAX)
    private String docNumber;

    /**
     * Дата документа удостоверяющего личность
     */
    // todo нужна валидация даты
    private LocalDate docDate;

    /**
     * Наименование государства
     */
    private String citizenshipName;

    /**
     * Код государства. Трехзначный цифровой код
     */
    @Pattern(regexp = "\\d{1,3}",
             message = ErrorMessage.COUNTRY_CATALOG_V_PATTERN)
    private String citizenshipCode;

    /**
     * Работающий
     */
    private Boolean isIdentified;

    @Min(value = 1, message = ErrorMessage.OFFICE_V_ID_NEGATIVE_OR_ZERO)
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

    public LocalDate getDocDate() {
        return docDate;
    }

    public void setDocDate(LocalDate docDate) {
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

    public Boolean getIsIdentified() {
        return isIdentified;
    }

    public void setIsIdentified(Boolean identified) {
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