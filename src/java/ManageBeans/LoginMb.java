/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import ejbs.AppUserFacade;
import ejbs.CodificarMD5;
import entities.AppUser;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Elsa
 */
@ManagedBean
@RequestScoped
public class LoginMb implements Serializable {

    @EJB
    private AppUserFacade user;
    private String email;
    private String password;
    @ManagedProperty(value = "#{logged}")
    private LoggedUserMb logado;

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

    public LoggedUserMb getLogado() {
        return logado;
    }

    public void setLogado(LoggedUserMb logado) {
        this.logado = logado;
    }

    public String confirmaLogin() {

        String pass = CodificarMD5.cryptWithMD5(this.password);
        AppUser us = user.validaPassword(this.email, pass);

        if (us != null) {

            logado.setUser(us);
            return "listAllMusics";

        } else {

            return "index";
        }
    }

        public String deleteUser(){
        user.remove(logado.getUser());
        return "index.xhtml";
    }
}
