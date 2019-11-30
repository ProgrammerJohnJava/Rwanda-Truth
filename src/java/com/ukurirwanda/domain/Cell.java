
package com.ukurirwanda.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Cell extends Location{
    @ManyToOne
    @JoinColumn(name = "SectCode")
    private Sector sector;
    
    @OneToMany(mappedBy = "cell")
    private List<Village> village;

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public List<Village> getVillage() {
        return village;
    }

    public void setVillage(List<Village> village) {
        this.village = village;
    }
    
    
}
