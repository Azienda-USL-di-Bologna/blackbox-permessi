/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.bologna.ausl.blackbox.exceptions;

/**
 *
 * @author gdm
 */
public class BlackBoxPermissionException extends Exception {

    public BlackBoxPermissionException() {
    }

    public BlackBoxPermissionException(String message) {
        super(message);
    }

    public BlackBoxPermissionException(String message, Throwable cause) {
        super(message, cause);
    }

    public BlackBoxPermissionException(Throwable cause) {
        super(cause);
    }
   
}
