package ru.bellintegrator.school.personnelregistry.api.dao;

import ru.bellintegrator.school.personnelregistry.api.dao.exception.DaoException;
import ru.bellintegrator.school.personnelregistry.api.model.Employee;

import java.util.List;
import java.util.Map;

/**
 * DAO для работы с сущностями Сотрудника
 */
public interface EmployeeDaoI {
    /**
     * Возвращает список сущностей Employee по параметрам
     *
     * @param filter - фильтрры по полям сущности для получения списска
     * @return коллекция {@link Employee}
     * @see Employee
     */
    List<Employee> getList(Map<String, Object> filter);

    /**
     * Получить Сущность по идентификатору
     *
     * @param id уникальный идентификатор сущности
     * @return при успешном запросе - объект сущность {@link Employee},
     *         иначе null
     */
    Employee getById(Integer id) throws DaoException;

    /**
     * Сохранить сущность
     *
     * @param employee - {@link Employee}
     * @return при успешном сохранении возвращает экземпляр обновленного объекта
     */
    Employee create(Employee employee) throws DaoException;

    /**
     * Обновиляет данные сущности
     *
     * @param employee - {@link Employee}
     * @return при успешном обновлении возвращает экземпляр обновленного объекта
     */
    Employee update(Employee employee) throws DaoException;
}
