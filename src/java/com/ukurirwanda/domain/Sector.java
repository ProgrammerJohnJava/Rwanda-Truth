
package com.ukurirwanda.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Sector extends Location{
    @ManyToOne
    @JoinColumn(name = "DistCode")
    private District district;
    
    @OneToMany(mappedBy = "sector")
    private List<Cell> cell;

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<Cell> getCell() {
        return cell;
    }

    public void setCell(List<Cell> cell) {
        this.cell = cell;
    }
    
    
}
