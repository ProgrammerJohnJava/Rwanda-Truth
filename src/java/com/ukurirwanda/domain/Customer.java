
package com.ukurirwanda.domain;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Customer implements Serializable {
    @Id
    private String id = UUID.randomUUID().toString();
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Infidele> infidele;

    @OneToOne(mappedBy = "customer")
    private User user;
    
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<House> house;
    
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Car> car;
    
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Electronics> electronics;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Infidele> getInfidele() {
        return infidele;
    }

    public void setInfidele(List<Infidele> infidele) {
        this.infidele = infidele;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<House> getHouse() {
        return house;
    }

    public void setHouse(List<House> house) {
        this.house = house;
    }

    public List<Car> getCar() {
        return car;
    }

    public void setCar(List<Car> car) {
        this.car = car;
    }

    public List<Electronics> getElectronics() {
        return electronics;
    }

    public void setElectronics(List<Electronics> electronics) {
        this.electronics = electronics;
    }

}
