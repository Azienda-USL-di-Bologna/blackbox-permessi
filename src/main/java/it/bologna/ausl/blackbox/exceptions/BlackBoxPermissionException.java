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
