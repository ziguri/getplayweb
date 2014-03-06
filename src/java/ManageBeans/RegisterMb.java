/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManageBeans;

import ejbs.Registered_UserFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import persistence.Registered_User;

/**
 *
 * @author Elsa
 */
@ManagedBean(name="registerMb")
@RequestScoped
public class RegisterMb implements Serializable {
    
    @EJB
    private Registered_UserFacade user;
    private String name;
    private String email;
    private String password;
    /**
     * Creates a new instance of UserManagedBean
     */
    public RegisterMb() {
       
    }
    
    public String addUser(){
    
        user.addUser(name, email, password);
        return "index";
    }

    public Registered_UserFacade getUser() {
        return user;
    }

    public void setUser(Registered_UserFacade user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
