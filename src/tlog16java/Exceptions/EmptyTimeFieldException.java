/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlog16java.Exceptions;

/**
 *
 * @author Flamy
 */
public class EmptyTimeFieldException extends RuntimeException {

    public EmptyTimeFieldException(String string) {
        super(string);
    }
    
    public EmptyTimeFieldException() {
    }           
}