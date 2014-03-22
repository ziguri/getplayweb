/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import ejbs.MusicFacade;
import ejbs.PlaylistFacade;
import entities.Playlist;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Elsa
 */
@Named("requestPlaylistMb")
@RequestScoped
public class RequestPlaylistMb {

    @Inject
    private PlaylistFacade playlist_ejb;

    @Inject
    private MusicFacade musics_ejb;

    @Inject
    private LoggedUserEjb user;

    private DataModel<Playlist> play;
    private Playlist playlist;
    private String message;

    /**
     * Creates a new instance of PlaylistMB
     */
    public RequestPlaylistMb() {
    }

    @PostConstruct
    public void init() {
        this.playlist = new Playlist();
        this.play = (DataModel<Playlist>) new ListDataModel(playlist_ejb.showMyPlaylist(user.getUser()));
        this.message = null;
    }

    public void addPlaylist() {
        if (!this.playlist.getName().equals("")){
            playlist_ejb.addPlaylist(playlist, user.getUser());
            this.message = "Playlist " + playlist.getName() + " create with success.";
            
        }
        else {
            this.message = "Invalid Name. Please insert a name for your playlist.";
            
        }
    }

    public DataModel<Playlist> getMyPlaylist() {
        if (playlist_ejb != null) {
            DataModel model = (DataModel<Playlist>) new ListDataModel(playlist_ejb.showMyPlaylist(user.getUser()));
            play = model;
            return model;
        }
        return null;
    }

    public String sortTable(String column, String order) {

        DataModel model = (DataModel<Playlist>) new ListDataModel(playlist_ejb.orderPlaylist(column, order, user.getUser()));
        play = model;
        return null;

    }

    public String destroy() {
        playlist = (Playlist) this.play.getRowData();
        playlist_ejb.remove(playlist);
        getMyPlaylist();
        return "listMyPlaylist";
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

    public LoggedUserEjb getUser() {
        return user;
    }

    public void setUser(LoggedUserEjb user) {
        this.user = user;
    }

    public DataModel<Playlist> getPlay() {
        return play;
    }

    public void setPlay(DataModel<Playlist> play) {
        this.play = play;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
