
package com.ukurirwanda.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class House implements Serializable {
    @Id
    private String id = UUID.randomUUID().toString();
    private String upi;
    private int bedroomNo;
    private int bathroomNo;
    private String type;
    private String address;    
    @Temporal(TemporalType.DATE)
    //The time of uploading on Site
    private Date recordDate = new Date();
    private String description;
    private Double price;
    //Status shall be Sell or Rent or Completed or Cancelled
    private String status;
    
    @OneToMany(mappedBy = "house", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<ProductImage> productImage;

    @ManyToOne
    private Customer customer;
    
    @ManyToOne
    private Village village;
    
    public String getUpi() {
        return upi;
    }

    public void setUpi(String upi) {
        this.upi = upi;
    }

    public int getBedroomNo() {
        return bedroomNo;
    }

    public void setBedroomNo(int bedroomNo) {
        this.bedroomNo = bedroomNo;
    }

    public int getBathroomNo() {
        return bathroomNo;
    }

    public void setBathroomNo(int bathroomNo) {
        this.bathroomNo = bathroomNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProductImage> getProductImage() {
        return productImage;
    }

    public void setProductImage(List<ProductImage> productImage) {
        this.productImage = productImage;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }
    
}
