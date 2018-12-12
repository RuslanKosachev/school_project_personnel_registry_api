package ru.bellintegrator.school.personnelregistry.api.service;

import ru.bellintegrator.school.personnelregistry.api.view.UserView;

import org.springframework.stereotype.Service;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class UserServiceImpl implements UserServiceI {

    /**
     * {@inheritDoc}
     */
    public List<UserView> getList(UserView userViewParam) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public UserView getById(Integer id) {
        UserView sample = new UserView();
        sample.setId(id);
        sample.setFirstName("Виктор");
        sample.setMiddleName("Прокопенко");
        sample.setPosition("Ассистент");
        sample.setOfficeId(2);

        return sample;
    }

    /**
     * {@inheritDoc}
     */
    public Boolean create(UserView userView) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public Boolean update(UserView userView) {
        return false;
    }
}
