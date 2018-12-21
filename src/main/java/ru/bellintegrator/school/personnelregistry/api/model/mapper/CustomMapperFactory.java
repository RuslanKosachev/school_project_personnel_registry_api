package ru.bellintegrator.school.personnelregistry.api.model.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Service;
import ru.bellintegrator.school.personnelregistry.api.model.Employee;
import ru.bellintegrator.school.personnelregistry.api.model.Office;
import ru.bellintegrator.school.personnelregistry.api.view.OfficeView;
import ru.bellintegrator.school.personnelregistry.api.view.UserView;

/**
 * Фабрика для создания MapperFactory.
 * При необходимости можно добавить кастомные мапперы
 */
@Service
public class CustomMapperFactory implements FactoryBean<MapperFactory> {
    @Override
    public MapperFactory getObject() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder()
                .constructorResolverStrategy(null)
                .build();
        mapperFactory.classMap(Office.class, OfficeView.class)
                .field("organization.id", "orgId")
                .byDefault()
                .register();
        mapperFactory.classMap(Employee.class, UserView.class)
                //.field("office.get(0).id", "officeId") ///???????
                .field("employeeDocument.date", "docDate")
                .field("employeeDocument.number", "docNumber")
                .field("employeeDocument.name", "docName")
                .field("employeeDocument.documentCatalog.name", "docNameCatalog")
                .field("employeeDocument.documentCatalog.code", "docCode")
                .field("country.name", "citizenshipName")
                .field("country.code", "citizenshipCode")
                .byDefault()
                .register();
        return mapperFactory;
    }

    @Override
    public Class<?> getObjectType() {
        return MapperFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
