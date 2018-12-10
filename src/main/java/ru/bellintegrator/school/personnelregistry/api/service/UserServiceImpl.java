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
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public Boolean create(UserView userView) {
        Boolean result = false;
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public boolean update(UserView userView) {
        return false;
    }
}
