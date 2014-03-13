/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.AppUser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entities.Music;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.ejb.EJB;

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

    public void editMusic(Music m, AppUser u) {
        try {
            String filePath = "C:\\APPGetPlayWeb\\";
            m.setMusic_path(filePath);
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

    public List<Music> showMusicsPlaylist(String p) {//Mostra as músicas de uma determinada playlist.
        try {
            List<Music> m = (List<Music>) em.createNamedQuery("Music.findMusicByPlaylist").setParameter("playlist", p).getResultList();
            return m;
        } catch (Exception e) {
            return null;
        }
    }

}
