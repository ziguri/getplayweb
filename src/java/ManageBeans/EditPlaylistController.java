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
//    @ManagedProperty("#{flash}")
//    private Flash playlistFlash;
    private Music musicSelected;
    private Playlist playlistSelected;
    @Inject
    LoggedUserEjb loggedUser;

    /**
     * Creates a new instance of EditPlaylistController
     */
    public EditPlaylistController() {
    }

    @PostConstruct
    public void init() {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        setPlaylistSelected((Playlist) flash.get("play"));
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

}
