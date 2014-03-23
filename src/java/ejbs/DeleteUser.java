/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.AppUser;
import entities.Playlist;
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
                    p.get(i).getMusics().remove(p.get(i).getMusics().get(j));
                }
            }
        }
        users.remove(user);
    }
}
