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
@ManagedBean(name = "editPlaylistMb")
@SessionScoped
public class EditPlaylistMb implements Serializable, Converter {

    private static final long serialVersionUID = 1L;
    @EJB
    private PlaylistFacade playlist_ejb;
    @EJB
    private MusicFacade musics_ejb;
    @ManagedProperty(value = "#{logged}")
    private LoggedUserMb user;
    private Music musicSelected;

//    private DataModel<Playlist> play;
    private Playlist selectedPlaylist=null;
    private List<Playlist> itemsPlays = null;

    /**
     * Creates a new instance of PlaylistMB
     */
    public EditPlaylistMb() {

    }
    /*
    public Playlist[] myPlaylists(){
    
        DataModel model = (DataModel<Playlist>) new ListDataModel(playlist_ejb.showMyPlaylist(user.getUser()));
        int size = model.getRowCount();
        
        Playlist[] playlists = new Playlist [size];
        
        for(int i=0; i<size; i++){
        
            model.setRowIndex(i);
            playlists [i] = (Playlist) model.getRowData();
        }
        
        return playlists;
    }
    */
    
    public void pickAction(ActionEvent ev){
    
        selectedPlaylist = (Playlist) ev.getSource();
    }
    
    public List<Playlist> myPlaylists(){
 
        //itemsPlays= playlist_ejb.showMyPlaylist(user.getUser());
        itemsPlays= user.getUser().getPlaylists();
        return itemsPlays;
    }
    
    /*
    public List<Playlist> getUserPlaylists() {
        play = user.getUser().getPlaylists();
        return play;
    }
*/
    public String prepareEdit() {

        //selected = this.play.getRowData();
        return "editPlaylist";
    }
    
    public String prepareViewMusicPlaylist(){
        //getMyPlaylist();
        //playlist = (Playlist) this.play.getRowData();
        return "viewPlaylist";
    }
    
    public DataModel<Music> viewMusicPlaylist(){
        //playlist = (Playlist) this.play.getRowData();
        if (playlist_ejb!= null) {
            DataModel model = (DataModel<Music>) new ListDataModel(playlist_ejb.showMusicPlaylist(selectedPlaylist));
            return model;
        }
        return null;
    }
    
    public String addMusicToPlay() {
        
        selectedPlaylist.getMusics().add(musicSelected);
        selectedPlaylist.setPlaylist_size(selectedPlaylist.getPlaylist_size()+1);
        playlist_ejb.edit(selectedPlaylist);
        selectedPlaylist=null;
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

//    public DataModel<Playlist> getPlay() {
//        return play;
//    }
//
//    public void setPlay(DataModel<Playlist> play) {
//        this.play = play;
//    }

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