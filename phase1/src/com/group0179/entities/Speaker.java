package com.group0179.entities;

import java.util.*;

/**
 * Represent a Speaker
 *
 * Store the events and their corresponding rooms that the Speaker is responsible for
 * Get the events that the Speaker is responsible for
 * Add and remove events that the Speaker is responsible for
 *
 *
 *
 * @author Zihan Wang, Justin Chan
 */

public class Speaker extends User {
    // Last modified: Justin Chan
    private final HashMap<UUID, ArrayList<UUID>> eventsSpeaking = new HashMap<>();

    public Speaker(String username) {
        super(username);
    }

    @Override
    public boolean isOrganizer() {
        return false;
    }

    // Last modified: Justin Chan
    public HashMap<UUID, ArrayList<UUID>> getEventsSpeaking() { // Changed return type
        return eventsSpeaking;
    }

    // Last modified: Justin Chan

    /**
     * add EventID to the Speaker's Events
     * @param roomID the Room of the Event
     * @param eventID the Event to be added
     */
    public void addEvent(UUID roomID, UUID eventID){
        eventsSpeaking.putIfAbsent(roomID, new ArrayList<>());
        eventsSpeaking.get(roomID).add(eventID);
    }

    /**
     * add EventID from the Speaker's Events
     * @param roomID the Room of the Event
     * @param eventID the Event to be removed
     * @return true if it is removed successfully, otherwise return false
     */
    public boolean removeEvent(UUID roomID, UUID eventID){
        if (!eventsSpeaking.containsKey(roomID)) {
            return false;
        }
        if (!eventsSpeaking.get(roomID).contains(eventID)) {
            return false;
        }
        eventsSpeaking.get(roomID).remove(eventID);
        if (eventsSpeaking.get(roomID).isEmpty()) {
            eventsSpeaking.remove(roomID);
        }
        return true;
    }

    @Override
    public boolean isSpeaker() {
        return true;
    }

    @Override
    public boolean isAttendee() {
        return false;
    }

    @Override
    public String getStringType() {
        return "speaker";
    }
}
