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
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 * @author Elsa Santos
 * @author Orlando Neves
 */
@Named("logged")
@SessionScoped
public class LoggedUserMb implements Serializable {

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
    public LoggedUserMb() {
    }

    @PostConstruct
    public void init() {
        this.errorMessage = null;
    }

    /**
     * Invalidate the current session in the user logout
     *
     * @return String
     */
    public String logout() {

        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        session.invalidate();

        return "index.xhtml";
    }

    /**
     * Invokes User EJB method in order to edit logged user
     *
     * @return
     */
    public String editUser() {
        user_ejb.editUserLogado(user);
        return "principal";

    }

    /**
     * Invokes DelUser EJB method to remove logged user
     *
     * @return
     */
    public String deleteUser() {
        delUser.userRemove(user);
        return "index.xhtml";
    }

    //Getter and Setter
    /**
     * Get User
     *
     * @return
     */
    public AppUser getUser() {
        return user;
    }

    /**
     * Set User
     *
     * @param user
     */
    public void setUser(AppUser user) {
        this.user = user;
    }

    /**
     * Get User EJB
     *
     * @return
     */
    public AppUserFacade getUser_ejb() {
        return user_ejb;
    }

    /**
     * Set User EJB
     *
     * @param user_ejb
     */
    public void setUser_ejb(AppUserFacade user_ejb) {
        this.user_ejb = user_ejb;
    }

    /**
     * Get Password
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set Password
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get DelUser EJB
     *
     * @return
     */
    public DeleteUser getDelUser() {
        return delUser;
    }

    /**
     * Set DelUser EJB
     *
     * @param delUser
     */
    public void setDelUser(DeleteUser delUser) {
        this.delUser = delUser;
    }

    /**
     * Get error message
     *
     * @return
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Set error message
     *
     * @param errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
