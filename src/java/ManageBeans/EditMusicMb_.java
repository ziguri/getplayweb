/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import ejbs.MusicFacade;
import ejbs.PlaylistFacade;
import entities.Music;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.inject.Inject;

/**
 *
 * @author Zueb LDA
 */
@ManagedBean(name = "editMusicMb")
@SessionScoped
public class EditMusicMb_ implements Serializable {

    @Inject
    private MusicFacade music_ejb;
    @Inject
    private PlaylistFacade playlist_ejb;
    @Inject
    private LoggedUserEjb user;
    private DataModel<Music> musics;
    private Music musicSelected;

    /**
     * Creates a new instance of EditMusicMb
     */
    public EditMusicMb_() {
        musics = null;
    }

    public String editMusic() {

        if (musicSelected.getUser().equals(user.getUser())) {
            music_ejb.edit(musicSelected);

            return "listAllMusics";
        } else {
            return "listAllMusics";
        }

    }

    public String destroy() {

        if (musicSelected.getUser().equals(user.getUser())) {
            music_ejb.remove(musicSelected);

            return "listAllMusics";
        }
        return "listAllMusics";

    }

}
