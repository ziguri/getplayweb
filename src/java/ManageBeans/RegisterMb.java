/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManageBeans;

import ejbs.AppUserFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import entities.AppUser;

/**
 *
 * @author Elsa
 */
@ManagedBean(name="registerMb")
@RequestScoped
public class RegisterMb implements Serializable {
    
    @EJB
    private AppUserFacade user_ejb;
    private AppUser user;
    /**
     * Creates a new instance of UserManagedBean
     */
    public RegisterMb() {
       
    }
    
    public String addUser(){
    
        user_ejb.addUser(user);
        return "index.xhtml";
    }

    public AppUserFacade getUser_ejb() {
        return user_ejb;
    }

    public void setUser_ejb(AppUserFacade user_ejb) {
        this.user_ejb = user_ejb;
    }

    public AppUser getUser() {
        if(user == null) {
            user = new AppUser();
        }
        return user;
    }

    public void setUser(AppUser user) { 
        this.user = user;
    }

    
}
