package ru.bellintegrator.school.personnelregistry.api.dao;

import ru.bellintegrator.school.personnelregistry.api.model.Employee;
import ru.bellintegrator.school.personnelregistry.api.view.UserView;

import java.util.List;
import java.util.Map;

/**
 * DAO для работы с сущностями Сотрудника
 */
public interface EmployeeDao {
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
     * @return объект сущность {@link Employee}
     */
    Employee getById(Integer id);

    /**
     * Сохранить сущность
     *
     * @param employee - {@link Employee}
     * @return при успешном добавлении значение - true
     */
    Boolean save(Employee employee);

    /**
     * Обновиляет данные сущности
     *
     * @param employee - {@link Employee}
     * @return при успешном обновлении возвращает экземпляр обновленного объекта, иначе - null
     */
    Employee update(Employee employee);
}
