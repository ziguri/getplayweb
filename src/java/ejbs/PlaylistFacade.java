/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejbs;


import entities.AppUser;
import entities.Music;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entities.Playlist;
import java.util.List;

/**
 *
 * @author Zueb LDA
 */
@Stateless
public class PlaylistFacade extends AbstractFacade<Playlist> {
    @PersistenceContext(unitName = "GetPlayWebPU")
    private EntityManager em;

    public void addPlaylist(Playlist p, AppUser u)  {
        try{
        p.setUser(u);
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
    
    public List<Playlist> showMyPlaylist (AppUser u){
    
        try {
            
            List<Playlist> pl =(List<Playlist>) em.createNamedQuery("Playlist.findPlaylistByUser").setParameter("user", u).getResultList();
            return pl;
        } catch (Exception e) {
            return null;
        }
    }
}
