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
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.event.ActionEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Elsa
 */
@ManagedBean(name = "sessionMb")
@SessionScoped
public class SessionMb implements Serializable, Converter {

    private static final long serialVersionUID = 1L;
    @EJB
    private PlaylistFacade playlist_ejb;
    @EJB
    private MusicFacade music_ejb;
    @ManagedProperty(value = "#{logged}")
    private LoggedUserMb user;
    private Music musicSelected;
    private Playlist selectedPlaylist = null;
    private List<Playlist> itemsPlays = null;
    private DataModel<Music> musics;

    /**
     * Creates a new instance of PlaylistMB
     */
    public SessionMb() {

    }

    public void pickAction(ActionEvent ev) {

        selectedPlaylist = (Playlist) ev.getSource();
    }

    public List<Playlist> myPlaylists() {

        itemsPlays = playlist_ejb.showMyPlaylist(user.getUser());
        return itemsPlays;
    }

    public String prepareViewMusicPlaylist() {
        return "viewPlaylist";
    }

    public String addMusicToPlay() {

        selectedPlaylist.getMusics().add(musicSelected);
        playlist_ejb.edit(selectedPlaylist);
        selectedPlaylist = null;
        return "principal";
    }

    public String editPlaylist() {
        playlist_ejb.editPlaylist(selectedPlaylist, user.getUser());
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

    public LoggedUserMb getUser() {
        return user;
    }

    public void setUser(LoggedUserMb user) {
        this.user = user;
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

    public String removeMusicPlaylist() {

        selectedPlaylist.getMusics().remove(musicSelected);
        playlist_ejb.edit(selectedPlaylist);
        return "viewPlaylist";
    }

    //Começa aqui a transferência desde o EditMusicMb
    public String destroy() {

        if (musicSelected.getUser().equals(user.getUser())) {
            music_ejb.remove(musicSelected);

            return "listAllMusics";
        }
        return "listAllMusics";

    }

    public String editMusic() {

        if (musicSelected.getUser().equals(user.getUser())) {
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
