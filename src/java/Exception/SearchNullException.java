/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Exception;

/**
 *
 * @author Elsa
 */
public class SearchNullException extends Exception{
    public SearchNullException(){
        super("Your search returned no results! Try again!");
    }
}
