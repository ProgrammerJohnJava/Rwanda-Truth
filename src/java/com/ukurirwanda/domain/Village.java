
package com.ukurirwanda.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Village extends Location{
    @ManyToOne
    @JoinColumn(name = "cellCode")
    private Cell cell;
    
    @OneToMany(mappedBy = "village", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<House> house;

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public List<House> getHouse() {
        return house;
    }

    public void setHouse(List<House> house) {
        this.house = house;
    }

}
