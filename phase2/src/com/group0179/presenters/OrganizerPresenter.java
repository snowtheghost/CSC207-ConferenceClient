package com.group0179.presenters;

import com.group0179.use_cases.MessageManager;
import com.group0179.use_cases.RoomManager;
import com.group0179.use_cases.UserManager;

import java.util.ArrayList;

public class OrganizerPresenter {
    private final UserManager um;
    private final RoomManager rm;
    private final MessageManager mm;

    private final int START_HOUR_EARLIEST = 9;
    private final int START_HOUR_LATEST = 16;

    public OrganizerPresenter(UserManager um, RoomManager rm, MessageManager mm) {
        this.um = um;
        this.rm = rm;
        this.mm = mm;
    }

    public ArrayList<String> getSpeakerNames() {
        return um.getSpeakerNames();
    }
}
