package hu.szakdolgozat.service.impl;

import hu.szakdolgozat.entity.User;
import hu.szakdolgozat.service.UserService;

import javax.ejb.Stateless;

@Stateless
public class UserServiceImpl extends JpaCommonEntityServiceImpl<User> {
}
