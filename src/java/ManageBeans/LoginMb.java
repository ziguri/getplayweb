/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import Exceptions.DuplicateEmailException;
import ejbs.AppUserFacade;
import ejbs.CodificarMD5;
import entities.AppUser;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Elsa
 */
@Named
@RequestScoped
public class LoginMb {

    @Inject
    private AppUserFacade user_ejb;
    private AppUser user;
    private String email;
    private String password;
    @Inject
    private LoggedUserEjb logado;

    private String errorMessageExperience;
    private String successfullyRegistered;

    /**
     * Creates a new instance of LoginMb
     */
    public LoginMb() {
    }

    @PostConstruct
    public void init() {
        this.errorMessageExperience = null;
        this.successfullyRegistered = null;
    }

    public AppUserFacade getUser_ejb() {
        return user_ejb;
    }

    public void setUser_ejb(AppUserFacade user_ejb) {
        this.user_ejb = user_ejb;
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

    public LoggedUserEjb getLogado() {
        return logado;
    }

    public void setLogado(LoggedUserEjb logado) {
        this.logado = logado;
    }

    public AppUser getUser() {
        if (user == null) {
            user = new AppUser();
        }
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public String getSuccessfullyRegistered() {
        return successfullyRegistered;
    }

    public void setSuccessfullyRegistered(String successfullyRegistered) {
        this.successfullyRegistered = successfullyRegistered;
    }

    public String confirmaLogin() {

        String pass = CodificarMD5.cryptWithMD5(this.password);
        AppUser us = user_ejb.validaPassword(this.email, pass);

        if (us != null) {

            logado.setUser(us);
            logado.getUser().getName();
            return "listAllMusics";

        } else {
            errorMessageExperience = "Login or Password Invalid. Please try again.";
            return "index";
        }
    }

    public String deleteUser() {
        user_ejb.remove(logado.getUser());
        return "index.xhtml";
    }

    //Começa aqui a parte que estava em "RegisterMb
    public String addUser() {

        if (user_ejb.existUser(user.getEmail()) == null) {
            user_ejb.addUser(user);
            return "index.xhtml";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User already registered"));
            return "registo";
        }
    }

    public void addUser2() {
        try {
            user_ejb.addUser2(user);
            successfullyRegistered = "Successfully Registered! Click \"return\" to login";
//            return "index.xhtml";
        } catch (DuplicateEmailException ex) {
            Logger.getLogger(LoginMb.class.getName()).log(Level.SEVERE, null, ex);
            errorMessageExperience = ex.getMessage();
//            return null;
        }
    }

    public String getErrorMessageExperience() {
        return errorMessageExperience;
    }

    public void setErrorMessageExperience(String errorMessageExperience) {
        this.errorMessageExperience = errorMessageExperience;
    }

}
