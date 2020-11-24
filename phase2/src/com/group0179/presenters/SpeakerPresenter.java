package com.group0179.presenters;

import com.group0179.use_cases.MessageManager;
import com.group0179.use_cases.RoomManager;
import com.group0179.use_cases.UserManager;

public class SpeakerPresenter {
    private final UserManager um;
    private final RoomManager rm;
    private final MessageManager mm;

    public SpeakerPresenter(UserManager um, RoomManager rm, MessageManager mm) {
        this.um = um;
        this.rm = rm;
        this.mm = mm;
    }
}
