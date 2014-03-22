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
import java.io.File;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Elsa
 */
@Named("sessionMb")
@SessionScoped
public class SessionMb implements Serializable, Converter {

    private static final long serialVersionUID = 1L;
    @Inject
    private PlaylistFacade playlist_ejb;
    @Inject
    private MusicFacade music_ejb;
    @Inject
    private LoggedUserEjb loggedUser;

    private Music musicSelected;
    private Playlist selectedPlaylist = null;
    private List<Playlist> itemsPlays = null;
    private DataModel<Music> musics;
    private String messageErrorMusic;

    /**
     * Creates a new instance of PlaylistMB
     */
    public SessionMb() {

    }

    public void pickAction(ActionEvent ev) {

        selectedPlaylist = (Playlist) ev.getSource();
    }

    public List<Playlist> myPlaylists() {

        itemsPlays = playlist_ejb.showMyPlaylist(loggedUser.getUser());
        return itemsPlays;
    }

    public String prepareViewMusicPlaylist() {
        return "viewPlaylist";
    }
    /*
     public String addMusicToPlay() {
     try {
     playlist_ejb.addMusicToPlaylist(selectedPlaylist.getId(), musicSelected.getId());
     //playlist_ejb.edit(selectedPlaylist);
     messageErrorMusic = null;
     selectedPlaylist = null;
     return null;
     } catch (MusicsAlreadyExistInPlaylist m) {
     Logger.getLogger(SessionMb.class.getName()).log(Level.SEVERE, null, m);
     messageErrorMusic = m.getMessage();
     return null;
     }

     }
     */

    public String editPlaylist() {
        playlist_ejb.editPlaylist(selectedPlaylist, loggedUser.getUser());
        return "listMyPlaylist";
    }

    public PlaylistFacade getPlaylist_ejb() {
        return playlist_ejb;
    }

    public void setPlaylist_ejb(PlaylistFacade playlist_ejb) {
        this.playlist_ejb = playlist_ejb;
    }

    public Playlist getSelectedPlaylist() {
        return selectedPlaylist;
    }

    public void setSelectedPlaylist(Playlist selected) {
        this.selectedPlaylist = selected;
    }

    public MusicFacade getMusic_ejb() {
        return music_ejb;
    }

    public void setMusic_ejb(MusicFacade music_ejb) {
        this.music_ejb = music_ejb;
    }

    public LoggedUserEjb getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(LoggedUserEjb loggedUser) {
        this.loggedUser = loggedUser;
    }

    public Music getMusicSelected() {
        return musicSelected;
    }

    public void setMusicSelected(Music musicSelected) {
        this.musicSelected = musicSelected;
    }

    public List<Playlist> getItemsPlays() {
        return itemsPlays;
    }

    public void setItemsPlays(List<Playlist> itemsPlays) {
        this.itemsPlays = itemsPlays;
    }

    public DataModel<Music> getPlaylistMusics() {

        DataModel model = (DataModel<Music>) new ListDataModel(music_ejb.showMusicsPlaylist(selectedPlaylist));
        return model;

    }

    public DataModel<Music> getMusics() {
        return musics;
    }

    public void setMusics(DataModel<Music> musics) {
        this.musics = musics;
    }

    public String getMessageErrorMusic() {
        return messageErrorMusic;
    }

    public void setMessageErrorMusic(String messageErrorMusic) {
        this.messageErrorMusic = messageErrorMusic;
    }

    public String removeMusicPlaylist() {

        selectedPlaylist.getMusics().remove(musicSelected);
        playlist_ejb.edit(selectedPlaylist);
        return "viewPlaylist";
    }

    //Começa aqui a transferência desde o EditMusicMb
    public String destroy() {

        if (musicSelected.getUser().equals(loggedUser.getUser())) {

            File file = new File(musicSelected.getMusic_path());
            file.delete();
            music_ejb.remove(musicSelected);

            return "listAllMusics";
        }
        return "listAllMusics";

    }

    public String editMusic() {

        if (musicSelected.getUser().equals(loggedUser.getUser())) {
            music_ejb.edit(musicSelected);

            return "listAllMusics";
        } else {
            return "listAllMusics";
        }

    }

    //Acaba aqui a transferência desde o EditMusciMb
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        if (!value.matches("\\d+")) {
            throw new ConverterException("The value is not a valid playlist ID: " + value);
        }

        Integer id = Integer.parseInt(value);
        return playlist_ejb.find(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value == null) {
            return null;
        }

        if (!(value instanceof Playlist)) {
            throw new ConverterException("The value is not a Playlist: " + value);
        }

        String id = ((Playlist) value).getId().toString();
        return (id != null) ? id.toString() : null;
    }

}
