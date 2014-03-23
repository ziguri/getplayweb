/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import ejbs.MusicFacade;
import entities.Music;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

/**
 *
 * @author Elsa
 */
@Named("requestMusicMb")
@RequestScoped
public class RequestMusicMb implements Serializable {

    @Inject
    private MusicFacade music_ejb;
    private DataModel<Music> items = null;
    private Music music;
    private String pathToSave;
    @Inject
    private LoggedUserMb user;
    private int selectedItemIndex;
    private DataModel<Music> musics;
    private String musicPath;
    private Part file1;
    private String message;

    /**
     * Creates a new instance of MusicMb
     */
    public RequestMusicMb() {

    }

    @PostConstruct
    public void init() {
        this.music = new Music();
        this.musics = null;
        this.message = null;
    }

    public void addMusic() throws FileNotFoundException, IOException {
        try {

            File file = new File("C:\\APPGetPlayWeb\\");
            file.mkdir();
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

            message = "Music " + music.getTitle() + " created with success.";

        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
            message = "File not found. Please try again";

        } catch (IOException ex2) {
            System.err.println(ex2.getMessage());
        }
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

    public DataModel<Music> getMyMusicList() {

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
        //music = (Music) this.musics.getRowData();
        if (music.getUser().equals(user.getUser())) {
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

    public DataModel<Music> getMusics() {
        return musics;
    }

    public void setMusics(DataModel<Music> musics) {
        this.musics = musics;
    }

    public String getMusicPath() {
        return musicPath;
    }

    public void setMusicPath(String musicPath) {
        this.musicPath = musicPath;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
