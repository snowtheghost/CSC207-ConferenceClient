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

    /**
     * Creates a new user with a unique ID and a username.
     * @param username The user's username.
     */
    public User(String username) {
        this.userID = UUID.randomUUID();
        this.username = username;
        this.conversations = new HashMap<>();
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
