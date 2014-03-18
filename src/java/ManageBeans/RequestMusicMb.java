/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import ejbs.MusicFacade;
import entities.Music;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.Part;

/**
 *
 * @author Elsa
 */
@ManagedBean(name = "requestMusicMb")
@RequestScoped
public class RequestMusicMb implements Serializable {

    @EJB
    private MusicFacade music_ejb;
    private DataModel<Music> items = null;
    private Music music;
    private String pathToSave;
    @ManagedProperty(value = "#{logged}")
    private LoggedUserMb user;
    private int selectedItemIndex;
    private DataModel <Music> musics;
    private String musicPath;
    private Part file1;

    /**
     * Creates a new instance of MusicMb
     */
    public RequestMusicMb() {
        this.music = new Music();
        musics=null;
    }

    public String addMusic() throws IOException {

        musicPath = "C:\\APPGetPlayWeb\\" + file1.getSubmittedFileName();
        InputStream inputStream = file1.getInputStream();
        FileOutputStream outputStream = new FileOutputStream(musicPath);

        byte[] buffer = new byte[4096];
        int bytesRead = 0;
        while (true) {
            bytesRead = inputStream.read(buffer);
            if (bytesRead > 0) {
                outputStream.write(buffer, 0, bytesRead);
            } else {
                break;
            }
        }
        outputStream.close();
        inputStream.close();

        music_ejb.addMusic(music, user.getUser(), musicPath);
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.FACES_MESSAGES, "Music inserted successfully"));

        return "principal";

    }

    public List<Music> viewAllMusic() {
        return music_ejb.showAllMusics();

    }

    public DataModel<Music> getMusicList() {
        if (music_ejb != null) {
            DataModel model = (DataModel<Music>) new ListDataModel(music_ejb.findAll());
            musics = model;
            return model;
        }
        return null;
    }
    
    public DataModel<Music> getMyMusicList(){
    
        DataModel model = (DataModel<Music>) new ListDataModel(music_ejb.showUserMusics(user.getUser()));
        return model;
    }

    public int countAllItens() {
        return music_ejb.count();
    }

    public Music getMusicSelected() {
        if (music == null) {
            music = new Music();
            selectedItemIndex = -1;
        }
        return music;
    }

    public String destroy() {
        music = (Music) this.musics.getRowData();
         if(music.getUser().equals(user.getUser())){
            music_ejb.remove(music);
            //recreateModel();
            getMusicList();
            return "listAllMusics";
        }
        return "listAllMusics";
        
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
        if (music == null) {
            music = new Music();
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

    public int getSelectedItemIndex() {
        return selectedItemIndex;
    }

    public void setSelectedItemIndex(int selectedItemIndex) {
        this.selectedItemIndex = selectedItemIndex;
    }

    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }

}
