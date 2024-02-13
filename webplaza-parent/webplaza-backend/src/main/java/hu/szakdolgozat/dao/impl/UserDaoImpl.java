package hu.szakdolgozat.dao.impl;

import hu.szakdolgozat.dao.UserDao;
import hu.szakdolgozat.entity.User;

import javax.ejb.Stateless;

@Stateless
public class UserDaoImpl extends JpaCommonEntityDaoImpl<User> implements UserDao {
    @Override
    protected Class<User> getManagedClass() {
        return User.class;
    }
}
