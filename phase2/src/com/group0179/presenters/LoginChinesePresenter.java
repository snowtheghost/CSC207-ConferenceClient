package com.group0179.presenters;

import com.group0179.exceptions.InvalidCredentialsException;
import com.group0179.exceptions.UsernameTakenException;
import com.group0179.use_cases.UserManager;

public class LoginChinesePresenter extends Presenter {
    private final UserManager um;

    /**
     * @param um the UserManager this presenter wishes to use.
     */
    public LoginChinesePresenter(UserManager um) {
        this.um = um;
    }

    /**
     * @param accountType a string representing the type of account to create (not case sensitive).
     * @param username the desired username for the account.
     * @return True iff the account was created successfully.
     * @throws UsernameTakenException if the given username already exists in the system.
     */
    public boolean createAccount(String accountType, String username) throws UsernameTakenException {
        if (this.um.userExists(username)) {
            throw new UsernameTakenException("用户名" + username + "已被使用");
        }
        switch (accountType.toLowerCase()) {
            case "organizer":
                um.createOrganizerAccount(username); break;
            case "attendee":
                um.createAttendeeAccount(username, false); break; // TODO: Figure out VIP system
            case "speaker":
                um.createSpeakerAccount(username); break;
            default: return false;
        }
        return true;
    }

    /**
     * Attempts to login a user. Sets the current user iff the login was successful.
     * @param username the username of the user.
     * @return The logged in user's account type iff the user was logged in successfully.
     * @throws InvalidCredentialsException if the user could not be logged in with the given credentials.
     */
    public String loginUser(String username) throws InvalidCredentialsException {
        if (!this.um.userExists(username)) {
            throw new InvalidCredentialsException("用户名" + username + "不存在");
        }
        this.um.setCurrentUser(username);
        return this.um.userType(username);
    }

    /**
     * @return a string representing a user's account creation success.
     */
    public String accountCreationSuccess() { return "账户成功注册！"; }

    /**
     * @return a string representing a user's account creation failure.
     */
    public String accountCreationFailure() { return "账户注册失败"; }

    /**
     * @return a string representing a user's login failure.
     */
    public String loginFailure() { return "无法登陆该证书."; }

    /**
     * @return a string representing the username prompt.
     */
    public String usernamePrompt() { return "用户名:"; }

    /**
     * @return a string representing the account type prompt.
     */
    public String accountTypePrompt() { return "账户类型:"; }

    /**
     * @return a string representing the organizer account choice.
     */
    public String organizerAccountChoice() { return "Organizer"; }

    /**
     * @return a string representing the attendee account choice.
     */
    public String attendeeAccountChoice() { return "Attendee"; }

    /**
     * @return a string representing the speaker account choice.
     */
    public String speakerAccountChoice() { return "Speaker"; }
}