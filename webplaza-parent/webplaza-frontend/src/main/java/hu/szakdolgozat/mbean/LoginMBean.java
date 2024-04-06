package hu.szakdolgozat.mbean;

import hu.szakdolgozat.entity.User;
import hu.szakdolgozat.enums.UserType;
import hu.szakdolgozat.service.UserService;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;


@ApplicationScoped
@Named
public class LoginMBean implements Serializable {


    private String username;
    private String phoneNumber;

    private String password;

    private String passwordAgain;
    private User loggedInUser;

    @Inject
    private UserService userService;

    @PostConstruct
    private void init() {

    }

    public void login() {
        loggedInUser = userService.authenticate(username, password);
        if (loggedInUser != null) {
            String redirectUrl = "login.xhtml";
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("loggedInUser", loggedInUser);

            switch (loggedInUser.getType()) {
                case ADMIN:
                    redirectUrl = "admin/adminMenubar.xhtml";
                    break;
                case CUSTOMER:
                    redirectUrl = "customer/customerWelcome.xhtml";
                    break;
                case COURIER:
                    redirectUrl = "courier/courierPlazas.xhtml";
                    break;
                case SHOP_OWNER:
                    redirectUrl = "shopowner/shopOwnerMenuBar.xhtml";
                    break;
            }

            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect(redirectUrl);
            } catch (IOException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hiba", "Hiba történt a bejelentkezés során!"));
            }
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Hibás adatok", "Hibás felhasználónév vagy jelszó!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void logout() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        session.invalidate();
        try {
            externalContext.redirect(externalContext.getRequestContextPath() + "/xhtml/login.xhtml");
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hiba", "Hiba történt a kijelentkezés során!"));
        }
    }

    public void register() {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hiba", "A felhasználónév már foglalt!"));
            return;
        }
        if (!password.equals(passwordAgain)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hiba", "A két jelszó nem egyezik!"));
            return;
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPhoneNumber(phoneNumber);
        newUser.setPassword(password);
        newUser.setType(UserType.CUSTOMER);
        newUser.setCreationDate(new Date());
        userService.add(newUser);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sikeres regisztráció", "Sikeres regisztráció! Most már bejelentkezhet!"));
        PrimeFaces.current().executeScript("PF('registerDialog').hide();");

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordAgain() {
        return passwordAgain;
    }

    public void setPasswordAgain(String passwordAgain) {
        this.passwordAgain = passwordAgain;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

