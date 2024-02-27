package hu.szakdolgozat.service;

import hu.szakdolgozat.entity.User;

public interface UserService extends JpaCommonEntityService<User> {

    User getUserByUsername(String username);

    User authenticate(String username, String password);
}
