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
    private AppUserFacade user;
    private String email;
    private String password;
    private LoggedUser logado;
    /**
     * Creates a new instance of LoginMb
     */
    public LoginMb() {
    }

    public AppUserFacade getUser() {
        return user;
    }

    public void setUser(AppUserFacade user) {
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

    public LoggedUser getLogado() {
        return logado;
    }

    public void setLogado(LoggedUser logado) {
        this.logado = logado;
    }
    
    public String confirmaLogin()throws Exception{
        AppUser us =  user.validaPassword(this.email, this.password);
        
        if(us!= null){
        
            logado.setUser(us);
            return"principal.xhtml";
            
        }else{
        
            return"index.xhtml";
        }
        
//        try{
//            user.find(this.email);
//            return "principal";
//        } catch(Exception e) {
//            System.out.println("User not find" + e);
//        }
//        return "index";
    }
    
}
