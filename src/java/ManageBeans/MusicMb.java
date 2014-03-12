/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManageBeans;

import ejbs.MusicFacade;
import ejbs.Uploader;
import entities.Music;
import java.io.File;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.mail.FetchProfile.Item;


/**
 *
 * @author Elsa
 */
@ManagedBean(name="musicMb")
@RequestScoped
public class MusicMb implements Serializable{
    @EJB
    private MusicFacade music_ejb;
    private DataModel<Music> items = null;
    private Music music;
    private String pathToSave;
    @ManagedProperty(value="#{logged}")
    private LoggedUserMb user;
    @EJB
    private Uploader uploader;
    private int selectedItemIndex;
    
    /**
     * Creates a new instance of MusicMb
     */
    public MusicMb() {
        this.music = new Music();
    }
    
    public String addMusic(){
        
        
        //pathToSave = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        //music.setMusic_path(pathToSave);
        //uploader.upload();
        music_ejb.addMusic(music, user.getUser());
        return "principal";
    }
    
    public List<Music> viewAllMusic(){
        return music_ejb.showAllMusics();
        
    }
    
    public DataModel<Music> getMusicList() {
        if (music_ejb != null) {
            DataModel model = (DataModel<Music>) new ListDataModel(music_ejb.findAll());
            return model;
        }
        return null;
    }
    
    public int countAllItens(){
        return music_ejb.count();
    }
    
     public Music getMusicSelected() {
        if (music == null) {
            music = new Music();
            selectedItemIndex = -1;
        }
        return music;
    }
     
     public String prepareEdit() {
        items = DataModel.getRowData();
         music = (Music) getItems().getRowData();
        selectedItemIndex = getItems().getRowIndex();
        music_ejb.editMusic(music, user.getUser());
        return "listAllMusic";
    }
     
    public String destroy() {
        music = (Music) getItems().getRowData();
        selectedItemIndex = getItems().getRowIndex();
        music_ejb.remove(music);
        recreateModel();
        getMusicList();
        return "listAllMusic";
    }
    private void recreateModel() {
        items = null;
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
            music.setUser(user.getUser());
        }
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public DataModel getItems() {
        return items;
    }

    public void setItems(DataModel items) {
        this.items = items;
    }

    public String getPathToSave() {
        return pathToSave;
    }

    public void setPathToSave(String pathToSave) {
        this.pathToSave = pathToSave;
    }

    public LoggedUserMb getUser() {
        return user;
    }

    public void setUser(LoggedUserMb user) {
        this.user = user;
    }

    public Uploader getUploader() {
        return uploader;
    }

    public void setUploader(Uploader uploader) {
        this.uploader = uploader;
    }

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
    }


}
