/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.AppUser;
import entities.Playlist;
import java.io.File;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.inject.Inject;

/**
 *
 * @author Zueb LDA
 */
@Stateful
public class DeleteUser {

    @Inject
    PlaylistFacade playlists;
    @EJB
    AppUserFacade users;

    public void userRemove(AppUser user) {

        List<Playlist> p = playlists.findAll();

        for (int i = 0; i < p.size(); i++) {
            for (int j = 0; j < p.get(i).getMusics().size(); j++) {
                if (p.get(i).getMusics().get(j).getUser().equals(user)) {
                    File file = new File(p.get(i).getMusics().get(j).getMusic_path());
                    System.err.println("FILE PATH -->" + file.getPath());
                    p.get(i).getMusics().remove(p.get(i).getMusics().get(j));
                    System.err.println("FILE PATH2 -->" + file.getPath());
                    file.delete();
                }
            }
        }
        users.remove(user);
    }
}
