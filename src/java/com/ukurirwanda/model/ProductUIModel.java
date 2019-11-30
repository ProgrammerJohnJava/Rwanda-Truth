package com.ukurirwanda.model;

import com.ukurirwanda.common.FileUpload;
import com.ukurirwanda.dao.InfideleDao;
import com.ukurirwanda.dao.ProductImageDao;
import com.ukurirwanda.dao.SetupDao;
import com.ukurirwanda.dao.UserDao;
import com.ukurirwanda.domain.Infidele;
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
public class ProductUIModel {

    private User loggedInUser = new User();
    private List<ProductImage> houses = new ArrayList<>();
    private List<ProductImage> cars = new ArrayList<>();
    private List<ProductImage> electronics = new ArrayList<>();
    private List<ProductImage> components = new ArrayList<>();
    private List<Infidele> infideles = new ArrayList<>();

    @PostConstruct
    public void init() {
        userInit();
        productInit();
    }
    
    public void productInit(){
        houses = new ProductImageDao().findByHouseAndCustomer("House", loggedInUser.getCustomer());
        cars = new ProductImageDao().findByCarAndCustomer("Car", loggedInUser.getCustomer());
        electronics = new ProductImageDao().findByElectronicsAndCustomer("Electronics", loggedInUser.getCustomer());
        components = new ProductImageDao().findByComponentAndCustomer("Component", loggedInUser.getCustomer());
        infideles = new InfideleDao().findByPublisher(loggedInUser.getCustomer());
    }
    
    public void userInit() {
        loggedInUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("session");
    }

    

    public void removeProduct(ProductImage p){
        p.setStatus("Banned");
        new ProductImageDao().update(p);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage("Product Removed"));
    }
    
    public void republishProduct(ProductImage p){
        p.setStatus("Approved");
        new ProductImageDao().update(p);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage("Product Removed"));
    }
    public void removeInfidele(Infidele i){
        i.setStatus("Banned");
        new InfideleDao().update(i);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage("Person Removed"));
    }
    
    public void republishInfidele(Infidele i){
        i.setStatus("Approved");
        new InfideleDao().update(i);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage("Person Published"));
    }
    
//    public void sendMail(){
//
//        Email email = new SimpleEmail();
//        try {
//            String authuser = "me@gmail.com";
//            String authpwd = "password";
//            email.setSmtpPort(587);
//            email.setAuthenticator(new DefaultAuthenticator(authuser, authpwd));
//            email.setDebug(true);
//            email.setHostName("smtp.gmail.com");
//            email.getMailSession().getProperties().put("mail.smtps.auth", "true");
//            email.getMailSession().getProperties().put("mail.debug", "true");
//            email.getMailSession().getProperties().put("mail.smtps.port", "587");
//            email.getMailSession().getProperties().put("mail.smtps.socketFactory.port", "587");
//            email.getMailSession().getProperties().put("mail.smtps.socketFactory.class",   "javax.net.ssl.SSLSocketFactory");
//            email.getMailSession().getProperties().put("mail.smtps.socketFactory.fallback", "false");
//            email.getMailSession().getProperties().put("mail.smtp.starttls.enable", "true");
//            email.setFrom("me@gmail.com", "Agencja Ubezpieczeniowa");
//            email.setSubject("TestMail");
//            email.setMsg("This is a test mail3");
//            email.addTo("someone@gmail.com", "ToName");
//            //email.setStartTLSRequired(false);
//            email.send();
//        } catch (EmailException e) {
//            e.printStackTrace();
//        }
//    }
    
    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
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

}
