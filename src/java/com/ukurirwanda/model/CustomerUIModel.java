package com.ukurirwanda.model;

import com.ukurirwanda.domain.House;
import com.ukurirwanda.domain.Car;
import com.ukurirwanda.domain.ProductImage;
import com.ukurirwanda.domain.Customer;
import com.ukurirwanda.domain.Electronics;
import com.ukurirwanda.dao.ProductImageDao;
import com.ukurirwanda.dao.HouseDao;
import com.ukurirwanda.dao.CustomerDao;
import com.ukurirwanda.dao.ElectronicsDao;
import com.ukurirwanda.dao.CarDao;
import com.ukurirwanda.common.FileUpload;
import com.ukurirwanda.dao.ComponentDao;
import com.ukurirwanda.dao.InfideleDao;
import com.ukurirwanda.dao.SetupDao;
import com.ukurirwanda.dao.VillageDao;
import com.ukurirwanda.domain.Component;
import com.ukurirwanda.domain.Infidele;
import com.ukurirwanda.domain.Setup;
import com.ukurirwanda.domain.User;
import com.ukurirwanda.domain.Village;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

@ManagedBean
@SessionScoped
public class CustomerUIModel {

    private Setup setup = new Setup();
    private Customer customer = new Customer();
    private User loggedInUser = new User();
    private String pass = new String();
    private String villId = new String();
    private List<String> componentImages = new ArrayList<>();
    private List<String> carImages = new ArrayList<>();
    private List<String> electronicsImages = new ArrayList<>();
    private List<String> houseImages = new ArrayList<>();
    private List<String> infideleImages = new ArrayList<>();
    private House house = new House();
    private Car car = new Car();
    private Electronics electronics = new Electronics();
    private Component componento = new Component();
    private List<ProductImage> houses = new ArrayList<>();
    private List<ProductImage> oneCarImages = new ArrayList<>();
    private List<ProductImage> oneHouseImages = new ArrayList<>();
    private List<ProductImage> oneElectronicsImages = new ArrayList<>();
    private List<ProductImage> oneComponentImages = new ArrayList<>();
    private List<ProductImage> components = new ArrayList<>();
    private List<ProductImage> cars = new ArrayList<>();
    private List<ProductImage> electronicss = new ArrayList<>();
    private List<ProductImage> products = new ArrayList<>();
    private Infidele infidele = new Infidele();
    private ProductImage choosenHouseProductImage = new ProductImage();
    private List<Infidele> infideles = new ArrayList<>();
    private Infidele choosenInfidele = new Infidele();
    
    @PostConstruct
    public void init() {
        setup = new SetupDao().findOne(Setup.class, "6ad91e4e-c75d-4ec3-8af0-339d456a53b9");
        houseinit();
        carinit();
        electronicsinit();
        componentinit();
        infideleinit();
    }

    public void houseinit() {
        for (ProductImage p : new ProductImageDao().findAll(ProductImage.class)) {
            if (p.getType().equalsIgnoreCase("House") && p.getStatus().equalsIgnoreCase("Approved")) {
                houses.add(p);
            }
        }
    }
    
    public void infideleinit(){
        for(Infidele i: new InfideleDao().findAll(Infidele.class)){
            if(i.getStatus().equalsIgnoreCase("Approved")){
                infideles.add(i);
            }
        }
    }

    public void carinit() {
        for (ProductImage p : new ProductImageDao().findAll(ProductImage.class)) {
            if (p.getType().equalsIgnoreCase("Car") && p.getStatus().equalsIgnoreCase("Approved")) {
                cars.add(p);
            }
        }
    }
    
    public void componentinit() {
        for (ProductImage p : new ProductImageDao().findAll(ProductImage.class)) {
            if (p.getType().equalsIgnoreCase("Component") && p.getStatus().equalsIgnoreCase("Approved")) {
                components.add(p);
            }
        }
    }

    public void electronicsinit() {
        for (ProductImage p : new ProductImageDao().findAll(ProductImage.class)) {
            if (p.getType().equalsIgnoreCase("Electronics") && p.getStatus().equalsIgnoreCase("Approved")) {
                electronicss.add(p);
            }
        }
    }

    public void userInit() {
        loggedInUser = (User) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("session");
        System.out.println(loggedInUser.getCustomer().getFirstName());
    }

    public void updateCustomer() {
        new CustomerDao().update(customer);
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage("External User Profile Updated"));
    }

    public void registerHouse() {
        if (this.houseImages.isEmpty()) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Upload Product Image"));
        } else {
            Village v = new VillageDao().findOne(Village.class, villId);
            house.setVillage(v);
            userInit();
            house.setCustomer(loggedInUser.getCustomer());
            house.setRecordDate(new Date());
            new HouseDao().create(house);
            for (String x : houseImages) {
                ProductImage pi = new ProductImage();
                pi.setName(x);
                pi.setHouse(house);
                pi.setType("House");
                pi.setStatus("Approved");
                new ProductImageDao().create(pi);
            }
            houseImages.clear();
            house = new House();
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("House Registered"));
        }
    }

    public String navigateHouse(ProductImage p) {
        choosenHouseProductImage = p;
        oneHouseImages = new ProductImageDao().findByHouse(p.getHouse());
        return "singlehouse.xhtml?faces-redirect=true";
    }
    
    public String navigateCar(ProductImage p) {
        choosenHouseProductImage = p;
        oneCarImages = new ProductImageDao().findByCar(p.getCar());
        return "singlecar.xhtml?faces-redirect=true";
    }
    
    public String navigateElectronics(ProductImage p) {
        choosenHouseProductImage = p;
        oneElectronicsImages = new ProductImageDao().findByElectronics(p.getElectronics());
        return "singleelectronics.xhtml?faces-redirect=true";
    }
    public String navigateComponent(ProductImage p) {
        choosenHouseProductImage = p;
        oneComponentImages = new ProductImageDao().findByComponent(p.getComponent());
        return "singlecomponent.xhtml?faces-redirect=true";
    }
    public String navigateInfidele(Infidele p) {
        choosenInfidele = p;
        return "singleinfidele.xhtml?faces-redirect=true";
    }

    public void registerCar() {
        if (this.carImages.isEmpty()) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Upload Product Image"));
        } else {
            userInit();
            car.setCustomer(loggedInUser.getCustomer());
            car.setRecordDate(new Date());
            new CarDao().create(car);
            for (String x : carImages) {
                ProductImage pi = new ProductImage();
                pi.setName(x);
                pi.setCar(car);
                pi.setType("Car");
                pi.setStatus("Approved");
                new ProductImageDao().create(pi);
            }
            carImages.clear();
            car = new Car();
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Car Registered"));
        }
    }

    public void registerElectronics() {
        if (this.electronicsImages.isEmpty()) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Upload Product Image"));
        } else {
            userInit();
            electronics.setCustomer(loggedInUser.getCustomer());
            new ElectronicsDao().create(electronics);
            for (String x : electronicsImages) {
                ProductImage pi = new ProductImage();
                pi.setName(x);
                pi.setElectronics(electronics);
                pi.setType("Electronics");
                pi.setStatus("Approved");
                new ProductImageDao().create(pi);
            }
            electronicsImages.clear();
            electronics = new Electronics();
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Electronics Registered"));
        }
    }

    public void registerComponent() {
        if (this.componentImages.isEmpty()) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Upload Product Image"));
        } else {
            userInit();
            componento.setCustomer(loggedInUser.getCustomer());
            new ComponentDao().create(componento);
            for (String x : componentImages) {
                ProductImage pi = new ProductImage();
                pi.setName(x);
                pi.setComponent(componento);
                pi.setType("Component");
                pi.setStatus("Approved");
                new ProductImageDao().create(pi);
            }
            componentImages.clear();
            componento = new Component();
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Product Registered"));
        }
    }

    public void registerInfidele() {
        if (this.infideleImages.isEmpty()) {
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Upload Person Image"));
        } else {
            for (String x : infideleImages) {
                infidele.setPhoto(x);
            }
            userInit();
            infidele.setCustomer(loggedInUser.getCustomer());
            infidele.setStatus("Approved");
            new InfideleDao().create(infidele);

            infideleImages.clear();
            infidele = new Infidele();
            FacesContext fc = FacesContext.getCurrentInstance();
            fc.addMessage(null, new FacesMessage("Infidele Registered"));
        }
    }

    public void UploadHouse(FileUploadEvent event) {
        houseImages.add(new FileUpload().Upload(event, "C:\\Users\\student\\Documents\\NetBeansProjects\\ukurirwanda\\web\\uploads\\product\\"));
    }

    public void UploadCar(FileUploadEvent event) {
        carImages.add(new FileUpload().Upload(event, "C:\\Users\\student\\Documents\\NetBeansProjects\\ukurirwanda\\web\\uploads\\product\\"));
    }

    public void UploadComponent(FileUploadEvent event) {
        componentImages.add(new FileUpload().Upload(event, "C:\\Users\\student\\Documents\\NetBeansProjects\\ukurirwanda\\web\\uploads\\product\\"));
    }

    public void UploadElectronics(FileUploadEvent event) {
        electronicsImages.add(new FileUpload().Upload(event, "C:\\Users\\student\\Documents\\NetBeansProjects\\ukurirwanda\\web\\uploads\\product\\"));
    }

    public void UploadInfidele(FileUploadEvent event) {
        infideleImages.add(new FileUpload().Upload(event, "C:\\Users\\student\\Documents\\NetBeansProjects\\ukurirwanda\\web\\uploads\\infidele\\"));
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getVillId() {
        return villId;
    }

    public void setVillId(String villId) {
        this.villId = villId;
    }

    public List<String> getCarImages() {
        return carImages;
    }

    public void setCarImages(List<String> carImages) {
        this.carImages = carImages;
    }

    public List<String> getElectronicsImages() {
        return electronicsImages;
    }

    public void setElectronicsImages(List<String> electronicsImages) {
        this.electronicsImages = electronicsImages;
    }

    public List<String> getHouseImages() {
        return houseImages;
    }

    public void setHouseImages(List<String> houseImages) {
        this.houseImages = houseImages;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Electronics getElectronics() {
        return electronics;
    }

    public void setElectronics(Electronics electronics) {
        this.electronics = electronics;
    }

    public List<ProductImage> getProducts() {
        return products;
    }

    public void setProducts(List<ProductImage> products) {
        this.products = products;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public List<String> getInfideleImages() {
        return infideleImages;
    }

    public void setInfideleImages(List<String> infideleImages) {
        this.infideleImages = infideleImages;
    }

    public Infidele getInfidele() {
        return infidele;
    }

    public void setInfidele(Infidele infidele) {
        this.infidele = infidele;
    }

    public List<ProductImage> getHouses() {
        return houses;
    }

    public void setHouses(List<ProductImage> houses) {
        this.houses = houses;
    }

    public List<ProductImage> getCars() {
        return cars;
    }

    public void setCars(List<ProductImage> cars) {
        this.cars = cars;
    }

    public List<ProductImage> getElectronicss() {
        return electronicss;
    }

    public void setElectronicss(List<ProductImage> electronicss) {
        this.electronicss = electronicss;
    }

    public List<ProductImage> getOneHouseImages() {
        return oneHouseImages;
    }

    public void setOneHouseImages(List<ProductImage> oneHouseImages) {
        this.oneHouseImages = oneHouseImages;
    }

    public ProductImage getChoosenHouseProductImage() {
        return choosenHouseProductImage;
    }

    public void setChoosenHouseProductImage(ProductImage choosenHouseProductImage) {
        this.choosenHouseProductImage = choosenHouseProductImage;
    }

    public List<Infidele> getInfideles() {
        return infideles;
    }

    public void setInfideles(List<Infidele> infideles) {
        this.infideles = infideles;
    }

    public Infidele getChoosenInfidele() {
        return choosenInfidele;
    }

    public void setChoosenInfidele(Infidele choosenInfidele) {
        this.choosenInfidele = choosenInfidele;
    }

    public List<ProductImage> getComponents() {
        return components;
    }

    public void setComponents(List<ProductImage> components) {
        this.components = components;
    }

    public Component getComponento() {
        return componento;
    }

    public void setComponento(Component componento) {
        this.componento = componento;
    }

    public List<String> getComponentImages() {
        return componentImages;
    }

    public void setComponentImages(List<String> componentImages) {
        this.componentImages = componentImages;
    }

    public List<ProductImage> getOneCarImages() {
        return oneCarImages;
    }

    public void setOneCarImages(List<ProductImage> oneCarImages) {
        this.oneCarImages = oneCarImages;
    }

    public List<ProductImage> getOneElectronicsImages() {
        return oneElectronicsImages;
    }

    public void setOneElectronicsImages(List<ProductImage> oneElectronicsImages) {
        this.oneElectronicsImages = oneElectronicsImages;
    }

    public List<ProductImage> getOneComponentImages() {
        return oneComponentImages;
    }

    public void setOneComponentImages(List<ProductImage> oneComponentImages) {
        this.oneComponentImages = oneComponentImages;
    }

    public Setup getSetup() {
        return setup;
    }

    public void setSetup(Setup setup) {
        this.setup = setup;
    }

}
