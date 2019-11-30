
package com.ukurirwanda.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class GenericDao<X> {
    public void create(X x){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.save(x);
        s.getTransaction().commit();
        s.close();
    }
    
    public void update(X x){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.update(x);
        s.getTransaction().commit();
        s.close();
    }
    
    public void delete(X x){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();
        s.delete(x);
        s.getTransaction().commit();
        s.close();
    }
    
    public X findOne(Class c, Serializable id){
        Session s = HibernateUtil.getSessionFactory().openSession();
        X x = (X) s.get(c, id);
        s.close();
        return x;
    }
    
    public List<X> findAll(Class c){
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("FROM "+c.getName()+" a");
        List<X> list = q.list();
        s.close();
        return list;
    }
}
