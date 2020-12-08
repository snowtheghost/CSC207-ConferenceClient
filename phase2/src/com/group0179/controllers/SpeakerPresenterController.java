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

public class SpeakerPresenterController extends Presenter {
    private final UserManager userMan;
    private final MessageManager msgMan;
    private final RoomManager roomMan;
    private final UUID currUserID;
    private ISpeakerPresenter langPresenter= new SpeakerPresenterEN();


    /**
     * Allows attendees to do attendee things.
     * @param userMan the user manager use case class
     * @param msgMan the user manager use case class
     * @param roomMan the user manager use case class
     */
    public SpeakerPresenterController(UserManager userMan, RoomManager roomMan, MessageManager msgMan) {
        this.userMan = userMan;
        this.msgMan = msgMan;
        this.roomMan = roomMan;
        this.currUserID = this.userMan.getCurrentUser();
    }

    /**
     * Changes the language presenter so the returned texts will be in a different language.
     * @param newPresenter The new type of language presenter.
     */
    public void changeLangPresenter(ISpeakerPresenter newPresenter){
        this.langPresenter = newPresenter;
    }

    /**
     * @param username The username of the person the user wants to message
     * @param content The content of the message.
     * @return Returns a string saying the user either does not exist or message sent success.
     */
    public String message(String username, String content){
        if (!userExists(username)) {
            return langPresenter.recipientDNE();
        }
        UUID recipient = this.userMan.getUserID(username);
        this.msgMan.sendMessage(this.userMan, currUserID, recipient, content);
        return langPresenter.sentSuccess();
    }

    public String messageEvent(String eventname, int roomNumber, String content){
        if (!eventExists(eventname, roomNumber)) {
            return langPresenter.eventDNE();
        }
        UUID eventid = this.roomMan.getEventUUIDfromNameandRoom(eventname, roomNumber);
        this.msgMan.sendMessageToEventAttendees(this.userMan, this.roomMan, currUserID, eventid, content);
        return langPresenter.sentSuccess();
    }



    /**
     * Prints the messages from a specific user or all users who sent him at least one message.
     * @param input The username of the user we want to get all messages of.
     * @return Returns the messages from that user, all users, or No users found.
     */
    public String viewMessages(String input){
        ArrayList<UUID> allUserIds = new ArrayList<>(this.userMan.getAttendeeUUIDs());
        // if want all messages, get a list of messages from each user and
        // write the sender name and message contents if user received at least 1 message from them
        if (input.equals("all")){
            StringBuilder allMsgs = new StringBuilder(langPresenter.messageWord() + "\n");
            for (UUID uuid : allUserIds){
                List<String> msgsFromPerson = this.msgMan.getMessageContentsFromUser(this.userMan, currUserID, uuid);
                if (msgsFromPerson.size()!=0){
                    String senderName = this.userMan.getUsername(uuid);
                    allMsgs.append(senderName).append(": ");
                    for (String msg : msgsFromPerson) {
                        allMsgs.append(msg).append(", ");
                    }
                    allMsgs.append("\n");
                }
            }
            return allMsgs.toString();
        }
        // if user wants message from specific user
        UUID recipient = this.userMan.getUserID(input);
        if (recipient == null) {return langPresenter.userNotFound();}
        List<String> msgContent = this.msgMan.getMessageContentsFromUser(this.userMan, currUserID, recipient);
        return String.join(",", msgContent);
    }

    /**
     * @return Returns a string on all existing events and the rooms they're in.
     * If there are no events, return "No events available".
     */
    public String viewAllEvents(){
        String allEvents = roomMan.stringEventInfoAll();
        if (allEvents.equals("")){
            return langPresenter.noEventsAvailiable();
        }
        return allEvents;
    }

    public String viewAllSpeakingEvents(){
        ArrayList<UUID> allSpeakingEvents = userMan.getSpeakerEventIDs(userMan.getCurrentUser());
        String allEvents = roomMan.stringEventsOfSpeaker(userMan, userMan.getUsername(userMan.getCurrentUser()));
        if (allSpeakingEvents.isEmpty()){
            return langPresenter.noSpeakingEvents();
            //return langPresenter.noEventsAvailiable();
        }
        return allEvents;
    }

    /**
     * @return A string of all events the user signed up for
     */
    public String viewSignedUpEvents(){
        return roomMan.stringEventInfoAttending(currUserID);
    }

    /**
     * Takes an eventID and the corrisponding roomID, and whether the user wants to join
     * or leave the event, returns a success message string or string explaining why action could
     * not be preformed.
     * @param roomNum The room number of the event that user wants to join.
     * @param eventName The event name
     * @param joinOrLeave Whether the he wants to try 'joining' or 'leaving'. (Must be in that format
     *                    or else invalid)
     * @return Whether the action was a success.
     */
    public String joinLeaveEvent(String joinOrLeave, String roomNum, String eventName){
        // Check if joinOrLeave is formatted right
        if (!(joinOrLeave.equals("j")||joinOrLeave.equals("l"))){
            return langPresenter.joinOrLeave();
        }
        // Check if roomnNum valid and event can be found
        if (!roomNum.matches("^[0-9]+$")){
            return langPresenter.invalidRoom();}
        int intRoomNum = Integer.parseInt(roomNum);
        UUID eventID = this.roomMan.getEventUUIDfromNameandRoom(eventName, intRoomNum);
        if (eventID==null){return langPresenter.noEventsFound();}

        if (joinOrLeave.equals("j") && this.roomMan.addEventAttendee(currUserID, eventID,
                this.userMan, this.userMan.isUserVip(currUserID))){
            //this.ap.displayJoinLeaveSuccess(joinOrLeave);
            return langPresenter.joinEventSuccess();
        } else if (joinOrLeave.equals("l") && this.roomMan.removeEventAttendee(currUserID, eventID, userMan)){
            //this.ap.displayJoinLeaveSuccess(joinOrLeave);
            return langPresenter.leaveEventSuccess();
        }
        //this.ap.displayJoinLeaveError(joinOrLeave);
       return joinOrLeave.equals("j") ? langPresenter.joinEventFail() : langPresenter.leaveEventFail();
    }

    /**
     * @return array of past login times for the current user.
     */
    public List<Double> getPastLoginTimes(){
        return userMan.getLengthsOfTimeLoggedInAsMinutesForCurrentUser();
    }

    /**
     * @return a string containing user stats.
     */
    public String getUserStats(){
        // User stats label
        String userStats = langPresenter.userStats() + "\n" + langPresenter.updateInfo() + "\n\n";

        // Last login label and datetime.
        String lastLoginTime = String.valueOf(userMan.getLastLoggedInForCurrentUser().getTime().toString());
        String lastLoginMsg = langPresenter.lastLogin() + lastLoginTime + "\n\n";

        // Average login time.
        String avgLoginTime = langPresenter.avgLoginTime() + Math.round(userMan.getAverageLengthOfTimeLoggedInForCurrentUser()) + "\n\n";

        // Total login label and datetime.
        String totalLoginMsg = langPresenter.totalLoginTime() + Math.round(userMan.getTotalMinutesLoggedInForCurrentUser()) + "\n\n";

        // Record max and min logged in label and time.
        double[] maxMinTimes = userMan.getMaximumAndMinimumMinutesLoggedInForCurrentUser();
        String maxLogintimeMsg = langPresenter.maxLoginTime() + Math.round(maxMinTimes[0]) + "\n\n";
        String minLoginTimeMsg = langPresenter.minLoginTime() + Math.round(maxMinTimes[1]) + "\n\n";

        return userStats + lastLoginMsg + totalLoginMsg + avgLoginTime + maxLogintimeMsg + minLoginTimeMsg;
    }

    public String getLastLogin(){
        return userMan.getLastLoggedInForCurrentUser().getTime().toString();
        //userMan.getTotalMinutesLoggedInForCurrentUser() +
        //Arrays.toString(userMan.getMaximumAndMinimumMinutesLoggedInForCurrentUser());
    }

    public String getavgLoginTime(){
        DecimalFormat df = new DecimalFormat("###.##");
        return df.format(userMan.getAverageLengthOfTimeLoggedInForCurrentUser());
        //return String.valueOf(Math.round(userMan.getAverageLengthOfTimeLoggedInForCurrentUser()));
    }

    public String getaTotalLoginTime(){
        DecimalFormat df = new DecimalFormat("###.##");
        return df.format(userMan.getTotalMinutesLoggedInForCurrentUser());
        //return String.valueOf(Math.round(userMan.getAverageLengthOfTimeLoggedInForCurrentUser()));
    }

    public List<String> autofillUsername(AtomicReference<String> input1){
        return userMan.retrieveUserNamesGivenQuery(input1.toString());
    }

    public List<String> autofillEvents(AtomicReference<String> input1){
        return roomMan.queryEventTitles(input1.toString());
    }

    /**
     * Takes a username and returns true iff username is from an existing user
     * also prints a message telling user if username does not exist.
     * @param username the given username as a string
     * @return Whether the username is from an existing user
     */
    private boolean userExists(String username) {
        return this.userMan.getUsernames().contains(username);
    }

    private boolean eventExists(String eventname, int roomNumber) {
        return this.roomMan.getEventIDs().contains(this.roomMan.getEventUUIDfromNameandRoom(eventname, roomNumber));
    }
}
