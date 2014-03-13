/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManageBeans;

import ejbs.MusicFacade;
import entities.Music;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Zueb LDA
 */
@ManagedBean
@SessionScoped
public class EditMusicMb {

    @EJB
    private MusicFacade music_ejb;
    @ManagedProperty(value = "#{logged}")
    private LoggedUserMb user;
    private DataModel <Music> musics;
    private Music music;
    /**
     * Creates a new instance of EditMusicMb
     */
    public EditMusicMb() {
        this.music = new Music();
        musics=null;
    }

    public String prepareEdit() {
        
        musics = (DataModel<Music>) new ListDataModel(music_ejb.findAll());
        music = (Music) this.musics.getRowData();
        
        if(music.getUser().equals(user.getUser())){
            music_ejb.editMusic(music, user.getUser());
            return "editMusic";
        }
        else{
        
            return "listAllMusics";
        }
    }
    
    

    public MusicFacade getMusic_ejb() {
        return music_ejb;
    }

    public void setMusic_ejb(MusicFacade music_ejb) {
        this.music_ejb = music_ejb;
    }

    public LoggedUserMb getUser() {
        return user;
    }

    public void setUser(LoggedUserMb user) {
        this.user = user;
    }

    public DataModel<Music> getMusics() {
        return musics;
    }

    public void setMusics(DataModel<Music> musics) {
        this.musics = musics;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
    
    
    
}
