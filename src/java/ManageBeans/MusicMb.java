/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManageBeans;

import ejbs.MusicFacade;
import entities.Music;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Elsa
 */
@ManagedBean(name="musicMb")
@RequestScoped
public class MusicMb implements Serializable{
    @EJB
    private MusicFacade music_ejb;
    private Music music;
    private LoggedUser user;
    
    /**
     * Creates a new instance of MusicMb
     */
    public MusicMb() {
    }
    
    public String addMusic(){
//        music.setUser(user.getUser());
        music_ejb.addMusic(music);
        return "principal";
    }
    
    public MusicFacade getMusic_ejb() {
        return music_ejb;
    }

    public void setMusic_ejb(MusicFacade music_ejb) {
        this.music_ejb = music_ejb;
    }

    public Music getMusic() {
        if (music==null){
            music= new Music();
        }
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
    
}
