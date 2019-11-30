package com.ukurirwanda.model;

import com.ukurirwanda.dao.CellDao;
import com.ukurirwanda.dao.DistrictDao;
import com.ukurirwanda.dao.ProvinceDao;
import com.ukurirwanda.dao.SectorDao;
import com.ukurirwanda.dao.VillageDao;
import com.ukurirwanda.domain.Cell;
import com.ukurirwanda.domain.District;
import com.ukurirwanda.domain.Province;
import com.ukurirwanda.domain.Sector;
import com.ukurirwanda.domain.Village;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "lm")
@SessionScoped
public class LocationModel {

    private Province province = new Province();
    private District district = new District();
    private Sector sector = new Sector();
    private Cell cell = new Cell();
    private Village village = new Village();

    private List<Province> provinces = new ProvinceDao().findAll(Province.class);
    private List<District> districts = new ArrayList<>();
    private List<Sector> sectors = new ArrayList<>();
    private List<Cell> cells = new ArrayList<>();
    private List<Village> villages = new ArrayList<>();

    private String provId = new String();
    private String distId;
    private String sectId = new String();
    private String cellId = new String();
    private String villId = new String();

    public void registerProvince() {
        new ProvinceDao().create(province);
        province = new Province();
        provinces = new ProvinceDao().findAll(Province.class);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage("Registered"));
    }

    public void registerDistrict() {
        Province p = new ProvinceDao().findOne(Province.class, provId);
        district.setProvince(p);
        new DistrictDao().create(district);
        district = new District();
        districts = new DistrictDao().findAll(District.class);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage("Registered"));
    }

    public void registerSector() {
        System.out.println(distId);
        District d = new DistrictDao().findOne(District.class, distId);
        sector.setDistrict(d);
        new SectorDao().create(sector);
        sector = new Sector();
        sectors = new SectorDao().findAll(Sector.class);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage("Registered"));
    }

    public void registerCell() {
        Sector s = new SectorDao().findOne(Sector.class, sectId);
        cell.setSector(s);
        new CellDao().create(cell);
        cell = new Cell();
        cells = new CellDao().findAll(Cell.class);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage("Registered"));
    }

    public void registerVillage() {
        Cell c = new CellDao().findOne(Cell.class, cellId);
        village.setCell(c);
        new VillageDao().create(village);
        village = new Village();
        villages = new VillageDao().findAll(Village.class);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage("Registered"));
    }

    public void districtInit() {
        //clearing district already filled in combo 
        districts.clear();
        //Checking if district is in province selected
        for (District d : new DistrictDao().findAll(District.class)) {
            if (d.getProvince().getCode().equalsIgnoreCase(provId)) {
                districts.add(d);
            }
        }
    }

    public void sectorInit() {
        //clearing district already filled in combo 
        sectors.clear();
        //Checking if district is in province selected
        for (Sector d : new SectorDao().findAll(Sector.class)) {
            if (d.getDistrict().getCode().equalsIgnoreCase(distId)) {
                sectors.add(d);
            }
        }
    }

    public void cellInit() {
        //clearing district already filled in combo 
        cells.clear();
        //Checking if district is in province selected
        for (Cell d : new CellDao().findAll(Cell.class)) {
            if (d.getSector().getCode().equalsIgnoreCase(sectId)) {
                cells.add(d);
            }
        }
    }

    public void villageInit() {
        //clearing district already filled in combo 
        villages.clear();
        //Checking if district is in province selected
        for (Village d : new VillageDao().findAll(Village.class)) {
            if (d.getCell().getCode().equalsIgnoreCase(cellId)) {
                villages.add(d);
            }
        }
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

    public List<Sector> getSectors() {
        return sectors;
    }

    public void setSectors(List<Sector> sectors) {
        this.sectors = sectors;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public List<Village> getVillages() {
        return villages;
    }

    public void setVillages(List<Village> villages) {
        this.villages = villages;
    }

    public String getProvId() {
        return provId;
    }

    public void setProvId(String provId) {
        this.provId = provId;
    }

    public String getDistId() {
        return distId;
    }

    public void setDistId(String distId) {
        this.distId = distId;
    }

    public String getSectId() {
        return sectId;
    }

    public void setSectId(String sectId) {
        this.sectId = sectId;
    }

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public String getVillId() {
        return villId;
    }

    public void setVillId(String villId) {
        this.villId = villId;
    }

}
