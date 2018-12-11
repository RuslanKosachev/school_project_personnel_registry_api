package ru.bellintegrator.school.personnelregistry.api.view;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Справочник видов документов, удостоверяющих личность физического лица
 */
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
    @Size(max = 250, message = "Наименование организации не должно превышать 250 символов")
    public String name;

    /**
     * Уникальный код документа по российской квалификации
     */
    @NotEmpty(message = "Название документа не может быть пустым")
    @Size(max = 2, message = "Код документа не должен превышать 2-х символов")
    public String code;
}