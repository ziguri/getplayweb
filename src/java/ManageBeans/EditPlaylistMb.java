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

import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Elsa
 */
@ManagedBean(name = "editPlaylistMb")
@SessionScoped
public class EditPlaylistMb {

    @EJB
    private PlaylistFacade playlist_ejb;

    @EJB
    private MusicFacade musics_ejb;

    @ManagedProperty(value = "#{logged}")
    private LoggedUserMb user;

    private DataModel<Playlist> play;
    private Playlist selected;

    /**
     * Creates a new instance of PlaylistMB
     */
    public EditPlaylistMb() {
        this.selected = new Playlist();
        this.play = null;
    }
    
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
            DataModel model = (DataModel<Music>) new ListDataModel(playlist_ejb.showMusicPlaylist(selected));
            return model;
        }
        return null;
    }

    public String editPlaylist() {
        playlist_ejb.editPlaylist(selected, user.getUser());
        return "listMyPlaylist";
    }

    public PlaylistFacade getPlaylist_ejb() {
        return playlist_ejb;
    }

    public void setPlaylist_ejb(PlaylistFacade playlist_ejb) {
        this.playlist_ejb = playlist_ejb;
    }

    public Playlist getSelected() {
        return selected;
    }

    public void setSelected(Playlist selected) {
        this.selected = selected;
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
