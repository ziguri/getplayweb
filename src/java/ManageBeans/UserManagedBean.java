/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManageBeans;

import ejbs.Registered_UserFacade;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import persistence.Registered_User;

/**
 *
 * @author Elsa
 */
@ManagedBean
@SessionScoped
public class UserManagedBean {
    private Registered_User user;
    private Registered_UserFacade user_facade;
    
    /**
     * Creates a new instance of UserManagedBean
     */
    public UserManagedBean() {
    }
    
//    public boolean verifica_login(String email, String pass){
//        boolean verifica = false;
//        
//    }
    
    
    
}
