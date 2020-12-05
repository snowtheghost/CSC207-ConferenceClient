package com.group0179.PresenterFactory;

import com.group0179.presenters.AttendeePresenterCH;
import com.group0179.presenters.AttendeePresenterEN;

public class AttendeePresenterFactory {

    public AttendeePresenterEN getAttendeePresenterEN(){return new AttendeePresenterEN();}

    public AttendeePresenterCH getAttendeePresenterCH(){
        return new AttendeePresenterCH();
    }
}
