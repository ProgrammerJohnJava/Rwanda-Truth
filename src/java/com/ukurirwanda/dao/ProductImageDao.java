
package com.ukurirwanda.dao;

import com.ukurirwanda.domain.Car;
import com.ukurirwanda.domain.Component;
import com.ukurirwanda.domain.Customer;
import com.ukurirwanda.domain.Electronics;
import com.ukurirwanda.domain.House;
import com.ukurirwanda.domain.ProductImage;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class ProductImageDao extends GenericDao<ProductImage>{
    public List<ProductImage> findByHouse(House iq){
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("select a from ProductImage a where a.house=:v");
        q.setParameter("v", iq);
        List<ProductImage> list = q.list();
        s.close();
        return list;
     }
    
    public List<ProductImage> findByCar(Car iq){
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("select a from ProductImage a where a.car=:v");
        q.setParameter("v", iq);
        List<ProductImage> list = q.list();
        s.close();
        return list;
     }
    
    public List<ProductImage> findByElectronics(Electronics iq){
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("select a from ProductImage a where a.electronics=:v");
        q.setParameter("v", iq);
        List<ProductImage> list = q.list();
        s.close();
        return list;
     }
    
    public List<ProductImage> findByComponent(Component iq){
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("select a from ProductImage a where a.component=:v");
        q.setParameter("v", iq);
        List<ProductImage> list = q.list();
        s.close();
        return list;
     }
    
    
    public List<ProductImage> findByHouseAndCustomer(String iq, Customer x){
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("select a from ProductImage a where a.type=:v and a.house.customer = :x");
        q.setParameter("v", iq);
        q.setParameter("x", x);
        List<ProductImage> list = q.list();
        s.close();
        return list;
     }
    
    public List<ProductImage> findByCarAndCustomer(String iq, Customer x){
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("select a from ProductImage a where a.type=:v and a.car.customer = :x");
        q.setParameter("v", iq);
        q.setParameter("x", x);
        List<ProductImage> list = q.list();
        s.close();
        return list;
     }
    
    public List<ProductImage> findByElectronicsAndCustomer(String iq, Customer x){
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("select a from ProductImage a where a.type=:v and a.electronics.customer = :x");
        q.setParameter("v", iq);
        q.setParameter("x", x);
        List<ProductImage> list = q.list();
        s.close();
        return list;
     }
    
    public List<ProductImage> findByComponentAndCustomer(String iq, Customer x){
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("select a from ProductImage a where a.type=:v and a.component.customer = :x");
        q.setParameter("v", iq);
        q.setParameter("x", x);
        List<ProductImage> list = q.list();
        s.close();
        return list;
     }
    
}
