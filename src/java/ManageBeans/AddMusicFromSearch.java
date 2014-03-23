/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import entities.Music;
import entities.Playlist;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.model.DataModel;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Zueb LDA
 */
@Named("addMusicFromSearch")
@ViewScoped
public class AddMusicFromSearch {

    private DataModel<Music> model;
    private Music musicSelected;
    private Playlist playlistSelected;
    private String word;

    /**
     * Creates a new instance of AddMusicFromSearch
     */
    public AddMusicFromSearch() {
    }

    @PostConstruct
    public void init() {
        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        setModel((DataModel<Music>) flash.get("model"));
        setWord((String) flash.get("word"));
    }

    //Getter and Setter
    public DataModel<Music> getModel() {
        return model;
    }

    public void setModel(DataModel<Music> model) {
        this.model = model;
    }

    public Music getMusicSelected() {
        return musicSelected;
    }

    public void setMusicSelected(Music musicSelected) {
        this.musicSelected = musicSelected;
    }

    public Playlist getPlaylistSelected() {
        return playlistSelected;
    }

    public void setPlaylistSelected(Playlist playlistSelected) {
        this.playlistSelected = playlistSelected;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

}
