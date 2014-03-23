/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import Exceptions.SearchNullException;
import ejbs.MusicFacade;
import entities.Music;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Elsa
 */
@Named("searchMb")
@RequestScoped
public class SearchMb implements Serializable {

    @Inject
    private MusicFacade musics_ejb;
    private String word;
    private String option;
    private DataModel<Music> musics;
    private FacesMessage message;

    private DataModel<Music> model;

    private String errorMessage;

    /**
     * Creates a new instance of SearchMb
     */
    public SearchMb() {
    }

    @PostConstruct
    public void init() {
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

    public FacesMessage getMessage() {
        return message;
    }

    public void setMessage(FacesMessage message) {
        this.message = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /*
     public DataModel<Music> resultSearch() {
     DataModel model;
     try {
     List<Music> results = musics_ejb.searchByColumn(option, word);
     model = (DataModel<Music>) new ListDataModel(results);
     return model;
     } catch (SearchNullException ex) {
     Logger.getLogger(SearchMb.class.getName()).log(Level.SEVERE, null, ex);
     errorMessage = ex.getMessage();
     return null;
     }
     }
     */
    public String search() {

        try {
            List<Music> results = musics_ejb.searchByColumn(option, word);
            this.model = (DataModel<Music>) new ListDataModel(results);
            Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
            flash.put("model", model);
            flash.put("word", word);

        } catch (SearchNullException ex) {
            Logger.getLogger(SearchMb.class.getName()).log(Level.SEVERE, null, ex);
            errorMessage = ex.getMessage();
            return null;
        }

        return "search";
    }

    public DataModel<Music> getModel() {
        return model;
    }

    public void setModel(DataModel<Music> model) {
        this.model = model;
    }
}
