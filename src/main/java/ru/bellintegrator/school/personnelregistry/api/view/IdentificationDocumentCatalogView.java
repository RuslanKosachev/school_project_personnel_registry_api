package ru.bellintegrator.school.personnelregistry.api.view;

import ru.bellintegrator.school.personnelregistry.api.error.ErrorMessage;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * Справочник видов документов, удостоверяющих личность физического лица
 * соласно приложению №3 приказа ФНС России от 25.01.2012 N ММВ-7-6/25@
 * (ред. от 25.05.2016)
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IdentificationDocumentCatalogView {
    /**
     * Идентификатор
     */
    @Min(value = 1, message = "id не должен быть меньше единицы")
    private Integer id;

    /**
     * Название документа
     */
    @NotEmpty(message = "Название документа не может быть пустым")
    @Size(max = 250, message = "Наименование документа не должно превышать 250 символов")
    private String name;

    /**
     * Уникальный код документа
     */
    @NotEmpty(message = "Код документа не может быть пустым")
    @Pattern(regexp = "\\d{1,2}",
             message = ErrorMessage.IDENTIFICATION_DOCUMENT_CATALOG_V_PATTERN)
    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (Objects.nonNull(code)) {
            this.code = code.trim().replaceFirst("^0+(?!$)", "");
        }
    }
}