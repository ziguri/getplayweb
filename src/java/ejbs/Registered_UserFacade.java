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
import persistence.Registered_User;

/**
 *
 * @author Zueb LDA
 */
@Stateless
public class Registered_UserFacade extends AbstractFacade<Registered_User> {
    @PersistenceContext(unitName = "GetPlayWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Registered_UserFacade() {
        super(Registered_User.class);
    }
    
    public void addUser(String nome, String email, String password)  {
        try{
            
        Registered_User a = new Registered_User(nome, email, password);
        this.create(a);
        
        }catch(Exception e){
            System.out.println("Excepção " + e);
        }
    }
    
}
