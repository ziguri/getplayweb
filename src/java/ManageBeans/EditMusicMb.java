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
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;

/**
 *
 * @author Zueb LDA
 */
@ManagedBean(name = "editMusicMb")
@SessionScoped
public class EditMusicMb implements Serializable {

    @EJB
    private MusicFacade music_ejb;
    @ManagedProperty(value = "#{logged}")
    private LoggedUserMb user;
    private DataModel<Music> musics;
    private Music selected;

    /**
     * Creates a new instance of EditMusicMb
     */
    public EditMusicMb() {
        musics = null;
    }

    public String prepareEdit() {

        return "editMusic";

    }

    public String editMusic() {

        if (selected.getUser().equals(user.getUser())) {
            music_ejb.edit(selected);

            return "listAllMusics";
        } else {
            return "listAllMusics";
        }

    }

    /*
     private void endSession(){
    
     FacesContext context = FacesContext.getCurrentInstance();
     HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
     session.invalidate();
     }*/
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

    public Music getSelected() {
        return selected;
    }

    public void setSelected(Music selected) {
        this.selected = selected;
    }

    public String destroy() {

        if (selected.getUser().equals(user.getUser())) {
            music_ejb.remove(selected);

            return "listAllMusics";
        }
        return "listAllMusics";

    }

}
