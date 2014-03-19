/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejbs;


import entities.AppUser;
import entities.Music;
import entities.Playlist;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
            System.err.println("Excepcion " + e);
        }
    }
    
    public void editPlaylist(Playlist p, AppUser u)  {
        p.setUser(u);
        this.edit(p);
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
    
    public List<Music> showMusicPlaylist (Playlist p){
    
        try {
            
            List<Music> m =(List<Music>) em.createNamedQuery("Music.findMusicByPlaylist").setParameter("playlist", p).getResultList();
            return m;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Playlist> orderPlaylist (String atribute, String order, AppUser u){
            
            if (atribute.equals("Title") && order.equals("Asc")){
                List<Playlist> pl =(List<Playlist>) em.createNamedQuery("Playlist.OrderByNameAsc").setParameter("user", u).getResultList();
                return pl;
            }
            
            if (atribute.equals("Creation") && order.equals("Asc")){
                List<Playlist> pl =(List<Playlist>) em.createNamedQuery("Playlist.OrderByCreationAsc").setParameter("user", u).getResultList();
                return pl;
            }
            
            if (atribute.equals("Size") && order.equals("Asc")){
                List<Playlist> pl =(List<Playlist>) em.createNamedQuery("Playlist.OrderBySizeAsc").setParameter("user", u).getResultList();
                return pl;
            }
            
            if (atribute.equals("Title") && order.equals("Desc")){
                List<Playlist> pl =(List<Playlist>) em.createNamedQuery("Playlist.OrderByNameDesc").setParameter("user", u).getResultList();
                return pl;
            }
            
            if (atribute.equals("Creation") && order.equals("Desc")){
                List<Playlist> pl =(List<Playlist>) em.createNamedQuery("Playlist.OrderByCreationDesc").setParameter("user", u).getResultList();
                return pl;
            }
            
            if (atribute.equals("Size") && order.equals("Desc")){
                List<Playlist> pl =(List<Playlist>) em.createNamedQuery("Playlist.OrderBySizeDesc").setParameter("user", u).getResultList();
                return pl;
            }
            
            else return null;
        }
}
