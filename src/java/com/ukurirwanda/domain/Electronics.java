
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
public class Electronics implements Serializable{
    @Id
    private String id = UUID.randomUUID().toString();
    private String name;
    private Double RAM;
    //Internal Storage HDD, SDD
    private Double storage;
    //Duration of power if not plugged in minutes
    private int powerResistanceMin;
    private String processor;
    
    @Temporal(TemporalType.DATE)
    //The time of uploading on Site
    private final Date recordDate = new Date();
    private String description;
    private Double price;
    //Status shall be Sell or Rent or Completed or Cancelled
    private String status;
    
    @OneToMany(mappedBy = "electronics", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<ProductImage> productImage;

    @ManyToOne
    private Customer customer;

    public Double getRAM() {
        return RAM;
    }

    public void setRAM(Double RAM) {
        this.RAM = RAM;
    }

    public Double getStorage() {
        return storage;
    }

    public void setStorage(Double storage) {
        this.storage = storage;
    }

    public int getPowerResistanceMin() {
        return powerResistanceMin;
    }

    public void setPowerResistanceMin(int powerResistanceMin) {
        this.powerResistanceMin = powerResistanceMin;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
