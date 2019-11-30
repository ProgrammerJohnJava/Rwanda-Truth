
package com.ukurirwanda.dao;

import com.ukurirwanda.common.PassCode;
import com.ukurirwanda.domain.User;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

public class UserDao extends GenericDao<User>{
    public List<User> loginencrypt(String u, String pass) throws Exception {

        Session s = HibernateUtil.getSessionFactory().openSession();
        List<User> list = new ArrayList<>();

        List<User> users = new UserDao().findAll(User.class);
        String z = "";
        for (User us : users) {
            if (us.getUsername().matches(u)) {
                if ((new PassCode().decrypt(us.getPassword())).matches(pass)) {
                    list.add(us);
                }
            }

        }

        s.close();
        return list;

    }
}
