/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistence;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "REGISTERED_USER")
public class Registered_User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer user_id;
    
    @NotNull
    @Pattern (regexp = "^[\\p{L} .'-]+$", message = "{invalid.name}")
    @Column (name = "NAME", nullable = false, length = 40)
    private String name;
    
    @NotNull
    @Pattern(regexp="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message="{invalid.email}")
    @Column (name = "EMAIL", nullable = false, length = 50)
    private String email;
    
    @NotNull
    @Size(min=6, max=12, message = "Minimum ")
    @Pattern(regexp ="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,12})", message="Invalid Password. You need between 6-12 characteres, at least 1 lower case, 1 upper case and 1 numeric")
    @Column (name = "PASSWORD", nullable = false, length = 12)
    private String password;
    
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column (name = "REGISTER_DATE", nullable = false)
    private Date register_date;
    
    @OneToOne(mappedBy = "user")
    private Music music;

    public Registered_User() {
    }

    public Registered_User(String name, String email, String password) {
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

    public Date getRegister_date() {
        return register_date;
    }

    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
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
        if (!(object instanceof Registered_User)) {
            return false;
        }
        Registered_User other = (Registered_User) object;
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
