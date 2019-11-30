
package com.ukurirwanda.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Province extends Location{
    
    @OneToMany(mappedBy = "province")
    private List<District> district;

    public List<District> getDistrict() {
        return district;
    }

    public void setDistrict(List<District> district) {
        this.district = district;
    }
    
    
}
