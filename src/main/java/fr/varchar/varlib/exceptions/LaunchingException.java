package fr.varchar.varlib.exceptions;

public class LaunchingException extends Exception {

    public LaunchingException(String message) {
        super(message);
    }

    public LaunchingException(String message, Throwable cause) {
        super(message, cause);
    }


}