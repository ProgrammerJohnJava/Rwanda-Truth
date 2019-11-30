
package com.ukurirwanda.dao;

import com.ukurirwanda.domain.Component;
import com.ukurirwanda.domain.Customer;
import com.ukurirwanda.domain.Infidele;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

public class InfideleDao extends GenericDao<Infidele>{
    public List<Infidele> findByPublisher(Customer iq){
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("select a from Infidele a where a.customer=:v");
        q.setParameter("v", iq);
        List<Infidele> list = q.list();
        s.close();
        return list;
     }
}
