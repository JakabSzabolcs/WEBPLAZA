package hu.szakdolgozat.mbean.admin;

import hu.szakdolgozat.entity.Address;
import hu.szakdolgozat.entity.Plaza;
import hu.szakdolgozat.service.PlazaService;
import org.apache.commons.math3.analysis.function.Add;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class AdminPlazasMBean implements Serializable {

    @Inject
    private PlazaService plazaService;

    List<Plaza> plazaList = new ArrayList<>();
    Plaza selectedPlaza = new Plaza();
    Address address = new Address();


    @PostConstruct
    public void init() {
        load();
        initNew();
    }

    private void load() {
        plazaList = plazaService.getAllEntity();
    }

    public void initNew() {
        selectedPlaza = new Plaza();
        address = new Address();
    }

    public void save() {
        if (selectedPlaza.getId() == null) {
            if (address.getCity() == null || address.getStreet() == null || address.getHouseNumber() == null || address.getPostalCode() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hiba", "Minden mező kitöltése kötelező!"));
                return;
            }
            selectedPlaza.setAddress(address);
            plazaService.add(selectedPlaza);
        } else {
            if (address.getCity() == null || address.getStreet() == null || address.getHouseNumber() == null || address.getPostalCode() == null) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hiba", "Minden mező kitöltése kötelező!"));
                return;
            }
            plazaService.update(selectedPlaza);
        }

        initNew();
        load();
    }

    public void remove() {
        plazaService.delete(selectedPlaza);
        initNew();
        load();
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    public Plaza getSelectedPlaza() {
        return selectedPlaza;
    }

    public void setSelectedPlaza(Plaza selectedPlaza) {
        this.selectedPlaza = selectedPlaza;
    }

    public List<Plaza> getPlazaList() {
        return plazaList;
    }

    public void setPlazaList(List<Plaza> plazaList) {
        this.plazaList = plazaList;
    }
}
