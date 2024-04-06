package hu.szakdolgozat.mbean;


import hu.szakdolgozat.entity.Address;
import hu.szakdolgozat.entity.User;
import hu.szakdolgozat.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class EditProfileMBean implements Serializable {

    @Inject
    private UserService userService;
    private User loggedInUser;
    private final List<String> streetType = List.of("utca", "út", "tér", "körút", "sétány", "sor", "dűlő", "lejáró", "park", "parkja", "tanya", "völgy", "rét", "erdő", "domb", "hegy", "sziget");
    private String streetTypeSelected;
    private Address profileAddress = new Address();

    private String oldPassWord;
    private String newPassWord;
    private String newPassWordAgain;

    @PostConstruct
    public void init() {
        loggedInUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("loggedInUser");
        if (loggedInUser.getType().name().equals("CUSTOMER") || loggedInUser.getType().name().equals("COURIER")) {
            profileAddress = loggedInUser.getAddress();
            if (profileAddress == null) {
                profileAddress = new Address(" ", " ", " ", " ");
                return;

            }
            String[] addressParts = loggedInUser.getAddress().getStreet().split(" ");
            streetTypeSelected = addressParts[addressParts.length - 1];
            profileAddress.setStreet(loggedInUser.getAddress().getStreet().replace(" " + streetTypeSelected, ""));
        }
    }

    public void saveEditProfile() {
        if (!newPassWord.equals(newPassWordAgain)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hiba", "A két jelszó nem egyezik!"));
            return;
        }
        if (loggedInUser.getType().name().equals("CUSTOMER") || loggedInUser.getType().name().equals("COURIER")) {
            profileAddress.setStreet(profileAddress.getStreet() + " " + streetTypeSelected);
            loggedInUser.setAddress(profileAddress);
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(oldPassWord, loggedInUser.getPassword())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hiba", "Nem megfelő jelszót adott meg!"));
            return;
        }
        loggedInUser.setModificationDate(new Date());
        if (!newPassWord.isEmpty()) {
            loggedInUser.setPassword(newPassWord);
        }
        userService.update(loggedInUser);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sikeres módosítás", "A profil módosítása sikeresen megtörtént!"));

    }

    public String getNewPassWord() {
        return newPassWord;
    }

    public void setNewPassWord(String newPassWord) {
        this.newPassWord = newPassWord;
    }

    public String getOldPassWord() {
        return oldPassWord;
    }

    public void setOldPassWord(String oldPassWord) {
        this.oldPassWord = oldPassWord;
    }

    public String getNewPassWordAgain() {
        return newPassWordAgain;
    }

    public void setNewPassWordAgain(String newPassWordAgain) {
        this.newPassWordAgain = newPassWordAgain;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public List<String> getStreetType() {
        return streetType;
    }

    public String getStreetTypeSelected() {
        return streetTypeSelected;
    }

    public void setStreetTypeSelected(String streetTypeSelected) {
        this.streetTypeSelected = streetTypeSelected;
    }

    public Address getProfileAddress() {
        return profileAddress;
    }

    public void setProfileAddress(Address profileAddress) {
        this.profileAddress = profileAddress;
    }
}
