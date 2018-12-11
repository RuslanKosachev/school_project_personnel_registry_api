package ru.bellintegrator.school.personnelregistry.api.view;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Справочник стран
 */
public class CountryCatalogView {
    /**
     * Идентификатор
     */
    @Min(value = 1, message = "id не должен быть меньше единицы")
    private Integer id;

    /**
     * Название страны
     */
    @Size(max = 150, message = "Наименование организации не должно превышать 150 символов")
    @NotEmpty(message = "Название страны не может быть пустым")
    private String name;

    /**
     * Цифровой код страны по ISO 3166-1
     */
    @Max(value = 999, message = "Цифровой код страны по ISO 3166-1 превышает значение(должен иметь 3 цифры)")
    @NotEmpty(message = "Цифровой код страны по ISO 3166-1 не может быть пустым")
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
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
