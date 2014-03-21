/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import Exceptions.DuplicateEmailException;
import entities.AppUser;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Zueb LDA
 */
@Stateless
public class AppUserFacade extends AbstractFacade<AppUser> {

    @PersistenceContext(unitName = "GetPlayWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AppUserFacade() {
        super(AppUser.class);
    }

    public void addUser(AppUser u) {
        String pass = CodificarMD5.cryptWithMD5(u.getPassword());
        u.setPassword(pass);
        this.create(u);
    }

    public void addUser2(AppUser u) throws DuplicateEmailException {
        String pass = CodificarMD5.cryptWithMD5(u.getPassword());
        u.setPassword(pass);
        try {
            this.existUser2(u.getEmail());
            this.create(u);
        } catch (DuplicateEmailException ex) {
            Logger.getLogger(AppUserFacade.class.getName()).log(Level.SEVERE, null, ex);
            throw new DuplicateEmailException();
        }

    }

    public void editUserLogado(AppUser u) {
        try {
            String pass = CodificarMD5.cryptWithMD5(u.getPassword());
            u.setPassword(pass);
            this.edit(u);
        } catch (Exception e) {
            System.err.println("Excepção " + e);
        }
    }

    public AppUser existUser(String email) {

        try {
            AppUser u = (AppUser) em.createNamedQuery("appuser.findByEmail").setParameter("email", email).getSingleResult();
            return u;
        } catch (Exception e) {

            return null;
        }
    }

    public boolean existUser2(String email) throws DuplicateEmailException {
        try {
            AppUser u = (AppUser) em.createNamedQuery("appuser.findByEmail").setParameter("email", email).getSingleResult();
            throw new DuplicateEmailException();
        } catch (NoResultException ex) {
            return true;
        }

    }

    public AppUser validaPassword(String email, String password) {

        AppUser u = existUser(email);

        if (u != null && password.equals(u.getPassword())) {

            return u;
        } else {

            return null;
        }
    }

}
