package com.group0179.presenters;

import com.group0179.cli.DefinitionsCLI;
import com.group0179.cli.InputFilterCLI;
import com.group0179.cli.presenters_cli.AttendeePresenterCLI;
import com.group0179.entities.Attendee;
import com.group0179.filters.AttendeeFilter;
import com.group0179.use_cases.MessageManager;
import com.group0179.use_cases.RoomManager;
import com.group0179.use_cases.UserManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class AttendeePresenter extends Presenter {
    private final UserManager userMan;
    private final MessageManager msgMan;
    private final RoomManager roomMan;
    private final AttendeePresenterCLI ap;
    private final Scanner input = new Scanner(System.in);
    private final AttendeeFilter attendeeFilter;
    private final UUID currUserID;


    /**
     * Allows attendees to do attendee things.
     * @param userMan the user manager use case class
     * @param msgMan the user manager use case class
     * @param roomMan the user manager use case class
     */
    public AttendeePresenter(UserManager userMan, RoomManager roomMan, MessageManager msgMan) {
        this.userMan = userMan;
        this.msgMan = msgMan;
        this.roomMan = roomMan;
        this.ap = new AttendeePresenterCLI(userMan, msgMan);
        this.attendeeFilter = new AttendeeFilter(userMan, roomMan, msgMan);
        this.currUserID = this.userMan.getCurrentUser();
    }

    /**
     * Prints a list of commands that the user can input
     */
    public String displayCommands(){
        // displays all possible commands
        return "Logout|Message|View messages|View all events|View signed up events|Join event|Leave event";
    }
    /**
     * @param username The username of the person the user wants to message
     * @param content The content of the message.
     * @return Returns a string saying the user either does not exist or message sent success.
     */
    public String message(String username, String content){
        if (!userExists(username)) {
            return "Recipient does not exist";
        }
        UUID currUserID = this.userMan.getCurrentUser();
        UUID recipient = this.userMan.getUserID(username);
        this.msgMan.sendMessage(this.userMan, currUserID, recipient, content);
        return "Message sent successfully.";
    }

    /**
     * Prints the messages from a specific user or all users who sent him at least one message.
     * @param input The username of the user we want to get all messages of.
     * @return Returns the messages from that user, all users, or No users found.
     *
     * TODO: Check fixes for UM
     */
    public String viewMessages(String input){
        UUID currUserID = this.userMan.getCurrentUser();
        ArrayList<UUID> allUserIds = new ArrayList<>(this.userMan.getAttendeeUUIDs());
        // if want all messages, get a list of messages from each user and
        // write the sender name and message contents if user received at least 1 message from them
        if (input.equals("all")){
            StringBuilder allMsgs = new StringBuilder("Messages:\n");
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
        if (recipient == null) {return "User not found";}
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
            return "No events available";
        }
        return allEvents;
    }

    /**
     * @return A string of all events the user signed up for
     */
    public String viewSignedUpEvents(){
        UUID currUserID = this.userMan.getCurrentUser();
        return roomMan.stringEventInfoAttending(currUserID);
    }

    /**
     * Takes an eventID and the corrisponding roomID, and whether the user wants to join
     * or leave the event, returns a success message string or string explaining why action could
     * not be preformed.
     * @param currUserID The current users UUID
     * @param userMan The userManager class
     * @param joinOrLeave A string either "joining" or "leaving" depending on what the user wants to do.
     * @return Returns an integer if user wants to go back out of the command, null otherwise
     */
    public Integer joinLeaveEvent(UUID currUserID, UserManager userMan, String joinOrLeave){

        // keeps asking user to input room number until valid number or user wants to cancel
        // if user wants to cancel, it'll return -1 and we will go back to attendee panel
        this.ap.joinLeaveEventOrRoomPrompt(joinOrLeave, "room");
        int inputRoomNum = this.attendeeFilter.inputRoom();
        if (inputRoomNum==-1){return DefinitionsCLI.REMAIN_IN_STATE;}

        // keeps asking user to input event number until valid number or user wants to cancel
        // if user wants to cancel, it'll return -1 and we will go back to attendee panel
        this.ap.joinLeaveEventOrRoomPrompt(joinOrLeave, "event");
        int inputEventNum = this.attendeeFilter.inputEventNumber(inputRoomNum);
        if (inputEventNum==-1){return DefinitionsCLI.REMAIN_IN_STATE;}

        //Signs user up to event in room
        if (joinOrLeave.equals("joining") && this.roomMan.addEventAttendee(currUserID, inputRoomNum,
                inputEventNum, userMan, this.userMan.isUserVip(currUserID))){
            this.ap.displayJoinLeaveSuccess(joinOrLeave);
        } else if (joinOrLeave.equals("leaving") && this.roomMan.removeEventAttendee(currUserID, inputRoomNum, inputEventNum, userMan)){
            this.ap.displayJoinLeaveSuccess(joinOrLeave);
        } else {
            this.ap.displayJoinLeaveError(joinOrLeave);
            return DefinitionsCLI.REMAIN_IN_STATE;
        }

        return null;
    }

    /**
     * Takes a username and returns true iff username is from an existing user
     * also prints a message telling user if username does not exist.
     * @param username the given username as a string
     * @return Whether the username is from an existing user
     */
    private boolean userExists(String username) {
        if (this.userMan.getUsernames().contains(username)) {
            return true;
        }
        ap.displayUserDoesNotExistError();
        return false;
    }
}
