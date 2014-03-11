/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import ManageBeans.MusicMb;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

/**
 *
 * @author Zueb LDA
 */
@Stateless
public class Uploader {

    private Part file;
    private String fileContent;
    @EJB
    private MusicFacade musicFacade;
    @ManagedProperty(value="#{musicMb}")
    
    private MusicMb musicManager;

    public void upload() {
        try {
            fileContent = new Scanner(file.getInputStream()).useDelimiter("\\A").next();

            String filePath = "C:\\APPGetPlayWeb\\" + file.getName();
            musicManager.getMusic().setMusic_path(filePath);
            musicFacade.addMusic(musicManager.getMusic());
            
        } catch (IOException e) {
            // Error handling
        }
    }

    public void validateFile(FacesContext ctx,
            UIComponent comp,
            Object value) {
        List<FacesMessage> msgs = new ArrayList<>();
        file = (Part) value;
        
        if (file.getSize() > 1024) {
            msgs.add(new FacesMessage("file too big"));
        }
        if (!"text/plain".equals(file.getContentType())) {
            msgs.add(new FacesMessage("not a text file"));
        }
        if (!msgs.isEmpty()) {
            throw new ValidatorException(msgs);
        }
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public MusicFacade getMusicFacade() {
        return musicFacade;
    }

    public void setMusicFacade(MusicFacade musicFacade) {
        this.musicFacade = musicFacade;
    }

    public MusicMb getMusicManager() {
        return musicManager;
    }

    public void setMusicManager(MusicMb musicManager) {
        this.musicManager = musicManager;
    }
    
    

}
