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
    private Registered_UserFacade user_ejb;
    private Registered_User user;
    /**
     * Creates a new instance of UserManagedBean
     */
    public RegisterMb() {
       
    }
    
    public String addUser(){
    
        user_ejb.addUser(user);
        return "principal";
    }

    public Registered_UserFacade getUser_ejb() {
        return user_ejb;
    }

    public void setUser_ejb(Registered_UserFacade user_ejb) {
        this.user_ejb = user_ejb;
    }

    public Registered_User getUser() {
        if(user == null) {
            user = new Registered_User();
        }
        return user;
    }

    public void setUser(Registered_User user) { 
        this.user = user;
    }

    
}
