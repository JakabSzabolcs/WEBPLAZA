package hu.szakdolgozat.service.impl;

import hu.szakdolgozat.dao.UserDao;
import hu.szakdolgozat.entity.Order;
import hu.szakdolgozat.entity.User;
import hu.szakdolgozat.service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserServiceImpl extends JpaCommonEntityServiceImpl<User> implements UserService {
    @Inject
    private UserDao dao;
    @Override
    public User getUserByUsername(String username) {
        return dao.getUserByUsername(username);
    }

    @Override
    public User authenticate(String username, String password) {
        return dao.authenticate(username, password);
    }
}
