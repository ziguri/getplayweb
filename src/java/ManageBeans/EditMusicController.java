/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import ejbs.MusicFacade;
import entities.Music;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Zueb LDA
 */
@Named("editMusicController")
@ViewScoped
public class EditMusicController {

    private Music musicSelected;
    @Inject
    private MusicFacade musicEjb;
    @Inject
    private LoggedUserEjb loggedUser;

    /**
     * Creates a new instance of EditMusicController
     */
    public EditMusicController() {
    }

    @PostConstruct
    public void init() {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        setMusicSelected((Music) flash.get("music"));
    }

    public String editMusic() {

        if (musicSelected.getUser().equals(loggedUser.getUser())) {
            musicEjb.edit(musicSelected);

            return "listMyMusics";
        } else {
            return "listMyMusics";
        }
    }

    //Getter and Setter
    public Music getMusicSelected() {
        return musicSelected;
    }

    public void setMusicSelected(Music musicSelected) {
        this.musicSelected = musicSelected;
    }

}
