package com.group0179.PresenterFactory;

import com.group0179.presenters.LoginPresenterCH;
import com.group0179.presenters.LoginPresenterEN;

public class LoginPresenterFactory extends PresenterFactory{
    public LoginPresenterEN getLoginPresenterEN(){return new LoginPresenterEN();}

    public LoginPresenterCH getLoginPresenterCH(){
        return new LoginPresenterCH();
    }
}
