/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManageBeans;

import ejbs.MusicFacade;
import ejbs.PlaylistFacade;
import entities.Music;
import entities.Playlist;
import java.util.GregorianCalendar;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Elsa
 */
@ManagedBean (name="requestPlaylistMb")
@RequestScoped
public class RequestPlaylistMb {
    @EJB
    private PlaylistFacade playlist_ejb;
    
    @EJB
    private MusicFacade musics_ejb;
    
    @ManagedProperty(value="#{logged}")
    private LoggedUserMb user;
    
    private DataModel<Playlist> play;
    private Playlist playlist;

    /**
     * Creates a new instance of PlaylistMB
     */
    public RequestPlaylistMb() {
        this.playlist = new Playlist();
        this.play = null;
    }

    public String addPlaylist(){
        
        GregorianCalendar gc = new GregorianCalendar();

        playlist.setPlaylist_size(0);
        playlist.setCreation_date(gc.getTime());
        playlist_ejb.addPlaylist(playlist, user.getUser());
        return "principal";
    } 
      
    public DataModel<Playlist> getMyPlaylist() {
        if (playlist_ejb!= null) {
            DataModel model = (DataModel<Playlist>) new ListDataModel(playlist_ejb.showMyPlaylist(user.getUser()));
            play = model;
            return model;
        }
        return null;
    }
    
      
    public String destroy() {
        playlist = (Playlist) this.play.getRowData();
        playlist_ejb.remove(playlist);
        getMyPlaylist();
        return "listMyPlaylist";
    }
    
    public String prepareViewMusicPlaylist(){
        getMyPlaylist();
        playlist = (Playlist) this.play.getRowData();
        return "viewPlaylist";
    }
    
    public DataModel<Music> viewMusicPlaylist(){
        playlist = (Playlist) this.play.getRowData();
        if (playlist_ejb!= null) {
            DataModel model = (DataModel<Music>) new ListDataModel(playlist_ejb.showMusicPlaylist(playlist));
            play = model;
            return model;
        }
        return null;
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

    public DataModel<Playlist> getPlay() {
        return play;
    }

    public void setPlay(DataModel<Playlist> play) {
        this.play = play;
    }
    
}