package com.group0179.presenters;

import com.group0179.use_cases.MessageManager;
import com.group0179.use_cases.RoomManager;
import com.group0179.use_cases.UserManager;

import java.util.*;

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
        ArrayList<UUID> eventIDs = this.roomMan.getEventIDs();
        ArrayList<String> events = new ArrayList<>();
        for (UUID eventID: eventIDs) {
            events.add(this.roomMan.stringEvent(eventID));
        } return events;
    }

    public ArrayList<String> getSpeakingEventListArray() {
        ArrayList<UUID> eventIDs = this.userMan.getSpeakerEventIDs(this.userMan.getCurrentUser());
        ArrayList<String> events = new ArrayList<>();
        for (UUID eventID: eventIDs) {
            events.add(this.roomMan.stringEvent(eventID));
        } return events;
    }

    public ArrayList<UUID> getAttendees() {
        return this.userMan.getAttendeeUUIDs();
    }

    public String getUsername(UUID id){return this.userMan.getUsername(id);}

    public Iterator<Map.Entry<UUID, List<String>>> viewMessages(){
        HashMap<UUID, List<String>> fmessages = new HashMap<>();
        for (UUID id : this.userMan.getAttendeeUUIDs()){
            List<String> messages = this.msgMan.getMessageContentsFromUser(this.userMan, this.userMan.getCurrentUser(), id);
            if (messages.size() > 0){
                fmessages.put(id, messages);
            }

        }
        Iterator<Map.Entry<UUID, List<String>>> entrySet = fmessages.entrySet().iterator();
        return entrySet;
    }

    public String message(String username, String content){
        if (!userExists(username)) {
            return "Recipient does not exist";
        }
        UUID recipient = this.userMan.getUserID(username);
        this.msgMan.sendMessage(this.userMan, this.userMan.getCurrentUser(), recipient, content);
        return "Message sent successfully.";
    }

    public String viewMessagess(String input){
        ArrayList<UUID> allUserIds = new ArrayList<>(this.userMan.getAttendeeUUIDs());
        // if want all messages, get a list of messages from each user and
        // write the sender name and message contents if user received at least 1 message from them
        if (input.equals("all")){
            StringBuilder allMsgs = new StringBuilder("Messages:\n");
            for (UUID uuid : allUserIds){
                List<String> msgsFromPerson = this.msgMan.getMessageContentsFromUser(this.userMan, userMan.getCurrentUser(), uuid);
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
        List<String> msgContent = this.msgMan.getMessageContentsFromUser(this.userMan, userMan.getCurrentUser(), recipient);
        return String.join(",", msgContent);
    }


    public String viewAllEventsButton() { return "View All Events"; }
    public String viewSpeakingEventsButton() { return "View All Speaking Events"; }

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
        //this.displayUserDoesNotExistError();
        System.out.println("User not found");
        return false;
    }

}
