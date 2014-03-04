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
    private int music_year;
    private String music_path;
    private Integer user_id; 
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

    public int getYear() {
        return music_year;
    }

    public void setYear(int year) {
        this.music_year = year;
    }

    public String getPath() {
        return music_path;
    }

    public void setPath(String path) {
        this.music_path = path;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
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
