package com.group0179.controllers;

import com.group0179.presenters.SpeakerPresenterEN;
import com.group0179.presenters.ISpeakerPresenter;
import com.group0179.presenters.Presenter;
import com.group0179.use_cases.MessageManager;
import com.group0179.use_cases.RoomManager;
import com.group0179.use_cases.UserManager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

public class AutofillController {
    private final UserManager userMan;
    private final MessageManager msgMan;
    private final RoomManager roomMan;
    private final UUID currUserID;

    public AutofillController(UserManager userMan, RoomManager roomMan, MessageManager msgMan) {
        this.userMan = userMan;
        this.msgMan = msgMan;
        this.roomMan = roomMan;
        this.currUserID = this.userMan.getCurrentUser();
    }

    /**
     * @param input1 the first several characters of a username
     * @return a list of usernames that start with those characters
     */
    public List<String> autofillUsername(AtomicReference<String> input1) {
        return userMan.retrieveUserNamesGivenQuery(input1.toString());
    }

    /**
     * @param input1 the first several characters of an event
     * @return a list of events that start with those characters
     */
    public List<String> autofillEvents(AtomicReference<String> input1) {
        return roomMan.queryEventTitles(input1.toString());
    }
}
