/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import Exceptions.DuplicateEmailException;
import ejbs.AppUserFacade;
import ejbs.DeleteUser;
import entities.AppUser;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Zueb LDA
 */
@ManagedBean(name = "logged")
@SessionScoped
public class LoggedUserMb implements Serializable {

    private AppUser user;
    @EJB
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
        try {
            user_ejb.editUserLogado(user, user.getEmail());
            return "principal";
        } catch (DuplicateEmailException ex) {
            Logger.getLogger(LoggedUserMb.class.getName()).log(Level.SEVERE, null, ex);
            errorMessage = ex.getMessage();
            return null;
        }
        
    }

    public String deleteUser() {
        delUser.userRemove(user);
        return "index.xhtml";
    }

}
