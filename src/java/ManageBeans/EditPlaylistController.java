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
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Zueb LDA
 */
@Named("editPlaylistController")
@ViewScoped
public class EditPlaylistController {

    @Inject
    private PlaylistFacade playlistEjb;
    @Inject
    private MusicFacade musicEjb;
    private Music musicSelected;
    private Playlist playlistSelected;
    @Inject
    LoggedUserEjb loggedUser;
    private DataModel<Playlist> play;

    /**
     * Creates a new instance of EditPlaylistController
     */
    public EditPlaylistController() {
    }

    @PostConstruct
    public void init() {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        setPlaylistSelected((Playlist) flash.get("play"));
        this.play = (DataModel<Playlist>) new ListDataModel(playlistEjb.showMyPlaylist(loggedUser.getUser()));
    }

    public String removeMusicPlaylist() {
        playlistEjb.removeMusicFromPlaylist(playlistSelected, musicSelected);
        return null;
    }

    public String saveSelectedPlaylist() {

        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("play", playlistSelected);

        return "viewPlaylist";

    }

    public DataModel<Music> getPlaylistMusics() {

        DataModel model = (DataModel<Music>) new ListDataModel(musicEjb.showMusicsPlaylist(playlistSelected));
        return model;

    }

    public String editPlaylist() {
        playlistEjb.editPlaylist(playlistSelected, loggedUser.getUser());
        return "listMyPlaylist";
    }

    public String sortTable(String column, String order) {

        DataModel model = (DataModel<Playlist>) new ListDataModel(playlistEjb.orderPlaylist(column, order, loggedUser.getUser()));
        play = model;
        return null;

    }

    public DataModel<Playlist> getMyPlaylist() {
        if (playlistEjb != null) {
            DataModel model = (DataModel<Playlist>) new ListDataModel(playlistEjb.showMyPlaylist(loggedUser.getUser()));
            play = model;
            return model;
        }
        return null;
    }

    public String destroy() {
        Playlist playlist = (Playlist) this.play.getRowData();
        playlistEjb.remove(playlist);
        getMyPlaylist();
        return null;
    }

    //Getter and Setter
    public PlaylistFacade getPlaylistEjb() {
        return playlistEjb;
    }

    public void setPlaylistEjb(PlaylistFacade playlistEjb) {
        this.playlistEjb = playlistEjb;
    }

    public Music getMusicSelected() {
        return musicSelected;
    }

    public void setMusicSelected(Music musicSelected) {
        this.musicSelected = musicSelected;
    }

    public Playlist getPlaylistSelected() {
        return playlistSelected;
    }

    public void setPlaylistSelected(Playlist playlistSelected) {
        this.playlistSelected = playlistSelected;
    }

    public MusicFacade getMusicEjb() {
        return musicEjb;
    }

    public void setMusicEjb(MusicFacade musicEjb) {
        this.musicEjb = musicEjb;
    }

    public LoggedUserEjb getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(LoggedUserEjb loggedUser) {
        this.loggedUser = loggedUser;
    }

    public DataModel<Playlist> getPlay() {
        return play;
    }

    public void setPlay(DataModel<Playlist> play) {
        this.play = play;
    }

}
