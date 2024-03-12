package hu.szakdolgozat.mbean.admin;

import hu.szakdolgozat.entity.User;
import hu.szakdolgozat.enums.UserType;
import hu.szakdolgozat.mbean.LoginMBean;
import hu.szakdolgozat.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Named
@ViewScoped
public class AdminUsersMBean implements Serializable {

    List<User> userList = new ArrayList<>();
    User selectedUser = new User();
    String passwordAgain;
    String password;
    String username;
    List<UserType> userRoleList = new ArrayList<>();
    UserType userType;

    @Inject
    private UserService userService;


    @PostConstruct
    public void init() {
        load();
        initNew();
    }

    public void load() {
        userRoleList = UserType.getAll();
        userList = userService.getAllEntity();
    }

    public void initNew() {
        selectedUser = new User();
    }

    public void save() {
        boolean usernameExists = userService.getUserByUsername(username) != null;

        if (password == null || !password.equals(passwordAgain)) {
            errorMessage("A jelszavak nem egyeznek");
            return;
        }
        if(selectedUser.getType() == null){
            errorMessage("Válasszon felhasználó típust");
            return;
        }
        if (selectedUser.getId() == null) {
            if (usernameExists) {
                errorMessage("A felhasználónév már létezik");
                return;
            }
            selectedUser.setPassword(password);
            userService.add(selectedUser);
        } else {
            selectedUser.setPassword(password);
            userService.update(selectedUser);
        }
        load();
        initNew();
        infoMessage("Felhasználó sikeresen mentve");
    }

    public void remove() {
        userService.delete(selectedUser);
        load();
        selectedUser = new User();
    }

    public void errorMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", message));
    }

    public void infoMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", message));
    }


    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<UserType> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<UserType> userRoleList) {
        this.userRoleList = userRoleList;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordAgain() {
        return passwordAgain;
    }

    public void setPasswordAgain(String passwordAgain) {
        this.passwordAgain = passwordAgain;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
