/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Exceptions;

/**
 *
 * @author Elsa
 */
public class MusicsAlreadyExistInPlaylistException extends Exception{
    public MusicsAlreadyExistInPlaylistException(){
        super("This music already exist in the select playlist.");
    }
}
