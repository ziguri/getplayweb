/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Zueb LDA
 */
@Entity
@Table(name = "PLAYLIST")
@NamedQueries({
    @NamedQuery(name = "Playlist.findAll", query = "SELECT p FROM Playlist p"),
    @NamedQuery(name = "Playlist.findPlaylistById", query = "SELECT p FROM Playlist p WHERE p.playlist_id = :playlist_id"),
    @NamedQuery(name = "Playlist.findPlaylistByName", query = "SELECT p FROM Playlist p WHERE p.playlist_name = :playlist_name"),
    @NamedQuery(name = "Playlist.findPlaylistByCreation", query = "SELECT p FROM Playlist p WHERE p.creation_date = :creation_date"),
    @NamedQuery(name = "Playlist.findPlaylistByUser", query = "SELECT p FROM Playlist p WHERE p.user = :user"),
    @NamedQuery(name = "Playlist.findPlaylistBySize", query = "SELECT p FROM Playlist p WHERE p.playlist_size = :playlists"),
})
public class Playlist implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer playlist_id;
    @NotNull
    @Size(max=40)
    @Column(name = "NAME")
    private String playlist_name;
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "CREATION_DATE")
    private Date creation_date;
    private Integer playlist_size;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="playlists_has_musics")
    private List<Music> musics;
    @NotNull
    @ManyToOne
    private AppUser user;

    public Playlist() {
    }

    public Integer getId() {
        return playlist_id;
    }

    public void setId(Integer id) {
        this.playlist_id = id;
    }

    public Integer getPlaylist_id() {
        return playlist_id;
    }

    public void setPlaylist_id(Integer playlist_id) {
        this.playlist_id = playlist_id;
    }

    public String getPlaylist_name() {
        return playlist_name;
    }

    public void setPlaylist_name(String playlist_name) {
        this.playlist_name = playlist_name;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public Integer getPlaylist_size() {
        return playlist_size;
    }

    public void setPlaylist_size(Integer playlist_size) {
        this.playlist_size = playlist_size;
    }

    public List<Music> getMusics() {
        return musics;
    }

    public void setMusics(ArrayList<Music> musics) {
        this.musics = musics;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (playlist_id != null ? playlist_id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the playlist_id fields are not set
        if (!(object instanceof Playlist)) {
            return false;
        }
        Playlist other = (Playlist) object;
        if ((this.playlist_id == null && other.playlist_id != null) || (this.playlist_id != null && !this.playlist_id.equals(other.playlist_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Playlist[ id=" + playlist_id + " ]";
    }
    
}
