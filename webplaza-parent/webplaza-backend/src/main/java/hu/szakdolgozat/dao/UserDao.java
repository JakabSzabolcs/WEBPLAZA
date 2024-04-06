package hu.szakdolgozat.dao;

import hu.szakdolgozat.entity.Order;
import hu.szakdolgozat.entity.User;

public interface UserDao extends JpaCommonEntityDao<User> {
    User getUserByUsername(String username);

    User authenticate(String username, String password);

}
