package com.group0179.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

/**
 * Represents an Event
 *
 * Responsibilities:
 * Store title
 * Get title
 *
 * Store eventID
 * Get eventID
 *
 * Store time
 * Get time
 *
 * Store AttendeeIDs
 * Get AttendeesIDs
 * Add AttendeeIDs
 * Remove AttendeeID
 *
 * Store SpeakerName
 * Get SpeakerName
 *
 * @author Justin Chan
 */

public class Event implements Comparable<Event>, Serializable {


    /**
     * Constructor for subclass NonSpeaker_Event
     * @param eventTitle the title of the evnt
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     * @param capacity the capacity of the event
     *                 Precondition: startTime.before(endTime)
     *                 Precondition: startTime has the same date as endTime
     */

    /**
     * There are 3 constructors to account for the 3 different possible types of events:
     * single speaker event(like a talk), multispeakerevent(like a panel), no speaker(like a party)
     * Depending on user input, the relevant Event would be initiated.
     */
    private final String title;
    private final UUID eventID;
    private Calendar startTime;
    private Calendar endTime;
    private final ArrayList<UUID> attendeeIDs = new ArrayList<>();  // List of attendees by UUID
    private boolean isVipOnly = false;
    private int capacity;
    private int occupiedCapacity = 0;
    private ArrayList<String> speakers;
    private ArrayList<UUID> eventRequests = new ArrayList<>();

    public Event(String eventTitle, String speakerName,  Calendar startTime, Calendar endTime, int capacity) {
        eventID = UUID.randomUUID();
        this.title = eventTitle;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
        this.speakers = new ArrayList<String>();
        this.speakers.add(speakerName);
    }
    public Event(String eventTitle,  Calendar startTime, Calendar endTime, int capacity){
        eventID = UUID.randomUUID();
        this.title = eventTitle;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
        this.speakers = new ArrayList<String>();
    }
    public Event(String eventTitle, ArrayList<String> speakers, Calendar startTime, Calendar endTime, int capacity){
        eventID = UUID.randomUUID();
        this.title = eventTitle;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
        this.speakers = speakers;
    }
    public int getCapacity() {return this.capacity;}
    public String getSpeakerName(){
        if (this.speakers.isEmpty()) return "";
        return this.speakers.get(0);
    }
    public ArrayList<String> getSpeakerNames(){
        return this.speakers;
    }
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

    /**
     * @return Vip status of event
     */
    public boolean getVipOnlyStatus(){ return this.isVipOnly; }

    /**
     * Sets event vip status
     * @param status whether the event is vip only
     */
    public void setVipOnlyStatus(boolean status) { this.isVipOnly = status; }

    /**
     * Adds request uuid to event
     * @param requestUUID the request's UUID
     */
    public void addRequest(UUID requestUUID){
        this.eventRequests.add(requestUUID);
    }

    /**
     * @param requestUUID request uuid to be removed
     * @return returns whether the event has the uuid to be removed.
     */
    public boolean removeRequest(UUID requestUUID){
        if (this.eventRequests.contains(requestUUID)){
            this.eventRequests.remove(requestUUID);
            return true;
        }
        return false;
    }

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

    /**
     * Returns a string representation of the details of an event.
     */
    public String toString() {
        return title + " at " + startTime.getTime() + " to " + endTime.getTime();
    }

    /**
     * Compares events to determine the event with the higher number of attendees
     * @param o
     * @return 1 if this is the bigger event, -1 if smaller, 0 if same size.
     */
    public int compareTo(Event o) {
        if(this.getAttendeeIDs().size() > o.getAttendeeIDs().size()){
            return 1;
        } else if (this.getAttendeeIDs().size() < o.getAttendeeIDs().size()){
            return -1;
        } else { return 0;
        }
    }

    /**
     * @return the event requests uuids
     */
    public ArrayList<UUID> getEventRequests(){
        return this.eventRequests;
    }
}


