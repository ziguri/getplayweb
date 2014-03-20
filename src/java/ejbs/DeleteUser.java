/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejbs;

import entities.AppUser;
import entities.Music;
import java.util.List;
import javax.ejb.Stateful;
import javax.inject.Inject;

/**
 *
 * @author Zueb LDA
 */
@Stateful
public class DeleteUser {

    @Inject
    MusicFacade musics;
    @Inject
    PlaylistFacade playlists;

    public boolean userRemove(AppUser user) {

        List<Music> userMusics = musics.showUserMusics(user);

        return true;
    }

}
