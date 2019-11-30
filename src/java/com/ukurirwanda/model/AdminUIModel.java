package com.ukurirwanda.model;

import com.ukurirwanda.common.PassCode;
import com.ukurirwanda.dao.CustomerDao;
import com.ukurirwanda.dao.OwnerDao;
import com.ukurirwanda.dao.SetupDao;
import com.ukurirwanda.dao.UserDao;
import com.ukurirwanda.domain.Customer;
import com.ukurirwanda.domain.Owner;
import com.ukurirwanda.domain.Setup;
import com.ukurirwanda.domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class AdminUIModel {

    private Setup setup = new Setup();
    private String ownerPass = new String();
    private String customerPass = new String();
    private User user = new User();
    private List<String> logo = new ArrayList<>();
    private String villId = new String();
    private Owner owner = new Owner();
    private Customer customer = new Customer();
    
    public void registerOwner() throws Exception {
        new OwnerDao().create(owner);
        
        user.setOwner(owner);
        user.setStatus("Active");
        user.setAccess("Owner");
        user.setPassword(new PassCode().encrypt(ownerPass));
        new UserDao().create(user);
        user = new User();
        owner = new Owner();
        
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage("Account Created"));
    }
    
    public void registerCustomer() throws Exception {
        new CustomerDao().create(customer);        
        user.setCustomer(customer);
        user.setStatus("Active");
        user.setAccess("Customer");
        user.setPassword(new PassCode().encrypt(customerPass));
        new UserDao().create(user);
        customer = new Customer();
        user = new User();
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage("External User Registered"));
    }

    

    public Setup getSetup() {
        return setup;
    }

    public void setSetup(Setup setup) {
        this.setup = setup;
    }

    public String getOwnerPass() {
        return ownerPass;
    }

    public void setOwnerPass(String ownerPass) {
        this.ownerPass = ownerPass;
    }

    public String getCustomerPass() {
        return customerPass;
    }

    public void setCustomerPass(String customerPass) {
        this.customerPass = customerPass;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getLogo() {
        return logo;
    }

    public void setLogo(List<String> logo) {
        this.logo = logo;
    }

    public String getVillId() {
        return villId;
    }

    public void setVillId(String villId) {
        this.villId = villId;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
    
    
}
