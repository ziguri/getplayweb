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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Zueb LDA
 */
@Entity
@Table(name = "appuser")
@NamedQueries({
    @NamedQuery(name = "appuser.findAll", query = "SELECT u FROM AppUser u"),
    @NamedQuery(name ="appuser.findByEmail", query = "SELECT u FROM AppUser u WHERE u.email = :email")
})
public class AppUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer user_id;
    
    //@NotNull
    @Pattern (regexp = "^[\\p{L} .'-]+$", message = "{invalid.name}")
    @Column (name = "NAME", nullable = false)
    private String name;
    
    //@NotNull
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="Invalid Email")
    @Column (name = "EMAIL", nullable = false)
    private String email;
    
    //@NotNull
    //@Pattern(regexp ="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,12})", message="Invalid Password. You need between 6-12 characteres, at least 1 lower case, 1 upper case and 1 numeric")
    @Column (name = "PASSWORD", nullable = false)
    private String password;
   
    
    @OneToMany(mappedBy = "user")
    private List<Music> musics;
    
    @OneToMany(mappedBy = "user")
    private List<Playlist> playlists;

    public AppUser() {
    }

    public AppUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer id) {
        this.user_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Music> getMusics() {
        return musics;
    }

    public void setMusics(List<Music> musics) {
        this.musics = musics;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (user_id != null ? user_id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the user_id fields are not set
        if (!(object instanceof AppUser)) {
            return false;
        }
        AppUser other = (AppUser) object;
        if ((this.user_id == null && other.user_id != null) || (this.user_id != null && !this.user_id.equals(other.user_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Registered_User[ id=" + user_id + " ]";
    }
    
}
