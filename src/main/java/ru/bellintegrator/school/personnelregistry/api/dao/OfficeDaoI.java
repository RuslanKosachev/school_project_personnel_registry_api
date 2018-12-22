package ru.bellintegrator.school.personnelregistry.api.dao;

import ru.bellintegrator.school.personnelregistry.api.model.Office;

import java.util.List;
import java.util.Map;

/**
 * DAO для работы с сущностями Сотрудника
 */
public interface OfficeDaoI {
    /**
     * Возвращает список сущностей Employee по параметрам
     *
     * @param filter - фильтрры по полям сущности для получения списска
     * @return коллекция {@link Office}
     */
    List<Office> getList(Map<String, Object> filter);

    /**
     * Получить Сущность по идентификатору
     *
     * @param id уникальный идентификатор сущности
     * @return при успешном запросе - объект сущность {@link Office},
     *         иначе null
     */
    Office getById(Integer id);

    /**
     * Сохранить сущность
     *
     * @param employee - {@link Office}
     * @return при успешном сохранении возвращает экземпляр обновленного объекта
     */
    Office create(Office employee);

    /**
     * Обновиляет данные сущности
     *
     * @param employee - {@link Office}
     * @return при успешном обновлении возвращает экземпляр обновленного объекта
     */
    Office update(Office employee);
}
