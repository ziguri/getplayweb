/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManageBeans;

import Exceptions.MusicsAlreadyExistInPlaylistException;
import ejbs.MusicFacade;
import ejbs.PlaylistFacade;
import entities.Music;
import entities.Playlist;
import java.io.File;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Zueb LDA
 */
@Named
@RequestScoped
public class GeneralController implements Converter {

    @Inject
    private PlaylistFacade playlistEjb;
    @Inject
    private LoggedUserEjb loggedUser;
    @Inject
    private MusicFacade musicEjb;
    private Playlist playlistSelected;
    private Music musicSelected;
    private Integer musicIdSelected;
    private Integer playlistIdSelected;
    private List<Playlist> itemsPlays = null;
    private DataModel<Music> musics;
    private String messageErrorMusic;

    public GeneralController() {
    }

    public String saveMusic() throws MusicsAlreadyExistInPlaylistException {
        try {
            playlistEjb.addMusicToPlaylist(playlistSelected, musicSelected);
            return null;
        } catch (MusicsAlreadyExistInPlaylistException e) {
            return "MessageDuplicatedMusic";
        }
    }

    //Acaba aqui a transferência desde o EditMusciMb
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        if (!value.matches("\\d+")) {
            throw new ConverterException("The value is not a valid playlist ID: " + value);
        }

        Integer id = Integer.parseInt(value);
        return playlistEjb.find(id);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        if (value == null) {
            return null;
        }

        if (!(value instanceof Playlist)) {
            throw new ConverterException("The value is not a Playlist: " + value);
        }

        String id = ((Playlist) value).getId().toString();
        return (id != null) ? id.toString() : null;
    }

    public List<Playlist> myPlaylists() {

        itemsPlays = playlistEjb.showMyPlaylist(loggedUser.getUser());
        return itemsPlays;
    }

    public DataModel<Music> getMusicList() {
        if (musicEjb != null) {
            DataModel model = (DataModel<Music>) new ListDataModel(musicEjb.findAll());
            musics = model;
            return model;
        }
        return null;
    }

    public String destroy() {

        if (musicSelected.getUser().equals(loggedUser.getUser())) {

            File file = new File(musicSelected.getMusic_path());
            file.delete();
            musicEjb.removeMusic(musicSelected);
            //musicEjb.remove(musicSelected);

            return "listMyMusics";
        }
        return "listMyMusics";

    }

    public String removeMusicPlaylist() {
        playlistEjb.removeMusicFromPlaylist(playlistSelected, musicSelected);
        return "viewPlaylist";
    }

    public DataModel<Music> getPlaylistMusics() {

        DataModel model = (DataModel<Music>) new ListDataModel(musicEjb.showMusicsPlaylist(playlistSelected));
        return model;

    }

    public String addMusicToSelectedPlaylist() {

        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("play", playlistSelected);

        return "viewPlaylist";

    }

    public String editSelectedPlaylist() {

        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("play", playlistSelected);

        return "editPlaylist";

    }

    public String editSelectedMusic() {

        Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
        flash.put("music", musicSelected);

        return "editMusic";

    }

    //Getter and Setter
    public PlaylistFacade getPlaylistEjb() {
        return playlistEjb;
    }

    public void setPlaylistEjb(PlaylistFacade playlistEjb) {
        this.playlistEjb = playlistEjb;
    }

    public LoggedUserEjb getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(LoggedUserEjb loggedUser) {
        this.loggedUser = loggedUser;
    }

    public MusicFacade getMusicEjb() {
        return musicEjb;
    }

    public void setMusicEjb(MusicFacade musicEjb) {
        this.musicEjb = musicEjb;
    }

    public Playlist getPlaylistSelected() {
        return playlistSelected;
    }

    public void setPlaylistSelected(Playlist playlistSelected) {
        this.playlistSelected = playlistSelected;
    }

    public List<Playlist> getItemsPlays() {
        return itemsPlays;
    }

    public void setItemsPlays(List<Playlist> itemsPlays) {
        this.itemsPlays = itemsPlays;
    }

    public DataModel<Music> getMusics() {
        return musics;
    }

    public void setMusics(DataModel<Music> musics) {
        this.musics = musics;
    }

    public Music getMusicSelected() {
        return musicSelected;
    }

    public void setMusicSelected(Music musicSelected) {
        this.musicSelected = musicSelected;
    }

    public String getMessageErrorMusic() {
        return messageErrorMusic;
    }

    public void setMessageErrorMusic(String messageErrorMusic) {
        this.messageErrorMusic = messageErrorMusic;
    }

    public Integer getMusicIdSelected() {
        return musicIdSelected;
    }

    public void setMusicIdSelected(Integer musicIdSelected) {
        this.musicIdSelected = musicIdSelected;
    }

    public Integer getPlaylistIdSelected() {
        return playlistIdSelected;
    }

    public void setPlaylistIdSelected(Integer playlistIdSelected) {
        this.playlistIdSelected = playlistIdSelected;
    }

}
