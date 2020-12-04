package com.group0179.presenters;

import com.group0179.use_cases.UserManager;

public class LoginPresenterCH implements ILoginPresenter {
    /**
     * @return a string representing a user's account creation success.
     */
    @Override
    public String accountCreationSuccess() { return "账户成功注册！"; }

    /**
     * @return a string representing a user's account creation failure.
     */
    @Override
    public String accountCreationFailure() { return "账户注册失败"; }

    /**
     * @return a string representing a user's login failure.
     */
    @Override
    public String loginFailure() { return "无法登陆该证书."; }

    /**
     * @return a string representing the username prompt.
     */
    @Override
    public String usernamePrompt() { return "用户名:"; }

    /**
     * @return a string representing the account type prompt.
     */
    @Override
    public String accountTypePrompt() { return "账户类型:"; }

    /**
     * @return a string representing the organizer account choice.
     */
    @Override
    public String organizerAccountChoice() { return "Organizer"; }

    /**
     * @return a string representing the attendee account choice.
     */
    @Override
    public String attendeeAccountChoice() { return "Attendee"; }

    /**
     * @return a string representing the speaker account choice.
     */
    @Override
    public String speakerAccountChoice() { return "Speaker"; }

    /**
     * @param username the username.
     * @return a string representing a username not found error.
     */
    @Override
    public String usernameNotFoundError(String username) {
        return "用户名" + username + "不存在";
    }

    /**
     * @param username the username.
     * @return a string representing that a username was taken.
     */
    @Override
    public String usernameTakenError(String username) {
        return "用户名" + username + "已被使用";
    }

    @Override
    public String loginButtonPrompt() {
        return "登录";
    }

    @Override
    public String createAccountButtonPrompt() {
        return "创建账号";
    }

    @Override
    public String vipAttendeeAccountChoice() { return "参与者(VIP)"; }
}