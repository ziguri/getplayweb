/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejbs;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entities.Playlist;

/**
 *
 * @author Zueb LDA
 */
@Stateless
public class PlaylistFacade extends AbstractFacade<Playlist> {
    @PersistenceContext(unitName = "GetPlayWebPU")
    private EntityManager em;

    public void addPlaylist(Playlist p)  {
        try{
         
        this.create(p);
        
        }catch(Exception e){
            System.err.println("Excepção " + e);
        }
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlaylistFacade() {
        super(Playlist.class);
    }
    
}
