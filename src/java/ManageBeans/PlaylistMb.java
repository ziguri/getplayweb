/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManageBeans;

import ejbs.PlaylistFacade;
import entities.Playlist;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
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
    private Playlist playlist;

    /**
     * Creates a new instance of PlaylistMB
     */
    public PlaylistMb() {
    }

    public String addPlaylist(){
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
    
}
