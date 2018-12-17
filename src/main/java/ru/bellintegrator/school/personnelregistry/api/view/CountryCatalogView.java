package ru.bellintegrator.school.personnelregistry.api.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import ru.bellintegrator.school.personnelregistry.api.view.exception.ErrorMessage;

import javax.validation.constraints.*;
import java.util.Objects;

/**
 * Справочник стран
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    @Pattern(regexp = "\\d{1,3}",
             message = ErrorMessage.COUNTRY_CATALOG_PATTERN)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryCatalogView that = (CountryCatalogView) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(code, that.code);
    }
}
