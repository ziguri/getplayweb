/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManageBeans;

import entities.AppUser;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Zueb LDA
 */
@ManagedBean
@SessionScoped
public class LoggedUser {
private AppUser user;
    /**
     * Creates a new instance of LoggedUser
     */
    public LoggedUser() {
  
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
    
    public String logout(){
    
        
    }
    
}
