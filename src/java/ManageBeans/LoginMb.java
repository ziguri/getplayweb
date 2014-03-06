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

/**
 *
 * @author Elsa
 */
@ManagedBean
@RequestScoped
public class LoginMb implements Serializable{
@EJB
    private Registered_UserFacade user;
    private String email;
    private String password;
    /**
     * Creates a new instance of LoginMb
     */
    public LoginMb() {
    }

    public Registered_UserFacade getUser() {
        return user;
    }

    public void setUser(Registered_UserFacade user) {
        this.user = user;
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
    
    public String confirmaLogin()throws Exception{
        try{
            user.find(this.email);
            return "principal";
        } catch(Exception e) {
            System.out.println("User not find" + e);
        }
        return "index";
    }
    
}
