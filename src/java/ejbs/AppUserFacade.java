/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejbs;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ValidationException;
import entities.AppUser;

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
    
    public void addUser(AppUser u)  {
        try{
            
        
        this.create(u);
        
        }catch(Exception e){
            System.out.println("Excepção " + e);
        }
    }
    
    private AppUser existUser(String email){
    
        try{
        AppUser u = (AppUser) em.createNamedQuery("AppUser.findByEmail").setParameter("email", email).getSingleResult();
        return u;
        }catch(Exception e){
        
            return null;
        }
    }
    
    public AppUser validaPassword (String email, String password){
    
        AppUser u = existUser(email);
        
        if(u!=null && password.equals(u.getPassword())){
        
            return u;
        }else{
        
            return null;
        }
    }
    
}
