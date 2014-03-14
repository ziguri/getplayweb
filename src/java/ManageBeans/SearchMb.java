/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import ejbs.MusicFacade;
import entities.Music;
import entities.Playlist;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Elsa
 */
@ManagedBean(name = "searchMb")
@SessionScoped
public class SearchMb {
    
    @EJB
    private MusicFacade musics_ejb;
    private String word;
    private String option;
    private DataModel<Music> musics;
    private FacesMessage message;

    /**
     * Creates a new instance of SearchMb
     */
    public SearchMb() {
        this.musics = null;
        this.message = new FacesMessage();
    }
    
    public MusicFacade getMusics_ejb() {
        return musics_ejb;
    }
    
    public void setMusics_ejb(MusicFacade musics_ejb) {
        this.musics_ejb = musics_ejb;
    }
    
    public String getWord() {
        return word;
    }
    
    public void setWord(String word) {
        this.word = word;
    }
    
    public DataModel<Music> getMusics() {
        return musics;
    }
    
    public void setMusics(DataModel<Music> musics) {
        this.musics = musics;
    }
    
    public String getOption() {
        return option;
    }
    
    public void setOption(String opcion) {
        this.option = opcion;
    }
    
    public DataModel<Music> resultSearch() {
        DataModel model = (DataModel<Music>) new ListDataModel(musics_ejb.searchByColumn(option, word));
        return model;
    }
    
}
