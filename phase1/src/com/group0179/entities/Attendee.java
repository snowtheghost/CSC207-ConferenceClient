package com.group0179.entities;

import java.util.*;

/**
 * Represents an Attendee
 * @author Zihan Wang
 */
public class Attendee extends User {
    private final Map<UUID, ArrayList<UUID>> events;

    /**
     * Creates a new Attendee with a unique ID, a username,
     * no messages, no contacts and no reserved Events.
     * @param username The user's username.
     */
    public Attendee(String username){
        super(username);
        events = new HashMap<>();
    }

    /**
     * @return the Attendee's Events
     */
    public Map<UUID, ArrayList<UUID>> getEvents() {
        return events;
    }

    /**
     * Add all all the UUIDs of the Events in EventstoAdd to the Attendee's events except duplication.
     * @param eventID An Event to be added.
     * @param roomID the Room of the Event.
     * @return whether the Event be added successfully
     */
    public boolean addEvents(UUID roomID, UUID eventID){
        events.putIfAbsent(roomID, new ArrayList<>());
        if(events.get(roomID).contains(eventID))return false;
        events.get(roomID).add(eventID);
        return true;
    }

    /**
     * Remove the eventID from this.event.
     * @param eventID the Event object to be removed from this.events
     * @return true if it is successfully removed, and false if it failed.
     *
     * Last modified for bug fix: Justin Chan
     */
    public boolean removeReservedEvents(UUID roomID, UUID eventID){
        if (!events.containsKey(roomID)) {
            return false;
        }
        if (!events.get(roomID).contains(eventID)) {
            return false;
        }
        events.get(roomID).remove(eventID);
        if (events.get(roomID).isEmpty()) {
            events.remove(roomID);
        }
        return true;
    }

    @Override
    public boolean isOrganizer(){
        return false;
    }

    @Override
    public boolean isAttendee(){return true;}

    @Override
    public boolean isSpeaker() {
        return false;
    }

    @Override
    public String getStringType() {
        return "attendee";
    }
}