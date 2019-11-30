
package com.ukurirwanda.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class District extends Location{
    
    @ManyToOne
    @JoinColumn(name = "ProvCode")
    private Province province;
    
    @OneToMany(mappedBy = "district")
    private List<Sector> sector;

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public List<Sector> getSector() {
        return sector;
    }

    public void setSector(List<Sector> sector) {
        this.sector = sector;
    }
    
    
}
