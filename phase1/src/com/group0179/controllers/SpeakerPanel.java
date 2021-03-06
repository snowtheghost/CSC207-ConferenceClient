package com.group0179.controllers;

import com.group0179.Definitions;
import com.group0179.presenters.SpeakerPresenter;
import com.group0179.use_cases.MessageManager;
import com.group0179.use_cases.RoomManager;
import com.group0179.use_cases.UserManager;

import java.util.*;

/**
 * @author Liam Ogilvie
 */

public class SpeakerPanel implements IController {
    private final UserManager userMan;
    private final RoomManager roomMan;
    private final MessageManager msgMan;
    private final SpeakerPresenter speakerPres;
    private final Scanner input = new Scanner(System.in);

    /**
     * @param msgMan: the MessageManager
     * @param userMan: the UserManager
     * @param roomMan: the RoomManager
     */
    public SpeakerPanel(UserManager userMan, MessageManager msgMan, RoomManager roomMan) {
        this.msgMan = msgMan;
        this.userMan = userMan;
        this.roomMan = roomMan;
        speakerPres = new SpeakerPresenter(userMan, roomMan, msgMan);
    }

    /**
     * Author: Liam Ogilvie
     * Main run method
     */
    @Override
    public int run() {
        UUID currUserID = this.userMan.getCurrentUser();
        ArrayList<UUID> allUserIds = new ArrayList<>(this.userMan.getAttendeeUUIDs());
        allUserIds.addAll(this.userMan.getOrganizerUUIDs());
        allUserIds.addAll(this.userMan.getSpeakerUUIDs());

        speakerPres.welcomePrompt();
        String decision = input.nextLine();
        while (!(decision.equals("logout") || decision.equals("quit"))){
            switch (decision) {
                case "help":
                    speakerPres.commandHelp(); break;

                case "view messages":
                    viewMessages(allUserIds, currUserID);
                    break;

                case "view all events":
                    viewAllEvents();
                    break;

                case "view speaking events":
                    viewSpeakingEvents(currUserID);
                    break;

                case "send message to event attendees":
                    sendMessageToAttendees(currUserID);
                    break;

                case "send direct message":
                    Message(currUserID, allUserIds);
                    break;

                default:
                    speakerPres.errorInvalidInput(decision); break;
            }
            decision = input.nextLine();
        }

        // Go back to LoginSystem or quits app if user types the command for either
        if (decision.equals("logout")){return Definitions.BACK;}
        return Definitions.QUIT;
    }
    /**
     * Author: Liam Ogilvie
     * Sends a message to a specific user
     */
    private Integer Message(UUID currUserID, ArrayList<UUID> allUserIds){
        this.speakerPres.dmPrompt();
        String response = input.nextLine();
        if (response.equals("q")){
            speakerPres.welcomePrompt();
            return Definitions.REMAIN_IN_STATE;}
        if (response.equals("a")){speakerPres.listAllUsers();}
        //keep asking for input until the input is an existing username or 'back'
        while (!userExists(response)) {
            speakerPres.errorUserNotFound();
            response = input.nextLine();
            if (response.contains("q")){
                speakerPres.welcomePrompt();
                return Definitions.REMAIN_IN_STATE;}
        }

        this.speakerPres.typeMsgPrompt();
        String message = input.nextLine();
        UUID recipient = this.userMan.getUserID(response);
        this.msgMan.sendMessage(this.userMan, currUserID, recipient, message);
        this.speakerPres.msgSentPrompt(userMan.getUsername(recipient));
        return null;
    }
    /**
     * Author: Liam Ogilvie
     * A helper method that checks if a user exists
     */
    private boolean userExists(String username) {
        if (this.userMan.getUsernames().contains(username)) {
            return true;
        }
        speakerPres.errorUserNotFound();
        return false;
    }
    /**
     * Author: Liam Ogilvie
     * Allows the speaker to view all their messages
     */
    private void viewMessages(ArrayList<UUID> allusers, UUID currUser){
        speakerPres.viewMessages(allusers, currUser);
        speakerPres.welcomePrompt();

    }
    /**
     * Author: Liam Ogilvie
     * Allows the speaker to view all of the events occurring at the conference
     */
    private void viewAllEvents(){
        speakerPres.viewAllEvents();
        speakerPres.welcomePrompt();
    }
    /**
     * Author: Liam Ogilvie
     * Shows all of the events that the speaker is speaking at
     */
    private void viewSpeakingEvents(UUID speakerID){
        speakerPres.viewSpeakingEvents(speakerID);
        speakerPres.welcomePrompt();
    }
    /**
     * Author: Liam Ogilvie
     * Sends a message to all attendees of an event (or events) that the speaker is speaking at
     */
    private int sendMessageToAttendees(UUID speakerID) {
        if (userMan.getSpeakerEventIDs(speakerID).size() == 0) {
            speakerPres.errorNoSpeakingEvents();
            speakerPres.welcomePrompt();
            return Definitions.REMAIN_IN_STATE;
        } else {
            ArrayList<Integer> recips = new ArrayList<>();
            speakerPres.sendAllAttendeesIntro(speakerID);
            String decision = input.nextLine();

            if (decision.equals("q")) {
                speakerPres.welcomePrompt();
                return Definitions.REMAIN_IN_STATE;
            }

            try {
                new Integer(decision);
            } catch (NumberFormatException e) {
                speakerPres.errorInvalidInput(decision);
                decision = "999999999"; //a really big number that *should* be larger than the number of events
            }
            while (true) {

                while (new Integer(decision) > userMan.getSpeakerEventIDs(speakerID).size()) {
                    decision = input.nextLine();
                    if (decision.contains("q")) {
                        speakerPres.welcomePrompt();
                        return Definitions.REMAIN_IN_STATE;
                    }
                    if (decision.contains("a") && recips.size() > 0) {
                        speakerPres.messagePrompt();
                        String message = input.nextLine();
                        for (Integer event : recips) {
                            UUID eventid = userMan.getSpeakerEventIDs(speakerID).get(event - 1);
                            UUID status = msgMan.sendMessageToEventAttendees(userMan, roomMan, speakerID, eventid, message);
                            if (status == null) {
                                speakerPres.errorGeneral();
                            } else {
                                speakerPres.messageSuccess(status, eventid);
                            }
                        }
                        speakerPres.welcomePrompt();
                        return Definitions.REMAIN_IN_STATE;
                    }
                    try {
                        if (new Integer(decision) > userMan.getSpeakerEventIDs(speakerID).size()) {
                            speakerPres.errorInvalidInput(decision);
                        }
                    } catch (NumberFormatException e) {
                        speakerPres.errorInvalidInput(decision);
                        decision = "999999999"; //a really big number that *should* be larger than the number of events
                    }

                }
                int selection = new Integer(decision);
                UUID eventid = userMan.getSpeakerEventIDs(speakerID).get(selection - 1);
                if (roomMan.getEventAttendeeIDs(eventid).size() == 0) {
                    speakerPres.errorNoAttendees();
                    decision = "999999999"; //yes I did it again, yes I know it's a cheap workaround
                    //if you are able to break it, I will fix it

                } else {
                    if (!(recips.contains(new Integer(decision)))) {
                        recips.add(new Integer(decision));
                    }
                    decision = "999999999"; //if it's stupid, and it works, it ain't stupid
                    speakerPres.messageSelectedTalks(recips);
                    //String command = input.nextLine();

                }
            }
        }
    }

}
