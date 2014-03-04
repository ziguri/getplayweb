/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistence;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author Zueb LDA
 */
@Entity
public class Music implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer music_id;
    private String title;
    private String artist;
    private String album;
    private Integer music_year;
    private String music_path;
    @ManyToOne
    private Registered_User user;
    @ManyToMany(mappedBy = "musics")
    private List<Playlist> playlists_has_musics;

    public Music() {
    }
    

    public Integer getId() {
        return music_id;
    }

    public void setId(Integer id) {
        this.music_id = id;
    }

    public Integer getMusic_id() {
        return music_id;
    }

    public void setMusic_id(Integer music_id) {
        this.music_id = music_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public Integer getMusic_year() {
        return music_year;
    }

    public void setMusic_year(Integer music_year) {
        this.music_year = music_year;
    }

    public String getMusic_path() {
        return music_path;
    }

    public void setMusic_path(String music_path) {
        this.music_path = music_path;
    }

    public Registered_User getUser() {
        return user;
    }

    public void setUser(Registered_User user) {
        this.user = user;
    }

    public List<Playlist> getPlaylists_has_musics() {
        return playlists_has_musics;
    }

    public void setPlaylists_has_musics(List<Playlist> playlists_has_musics) {
        this.playlists_has_musics = playlists_has_musics;
    }

   
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (music_id != null ? music_id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the music_id fields are not set
        if (!(object instanceof Music)) {
            return false;
        }
        Music other = (Music) object;
        if ((this.music_id == null && other.music_id != null) || (this.music_id != null && !this.music_id.equals(other.music_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Music[ id=" + music_id + " ]";
    }
    
}
