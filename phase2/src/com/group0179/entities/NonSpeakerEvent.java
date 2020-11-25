package com.group0179.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

/**
 * Represents a type of event without any speaker, like a party.
 * @author Tanuj Devjani
 */

public class NonSpeakerEvent implements Serializable, Comparable<NonSpeakerEvent>{
    /**
     * Constructor for subclass NonSpeaker_Event
     * @param eventTitle the title of the evnt
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     * @param capacity the capacity of the event
     *                 Precondition: startTime.before(endTime)
     *                 Precondition: startTime has the same date as endTime
     */
    private final String title;
    private final UUID eventID;
    private Calendar startTime;
    private Calendar endTime;
    private final ArrayList<UUID> attendeeIDs = new ArrayList<>();  // List of attendees by UUID
    private boolean isVipOnly = false;
    private int capacity;
    private int occupiedCapacity = 0;

    public NonSpeakerEvent(String eventTitle,  Calendar startTime, Calendar endTime, int capacity) {
        eventID = UUID.randomUUID();
        this.title = eventTitle;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
    }

    public int getCapacity() {return this.capacity;}

    public String getTitle() {
        return title;
    }

    public UUID getEventID() {
        return eventID;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public boolean getVipOnlyStatus(){ return this.isVipOnly; }

    public void setVipOnlyStatus(boolean status) { this.isVipOnly = status; }

    /**
     * @param startTime the start time of the event
     *
     * Precondition: the modified event does not overlap any existing events
     */
    public void setTime(Calendar startTime, Calendar endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ArrayList<UUID> getAttendeeIDs() {
        return attendeeIDs;
    }

    /**
     * This method adds all the UUIDs of the Attendees in attendeeToAdd to the Event attendees. Duplicate members will
     * not be added.
     *
     * @param attendeeID an Attendee to be added to the Event attendees.
     *                       Note that we take an ArrayList of Attendees and NOT UUIDs.
     *
     * @return the number of Attendees that were not added (as a result to an already present UUID)
     * TODO: We need to add the eventID to the attendee involved
     */
    public boolean addAttendee(UUID attendeeID) {
        if (attendeeIDs.contains(attendeeID) || this.capacity<=this.occupiedCapacity) {
            return false;
        }
        this.occupiedCapacity++;
        attendeeIDs.add(attendeeID);
        return true;
    }

    /**
     * This method the UUID of the Attendee attendeesToRemove from the Event attendees. Returns true if the UUID was
     * successfully removed, and false if the UUID was not present in the Event attendees.
     *
     * @param attendeeID an Attendee  to be removed from the Event attendees.
     *                         Note that we take an Attendee and NOT its UUID
     *
     * @return true if the Attendee was removed and false if the Attendee was not present in the first place
     */
    public boolean removeAttendee(UUID attendeeID) {
        this.occupiedCapacity--;
        return attendeeIDs.remove(attendeeID);
    }

    @Override
    public String toString() {
        return title + " at " + startTime.getTime() + " to " + endTime.getTime();
    }

    @Override
    public int compareTo(NonSpeakerEvent o) {
        if(this.getAttendeeIDs().size() > o.getAttendeeIDs().size()){
            return 1;
        } else if (this.getAttendeeIDs().size() < o.getAttendeeIDs().size()){
            return -1;
        } else {
            return 0;
        }
    }
}

