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
    }

    /**
     * Prints a list of commands that the user can input
     */
    public String displayCommands(){
        // displays all possible commands
        return "Logout|Message|View messages|View all events|View signed up events|Join event|Leave event";
    }
    /**
     * @param currUserID The current user's userid
     * @return Returns an Integer if user wants to exit, null otherwise
     */
    public Integer Message(UUID currUserID){
        this.ap.dmPrompt();
        String response = input.nextLine().toLowerCase();
        if (response.equals("back")){return DefinitionsCLI.REMAIN_IN_STATE;}
        //keep asking for input until the input is an existing username or 'back'
        while (!userExists(response)) {
            this.ap.displayUserDoesNotExistError();
            response = input.nextLine();
            if (response.equals("back")){return DefinitionsCLI.REMAIN_IN_STATE;}
        }

        this.ap.typeMsgPrompt();
        String message = input.nextLine();
        UUID recipient = this.userMan.getUserID(response);
        this.msgMan.sendMessage(this.userMan, currUserID, recipient, message);
        this.ap.msgSentPrompt();
        return null;
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
     * Prints all existing events
     */
    private void viewAllEvents(){
        this.ap.displayAllEvents(roomMan.stringEventInfoAll());
    }

    /**
     * Prints all events the user signed up for
     * @param currUserID The current users UUID
     */
    private void viewSignedUpEvents(UUID currUserID){
        this.ap.displayAttendingEvents(roomMan.stringEventInfoAttending(currUserID));
    }

    /**
     * Asks user for an event name and prints whether it successfully signed up/signed out.
     * @param currUserID The current users UUID
     * @param userMan The userManager class
     * @param joinOrLeave A string either "joining" or "leaving" depending on what the user wants to do.
     * @return Returns an integer if user wants to go back out of the command, null otherwise
     */
    private Integer joinLeaveEvent(UUID currUserID, UserManager userMan, String joinOrLeave){
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
