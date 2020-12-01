package com.group0179.exceptions;

/**
 * Exception for use with account creation methods.
 * @author Zachariah Vincze
 */
public class UsernameTakenException extends Exception {
    public UsernameTakenException() { super(); }
    public UsernameTakenException(String message) {
        super(message);
    }
}
