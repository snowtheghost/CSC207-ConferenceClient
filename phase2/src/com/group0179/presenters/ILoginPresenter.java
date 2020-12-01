package com.group0179.presenters;

/**
 * Interface for login presenters.
 * @author Zachariah Vincze
 */
public interface ILoginPresenter {
    /**
     * @return a string representing a user's account creation success.
     */
    String accountCreationSuccess();

    /**
     * @return a string representing a user's account creation failure.
     */
    String accountCreationFailure();

    /**
     * @return a string representing a user's login failure.
     */
    String loginFailure();

    /**
     * @return a string representing the username prompt.
     */
    String usernamePrompt();

    /**
     * @return a string representing the account type prompt.
     */
    String accountTypePrompt();

    /**
     * @return a string representing the organizer account choice.
     */
    String organizerAccountChoice();

    /**
     * @return a string representing the attendee account choice.
     */
    String attendeeAccountChoice();

    /**
     * @return a string representing the speaker account choice.
     */
    String speakerAccountChoice();

    /**
     * @param username the username.
     * @return a string representing a username not found error.
     */
    String usernameNotFoundError(String username);

    /**
     * @param username the username.
     * @return a string representing that a username was taken.
     */
    String usernameTakenError(String username);

    String loginButtonPrompt();

    String createAccountButtonPrompt();
}
