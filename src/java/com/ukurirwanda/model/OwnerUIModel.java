package com.ukurirwanda.model;

import com.ukurirwanda.common.FileUpload;
import com.ukurirwanda.common.PassCode;
import com.ukurirwanda.dao.InfideleDao;
import com.ukurirwanda.dao.OwnerDao;
import com.ukurirwanda.dao.ProductImageDao;
import com.ukurirwanda.dao.SetupDao;
import com.ukurirwanda.dao.UserDao;
import com.ukurirwanda.domain.Infidele;
import com.ukurirwanda.domain.Owner;
import com.ukurirwanda.domain.ProductImage;
import com.ukurirwanda.domain.Setup;
import com.ukurirwanda.domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

@ManagedBean
@SessionScoped
public class OwnerUIModel {

    private Setup setup = new Setup();
    private List<String> logo = new ArrayList<>();
    private User loggedInUser = new User();
    private List<User> customers = new ArrayList<>();
    private List<User> owners = new ArrayList<>();
    private List<ProductImage> houses = new ArrayList<>();
    private List<ProductImage> cars = new ArrayList<>();
    private List<ProductImage> electronics = new ArrayList<>();
    private List<ProductImage> components = new ArrayList<>();
    private List<Infidele> infideles = new ArrayList<>();
    private Owner owner = new Owner();
    private User user = new User();
    private String ownerPass = new String();

    @PostConstruct
    public void init() {
        setup = new SetupDao().findOne(Setup.class, "6ad91e4e-c75d-4ec3-8af0-339d456a53b9");
        usersinit();
        houseinit();
        carinit();
        electronicsinit();
        componentinit();
        infideles = new InfideleDao().findAll(Infidele.class);
    }

    public void houseinit() {
        for (ProductImage p : new ProductImageDao().findAll(ProductImage.class)) {
            if (p.getType().equalsIgnoreCase("House")) {
                houses.add(p);
            }
        }
    }

    public void carinit() {
        for (ProductImage p : new ProductImageDao().findAll(ProductImage.class)) {
            if (p.getType().equalsIgnoreCase("Car")) {
                cars.add(p);
            }
        }
    }

    public void componentinit() {
        for (ProductImage p : new ProductImageDao().findAll(ProductImage.class)) {
            if (p.getType().equalsIgnoreCase("Component")) {
                components.add(p);
            }
        }
    }

    public void electronicsinit() {
        for (ProductImage p : new ProductImageDao().findAll(ProductImage.class)) {
            if (p.getType().equalsIgnoreCase("Electronics")) {
                electronics.add(p);
            }
        }
    }

    public void usersinit() {
        for (User u : new UserDao().findAll(User.class)) {
            if (u.getAccess().equalsIgnoreCase("Customer")) {
                customers.add(u);
            } else if (u.getAccess().equalsIgnoreCase("Owner")) {
                owners.add(u);
            }
        }
    }

    public void userInit() {
        loggedInUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("session");
    }

    public void block(User u) {
        u.setStatus("Blocked");
        new UserDao().update(u);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage("Account Blocked"));
    }

    public void activate(User u) {
        u.setStatus("Active");
        new UserDao().update(u);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage("Account Activated"));
    }

    public void removeProduct(ProductImage p) {
        p.setStatus("Banned");
        new ProductImageDao().update(p);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage("Product Removed"));
    }

    public void republishProduct(ProductImage p) {
        p.setStatus("Approved");
        new ProductImageDao().update(p);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage("Product Removed"));
    }

    public void removeInfidele(Infidele i) {
        i.setStatus("Banned");
        new InfideleDao().update(i);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage("Person Removed"));
    }

    public void republishInfidele(Infidele i) {
        i.setStatus("Approved");
        new InfideleDao().update(i);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage("Person Published"));
    }

    public void setupInit() {
        if (this.logo.isEmpty()) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Upload Logo"));
        } else {
            for (String x : logo) {
                setup.setLogo(x);
            }
            logo.clear();

            new SetupDao().update(setup);
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Setup Details Saved"));
        }
    }

    public void registerOwner() throws Exception {
        new OwnerDao().create(owner);        
        user.setOwner(owner);
        user.setStatus("Active");
        user.setAccess("Owner");
        user.setPassword(new PassCode().encrypt(ownerPass));
        new UserDao().create(user);
        owner = new Owner();
        user = new User();
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage("Admin Registered"));
    }

    public void UploadLogo(FileUploadEvent event) {
        logo.add(new FileUpload().Upload(event, "C:\\Users\\student\\Documents\\NetBeansProjects\\ukurirwanda\\web\\uploads\\logo\\"));
    }

    public Setup getSetup() {
        return setup;
    }

    public void setSetup(Setup setup) {
        this.setup = setup;
    }

    public List<String> getLogo() {
        return logo;
    }

    public void setLogo(List<String> logo) {
        this.logo = logo;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public List<User> getCustomers() {
        return customers;
    }

    public void setCustomers(List<User> customers) {
        this.customers = customers;
    }

    public List<User> getOwners() {
        return owners;
    }

    public void setOwners(List<User> owners) {
        this.owners = owners;
    }

    public List<ProductImage> getHouses() {
        return houses;
    }

    public void setHouses(List<ProductImage> houses) {
        this.houses = houses;
    }

    public List<ProductImage> getCars() {
        return cars;
    }

    public void setCars(List<ProductImage> cars) {
        this.cars = cars;
    }

    public List<ProductImage> getElectronics() {
        return electronics;
    }

    public void setElectronics(List<ProductImage> electronics) {
        this.electronics = electronics;
    }

    public List<ProductImage> getComponents() {
        return components;
    }

    public void setComponents(List<ProductImage> components) {
        this.components = components;
    }

    public List<Infidele> getInfideles() {
        return infideles;
    }

    public void setInfideles(List<Infidele> infideles) {
        this.infideles = infideles;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOwnerPass() {
        return ownerPass;
    }

    public void setOwnerPass(String ownerPass) {
        this.ownerPass = ownerPass;
    }

}
