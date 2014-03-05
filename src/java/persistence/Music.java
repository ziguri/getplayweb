/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

/**
 *
 * @author Zueb LDA
 */
@Entity
@Table(name = "MUSIC")
public class Music implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer music_id;
    
    @NotNull
    @Size(max=40, message = "Max size 40char")
    @Column(nullable =false,length =40, name = "TITLE")
    private String title;
    
    @NotNull
    @Size(max=40, message = "Max size 40char")
    @Column(nullable =false,length =40, name = "ARTIST")
    private String artist;
    
    @NotNull
    @Size(max=40, message = "Max size 40char")
    @Column(nullable =false, length =40, name = "ALBUM")
    private String album;
    
    @NotNull 
    @Column(nullable =false, name = "MUSIC YEAR")
    private Integer music_year;
    
    @NotNull
    @Column(nullable =false, name = "MUSIC PATH")
    private String music_path;
    
    @ManyToOne
    private Registered_User user;
    
    @ManyToMany(mappedBy = "musics")
    private List<Playlist> playlists_has_musics;

    public Music() {
    }

    public Music(String title, String artist, String album, Integer music_year, String music_path, Registered_User user, List<Playlist> playlists_has_musics) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.music_year = music_year;
        this.music_path = music_path;
        this.user = user;
        this.playlists_has_musics = playlists_has_musics;
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
