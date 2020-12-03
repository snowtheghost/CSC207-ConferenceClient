package com.group0179.entities;

import java.io.Serializable;
import java.util.*;

/**
 * Represents an abstract User
 * @author Zachariah Vincze
 */
public abstract class User implements Serializable {
    private final UUID userID;
    private final String username;
    private final Map<UUID, List<UUID>> conversations;
    private boolean isVip = false;
    private Calendar lastLoggedIn;
    private Calendar timeOfAccountCreation;
    private List<Double> lengthsOfTimeLoggedInAsMinutes;

    /**
     * Creates a new user with a unique ID and a username.
     * @param username The user's username.
     */
    public User(String username) {
        this.userID = UUID.randomUUID();
        this.username = username;
        this.conversations = new HashMap<>();
        this.lengthsOfTimeLoggedInAsMinutes = new ArrayList<>();
        this.lastLoggedIn = null;
        this.timeOfAccountCreation = Calendar.getInstance();
    }
    /**
     * Creates a new user with a unique ID and a username and changes the vip status.
     * @param username The user's username.
     */
    public User(String username, boolean isVip) {
        this.userID = UUID.randomUUID();
        this.username = username;
        this.conversations = new HashMap<>();
        this.isVip = isVip;
        this.lengthsOfTimeLoggedInAsMinutes = new ArrayList<>();
        this.lastLoggedIn = null;
        this.timeOfAccountCreation = Calendar.getInstance();
    }

    /**
     * Returns whether the user is an vip
     * @return True if attendee is an vip, false otherwise.
     */
    public boolean isVip() {
        return this.isVip;
    }

    /**
     * @return the User's ID
     */
    public UUID getUserID() {
        return userID;
    }

    /**
     * @return the User's name
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns a list of message UUIDs that this user has received from a specific sender.
     * @param sender the UUID of the sender of the messages.
     * @return a list of messages UUIDs that have been sent by sender.
     */
    public List<UUID> getMessages(UUID sender) {
        if(!conversations.containsKey(sender)) conversations.put(sender, new ArrayList<>());
        return this.conversations.get(sender);
    }

    /**
     * @return a set of UUIDs of users that have sent messages to this user.
     */
    public Set<UUID> getContacts() {
        return this.conversations.keySet();
    }

    public Calendar getTimeOfAccountCreation(){
        return this.timeOfAccountCreation;
    }

    /**
     * Adds a message to this user's received message history.
     * @param sender the UUID of the user who sent this message.
     * @param messageID the UUID of the message that was sent.
     */
    public void addMessage(UUID sender, UUID messageID) {
        if (!this.conversations.containsKey(sender)) {
            this.conversations.put(sender, new ArrayList<>());
        }
        this.conversations.get(sender).add(messageID);
    }
    //kaiyi
    /**
     * Sets last logged in time stamp for this user
     * @param calendar
     */
    public void setLastLoggedIn(Calendar calendar){
        this.lastLoggedIn = calendar;
    }
    //kaiyi
    /**
     * Get last logged in time stamp for this user
     * @return a Calendar object which is the timestamp for last logged in time
     */
    public Calendar getLastLoggedIn(){
        return (Calendar) this.lastLoggedIn.clone();
    }
    //kaiyi
    /**
     * Adds a time period to this user which indicates amount of time user was logged in for
     * @param timeElapsed
     * @return boolean value to indicate whether time was added successfully
     */
    public boolean addNewTimeLoggedIn(double timeElapsed){
        if(timeElapsed > 0){
            this.lengthsOfTimeLoggedInAsMinutes.add(timeElapsed);
            return true;
        }
        return false;
    }
    //kaiyi
    /**
     * @return a List containing each logged in time period for the user since account creation
     */
    public List<Double> getLengthsOfTimeLoggedIn(){
        return new ArrayList<>(this.lengthsOfTimeLoggedInAsMinutes);
    }
    //kaiyi
    /**
     * Get average amount of time this user is logged in for
     * @return a double which represents the average amount of time this user is logged in for
     */
    public double getAverageLengthOfTimeLoggedIn(){
        double totalTimeLoggedInAsMinutes = 0;
        for(double timeElapsed : this.lengthsOfTimeLoggedInAsMinutes){
            totalTimeLoggedInAsMinutes += timeElapsed;
        }
        if(this.lengthsOfTimeLoggedInAsMinutes.size() > 0) {
            return totalTimeLoggedInAsMinutes / this.lengthsOfTimeLoggedInAsMinutes.size();
        }
        return totalTimeLoggedInAsMinutes;
    }
    //kaiyi
    /**
     * Get total amount of time this user is logged in for
     * @return a double which represents the total amount of time this user is logged in for
     */
    public double getTotalMinutesLoggedIn(){
        double totalTimeLoggedInAsMinutes = 0;
        for(double timeElapsed : this.lengthsOfTimeLoggedInAsMinutes){
            totalTimeLoggedInAsMinutes += timeElapsed;
        }
        return totalTimeLoggedInAsMinutes;
    }
    //kaiyi
    /**
     * Get maximum and minimum amount of time this user is logged in for
     * @return an array which contains the maximum and minimum amount of time this user is logged in for
     */
    public double[] getMaximumAndMinimumMinutesLoggedIn(){
        double maximum = 0;
        double minimum = 0;
        if(!this.lengthsOfTimeLoggedInAsMinutes.isEmpty()) {
            minimum = this.lengthsOfTimeLoggedInAsMinutes.get(0);
        }
        for(double timeElapsed : this.lengthsOfTimeLoggedInAsMinutes){
            if(timeElapsed > maximum){
                maximum = timeElapsed;
            }
            if(timeElapsed < minimum){
                minimum = timeElapsed;
            }
        }
        double[] maximumAndMinimumValues = {maximum, minimum};
        return maximumAndMinimumValues;
    }

    /**
     * Checks if User is an Organizer.
     * @return returns true if User is an Organizer, otherwise return false.
     * */
    public abstract boolean isOrganizer();

    /**
     * Check if User is an Attendee.
     * @return returns true if User is an Attendee, otherwise return false.
     */
    public abstract boolean isAttendee();

    /**
     * Check if User is an Speaker.
     * @return returns true if User is an Speaker, otherwise return false.
     */
    public abstract boolean isSpeaker();

    /**
     * @return the user's type as a string representation.
     */
    public abstract String getStringType();
}
