/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManageBeans;

import ejbs.Registered_UserFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import persistence.Registered_User;

/**
 *
 * @author Elsa
 */
@ManagedBean
@SessionScoped
public class RegisterMb {
    
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
    
    public void addUser(){
    
        user.addUser(name, email, password);
    }

    public Registered_UserFacade getUser_facade() {
        return user;
    }

    public void setUser_facade(Registered_UserFacade user_facade) {
        this.user = user_facade;
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
