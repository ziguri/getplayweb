/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManageBeans;

import ejbs.AppUserFacade;
import entities.AppUser;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Zueb LDA
 */
@ManagedBean(name="logged")
@SessionScoped
public class LoggedUserMb implements Serializable {
private AppUser user;
@EJB
private AppUserFacade user_ejb;
private String password;

    /**
     * Creates a new instance of LoggedUser
     */
    public LoggedUserMb() {
  
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public AppUserFacade getUser_ejb() {
        return user_ejb;
    }

    public void setUser_ejb(AppUserFacade user_ejb) {
        this.user_ejb = user_ejb;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String logout(){
    
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();
        
        return "index.xhtml";
    }
    
    public String editUser(){
        user_ejb.editUserLogado(user);
        return "principal";
    }
    
    public String deleteUser(){
        user_ejb.remove(user);
        return "index";
    }
    
}
