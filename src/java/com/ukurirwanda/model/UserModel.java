
package com.ukurirwanda.model;

import com.ukurirwanda.dao.UserDao;
import com.ukurirwanda.domain.User;
import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class UserModel {

    private User user = new User();

    private User userDetails = new User();

    private UserDao userDao = new UserDao();

    private List<User> users;

    private String username = new String();

    private String password = new String();

    private String userdetails = new String();

    private String sid = new String();

    private String sectid = new String();

    public String login() throws IOException, Exception {
        String msg = "";
        findUser();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        if(user != null){
            if(user.getStatus().matches("Active")){
                if(user.getAccess().matches("Owner")){
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("session", user);
                    ec.redirect(ec.getRequestContextPath() + "/faces/owner/config.xhtml");
                    msg = "faces/owner/config.xhtml?faces-redirect=true";
                }else if(user.getAccess().matches("Customer")){
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("session", user);
                    ec.redirect(ec.getRequestContextPath() + "/faces/user/houseregister.xhtml");
                    
                    msg =  "faces/user/houseregister.xhtml?faces-redirect=true";
                }
            }else if(user.getStatus().matches("Blocked")){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Your Account is not Blocked"));
                ec.redirect(ec.getRequestContextPath() + "/faces/index.xhtml");
                msg =  "faces/index.xhtml";
            }
        }else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Login Invalid"));
            ec.redirect(ec.getRequestContextPath() + "/faces/index.xhtml");
            msg =  "faces/index.xhtml";
        }
        return msg;

    }

    public void findUser() throws Exception {
        List<User> usersLogin = new UserDao().loginencrypt(username, password);

        if (!usersLogin.isEmpty()) {
            for (User u : usersLogin) {
                user = u;
            }
        } else {
            user = null;
        }
    }

    public void logout() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        user = null;
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/faces/index.xhtml");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(User userDetails) {
        this.userDetails = userDetails;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserdetails() {
        return userdetails;
    }

    public void setUserdetails(String userdetails) {
        this.userdetails = userdetails;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSectid() {
        return sectid;
    }

    public void setSectid(String sectid) {
        this.sectid = sectid;
    }

}
