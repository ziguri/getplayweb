/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManageBeans;

import ejbs.MusicFacade;
import ejbs.PlaylistFacade;
import entities.Playlist;
import java.util.GregorianCalendar;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Elsa
 */
@ManagedBean (name="playlistMb")
@RequestScoped
public class PlaylistMb {
    @EJB
    private PlaylistFacade playlist_ejb;
    @EJB
    private MusicFacade musics_ejb;
    private Playlist playlist;
    @ManagedProperty(value="#{logged}")
    private LoggedUserMb user;

    /**
     * Creates a new instance of PlaylistMB
     */
    public PlaylistMb() {
        this.playlist = new Playlist();
    }

    public String addPlaylist(){
        
        GregorianCalendar gc = new GregorianCalendar();

        playlist.setPlaylist_size(0);
        playlist.setCreation_date(gc.getTime());
//        playlist.setUser(user.getUser());
        playlist_ejb.addPlaylist(playlist);
        return "principal";
    }
    
    public PlaylistFacade getPlaylist_ejb() {
        return playlist_ejb;
    }

    public void setPlaylist_ejb(PlaylistFacade playlist_ejb) {
        this.playlist_ejb = playlist_ejb;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public MusicFacade getMusics_ejb() {
        return musics_ejb;
    }

    public void setMusics_ejb(MusicFacade musics_ejb) {
        this.musics_ejb = musics_ejb;
    }

    public LoggedUserMb getUser() {
        return user;
    }

    public void setUser(LoggedUserMb user) {
        this.user = user;
    }
    
}
