/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlog16java.Exceptions;

/**
 *
 * @author precognox
 */
public class NotNewDateException extends RuntimeException{

    public NotNewDateException() {
    }

    public NotNewDateException(String message) {
        super(message);
    }
    
}
