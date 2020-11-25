package com.group0179.presenters;

import com.group0179.use_cases.MessageManager;
import com.group0179.use_cases.RoomManager;
import com.group0179.use_cases.UserManager;

import java.util.ArrayList;
import java.util.UUID;

public class SpeakerPresenter extends Presenter {
    private final UserManager userMan;
    private final RoomManager roomMan;
    private final MessageManager msgMan;

    public SpeakerPresenter(UserManager userMan, RoomManager roomMan, MessageManager msgMan) {
        this.userMan = userMan;
        this.roomMan = roomMan;
        this.msgMan = msgMan;
    }

    public ArrayList<String> getEventListArray() {
        ArrayList<UUID> eventIDs = roomMan.getEventIDs();
        ArrayList<String> events = new ArrayList<>();
        for (UUID eventID: eventIDs) {
            events.add(roomMan.stringEvent(eventID));
        } return events;
    }

    public String viewAllEventsButton() { return "View All Events"; }

}
