/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import Exception.SearchNullException;
import entities.AppUser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entities.Music;
import entities.Playlist;
import java.util.List;

/**
 *
 * @author Zueb LDA
 */
@Stateless
public class MusicFacade extends AbstractFacade<Music> {

    @PersistenceContext(unitName = "GetPlayWebPU")
    private EntityManager em;

    public void addMusic(Music m, AppUser u, String path) {
        try {
            m.setMusic_path(path);
            m.setUser(u);
            this.create(m);
        } catch (Exception e) {
            System.out.println("Excepção " + e);
        }
    }

    //Método não utilizado. Apagar comentário se passar a ser
    public void editMusic(Music m, AppUser u) {
        try {
            m.setUser(u);
            this.edit(m);

        } catch (Exception e) {
            System.out.println("Excepção " + e);
        }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MusicFacade() {
        super(Music.class);
    }

    public List<Music> showAllMusics() {//Mostra todas as músicas da aplicação.
        try {
            List<Music> m = (List<Music>) em.createNamedQuery("Music.findAll").getResultList();
            return m;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Music> showMusicsPlaylist(Playlist p) {

        try {
            List<Music> m = (List<Music>) em.createNamedQuery("Music.findMusicByPlaylist").setParameter("playlists", p).getResultList();
            return m;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Music> searchByColumn(String column, String word) throws SearchNullException {//Mostra as músicas resultantes de uma pesquisa.
        
        if (column.equals("Title")){       
        List<Music> m = (List<Music>) em.createNamedQuery("Music.findMusicByTitle").setParameter("word", "%" + word + "%").getResultList();
        return m;
        }
        
        if (column.equals("Artist")){       
        List<Music> m = (List<Music>) em.createNamedQuery("Music.findMusicByArtist").setParameter("word", "%" + word + "%").getResultList();
        return m;
        }
        
        if (column.equals("ArTi")){       
        List<Music> m = (List<Music>) em.createNamedQuery("Music.findMusicByTitleOrArtist").setParameter("word", "%" + word + "%").getResultList();
        return m;
        }
        
        else{
            throw new SearchNullException();
        }
    }
    
    public List<Music> showUserMusics (AppUser u){
    
        try {
            
            List<Music> mus =(List<Music>) em.createNamedQuery("Music.findAllFromUser").setParameter("user", u).getResultList();
            return mus;
        } catch (Exception e) {
            return null;
        }
    }
}
