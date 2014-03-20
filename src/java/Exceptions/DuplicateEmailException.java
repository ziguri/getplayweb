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
public class DuplicateEmailException extends Exception{
    public DuplicateEmailException(){
        super("Email already exist! Try again!");
    }

}
