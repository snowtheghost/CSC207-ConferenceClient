package com.group0179.PresenterFactory;

import com.group0179.presenters.OrganizerPresenterCH;
import com.group0179.presenters.OrganizerPresenterEN;
import com.group0179.use_cases.MessageManager;
import com.group0179.use_cases.RoomManager;
import com.group0179.use_cases.UserManager;

public class OrganizerPresenterFactory {

    private final UserManager um;
    private final RoomManager rm;
    private final MessageManager mm;

    public OrganizerPresenterFactory(UserManager um, RoomManager rm, MessageManager mm){
        this.um = um;
        this.rm = rm;
        this.mm =mm;
    }

    public OrganizerPresenterEN getOrganizerPresenterEN(){
        return new OrganizerPresenterEN(um,rm,mm);
    }

    public OrganizerPresenterCH getOrganizerPresenterCH(){
        return new OrganizerPresenterCH(um, rm, mm);
    }
}
