package com.group0179.controllers;

import com.group0179.exceptions.InvalidCredentialsException;
import com.group0179.exceptions.UsernameTakenException;
import com.group0179.use_cases.UserManager;

import java.util.Calendar;

/**
 * Handles user login based logic.
 */
public class LoginController {
    private final UserManager um;

    public LoginController(UserManager um) {
        this.um = um;
    }

    /**
     * Precondition: accountType is a valid account type.
     * @param accountType a string representing the type of account to create (not case sensitive).
     * @param username the desired username for the account.
     * @return True iff the account was created successfully.
     * @throws UsernameTakenException if the given username already exists in the system.
     */
    public void createAccount(String accountType, String username) throws UsernameTakenException {
        if (this.um.userExists(username)) {
            throw new UsernameTakenException();
        }
        switch (accountType.toLowerCase()) {
            case "organizer":
                um.createOrganizerAccount(username); break;
            case "attendee":
                um.createAttendeeAccount(username, false); break;
            case "vipattendee":
                um.createAttendeeAccount(username, true); break;
            case "speaker":
                um.createSpeakerAccount(username); break;
        }
    }

    /**
     * Attempts to login a user. Sets the current user iff the login was successful.
     * @param username the username of the user.
     * @return The logged in user's account type iff the user was logged in successfully.
     * @throws InvalidCredentialsException if the user could not be logged in with the given credentials.
     */
    public String loginUser(String username) throws InvalidCredentialsException {
        if (!this.um.userExists(username)) {
            throw new InvalidCredentialsException();
        }
        this.um.setCurrentUser(username);
        this.um.addLastLoggedInForCurrentUser(Calendar.getInstance());
        return this.um.userType(username);
    }

    /**
     * Logs the user out of the system and adds their new login duration.
     * @return True iff the user has been logged out successfully.
     */
    public boolean logoutUser() {
        if (this.um.getCurrentUser() == null) return false;
        long beginEpoch = this.um.getLastLoggedInForCurrentUser().getTimeInMillis();
        long endEpoch = Calendar.getInstance().getTimeInMillis();
        double elapsedMinutes = (double)(endEpoch - beginEpoch) / 1000 / 60;
        this.um.addNewTimeLoggedInForCurrentUser(elapsedMinutes);
        return true;
    }
}
