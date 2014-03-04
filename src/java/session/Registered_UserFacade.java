/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    
}
