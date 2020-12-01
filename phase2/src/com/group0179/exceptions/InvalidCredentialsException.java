package com.group0179.exceptions;

/**
 * Exception for use in user authentication.
 * @author Zachariah Vincze
 */
public class InvalidCredentialsException extends Exception {
    public InvalidCredentialsException() { super(); }
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
