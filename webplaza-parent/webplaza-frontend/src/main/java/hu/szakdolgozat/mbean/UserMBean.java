package hu.szakdolgozat.mbean;

import hu.szakdolgozat.entity.*;
import hu.szakdolgozat.service.*;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Named
@ViewScoped
public class UserMBean implements Serializable {

    List<User> userList = new ArrayList<>();

    @Inject
    private UserService userService;


    @PostConstruct
    public void init() {

    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
