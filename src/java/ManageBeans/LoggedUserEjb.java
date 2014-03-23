/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import ejbs.AppUserFacade;
import ejbs.DeleteUser;
import entities.AppUser;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Zueb LDA
 */
//@ManagedBean(name = "logged")
@SessionScoped
@Stateful(name = "logged")
public class LoggedUserEjb implements Serializable {

    private AppUser user;
    @Inject
    private AppUserFacade user_ejb;
    private String password;
    @Inject
    private DeleteUser delUser;
    private String errorMessage;

    /**
     * Creates a new instance of LoggedUser
     */
    public LoggedUserEjb() {
    }

    @PostConstruct
    public void init() {
        this.errorMessage = null;
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

    public DeleteUser getDelUser() {
        return delUser;
    }

    public void setDelUser(DeleteUser delUser) {
        this.delUser = delUser;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String logout() {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();

        return "index.xhtml";
    }

    public String editUser() {
        user_ejb.editUserLogado(user);
        return "principal";

    }

    public String deleteUser() {
        delUser.userRemove(user);
        return "index.xhtml";
    }

}
