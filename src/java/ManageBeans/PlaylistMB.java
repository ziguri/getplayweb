/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ManageBeans;

import ejbs.PlaylistFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Elsa
 */
@ManagedBean
@RequestScoped
public class PlaylistMB {
    @EJB
    private PlaylistFacade playlists;

    /**
     * Creates a new instance of PlaylistMB
     */
    public PlaylistMB() {
    }
    
}
