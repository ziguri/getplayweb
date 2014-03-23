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
 * @author Elsa Santos
 * @author Orlando Neves
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
    /**
     * Return DataModel<Music>
     *
     * @return
     */
    public DataModel<Music> getModel() {
        return model;
    }

    /**
     * Receives DataModel<Music> and set current DataModel<Music>
     *
     * @param model
     */
    public void setModel(DataModel<Music> model) {
        this.model = model;
    }

    /**
     * Return Music musicSelected
     *
     * @return
     */
    public Music getMusicSelected() {
        return musicSelected;
    }

    /**
     * Receives Music Object and set current musicSelected
     *
     * @param musicSelected
     */
    public void setMusicSelected(Music musicSelected) {
        this.musicSelected = musicSelected;
    }

    /**
     * Return Playlist playlistSelected
     *
     * @return
     */
    public Playlist getPlaylistSelected() {
        return playlistSelected;
    }

    /**
     * Receives Playlist Object and set current playlistSelected
     *
     * @param playlistSelected
     */
    public void setPlaylistSelected(Playlist playlistSelected) {
        this.playlistSelected = playlistSelected;
    }

    /**
     * Return String word
     *
     * @return
     */
    public String getWord() {
        return word;
    }

    /**
     * Receives String Object and set current word
     *
     * @param word
     */
    public void setWord(String word) {
        this.word = word;
    }

}
