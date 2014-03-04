/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Zueb LDA
 */
@Entity
public class Playlist implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer playlist_id;
    private String playlist_name;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date creation_date;
    private Integer playlist_size;
    @ManyToMany
    private ArrayList<Music> musics;
    @ManyToOne
    private Registered_User user;

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

    public ArrayList<Music> getMusics() {
        return musics;
    }

    public void setMusics(ArrayList<Music> musics) {
        this.musics = musics;
    }

    public Registered_User getUser() {
        return user;
    }

    public void setUser(Registered_User user) {
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
