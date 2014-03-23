/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import Exceptions.MusicsAlreadyExistInPlaylistException;
import entities.AppUser;
import entities.Music;
import entities.Playlist;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Elsa Santos
 * @author Orlando Neves
 */

@Stateless
public class PlaylistFacade extends AbstractFacade<Playlist> {

    @PersistenceContext(unitName = "GetPlayWebPU")
    private EntityManager em;

    public void addPlaylist(Playlist p, AppUser u) {
        try {
            p.setUser(u);
            this.create(p);

        } catch (Exception e) {
            System.err.println("Excepcion " + e);
        }
    }

    public void editPlaylist(Playlist p, AppUser u) {
        p.setUser(u);
        this.edit(p);
    }

    public boolean musicExistInPlaylist(Playlist p, Music m) {
        boolean igual = false;

        for (int i = 0; i < p.getMusics().size() && !igual; i++) {
            if (p.getMusics().get(i).equals(m)) {
                igual = true;
            }
        }
        return igual;
    }

    public void addMusicToPlaylist(Playlist playlist, Music music) throws MusicsAlreadyExistInPlaylistException {

        boolean equal = musicExistInPlaylist(playlist, music);

        if (equal) {
            throw new MusicsAlreadyExistInPlaylistException();
        } else {
            playlist.getMusics().add(music);
        }

        edit(playlist);
    }

    public void removeMusicFromPlaylist(Playlist playlist, Music music) {

        playlist.getMusics().remove(music);
        edit(playlist);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlaylistFacade() {
        super(Playlist.class);
    }

    public List<Playlist> showMyPlaylist(AppUser u) {

        try {

            List<Playlist> pl = (List<Playlist>) em.createNamedQuery("Playlist.findPlaylistByUser").setParameter("user", u).getResultList();
            return pl;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Music> showMusicPlaylist(Playlist p) {

        try {

            List<Music> m = (List<Music>) em.createNamedQuery("Music.findMusicByPlaylist").setParameter("playlist", p).getResultList();
            return m;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Playlist> orderPlaylist(String column, String order, AppUser u) {

        String namequery = "Playlist.OrderBy" + column + order;
        List<Playlist> pl = (List<Playlist>) em.createNamedQuery(namequery).setParameter("user", u).getResultList();
        return pl;
    }
}
