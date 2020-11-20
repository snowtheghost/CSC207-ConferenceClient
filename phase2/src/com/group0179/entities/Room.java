package com.group0179.entities;

import java.io.Serializable;
import java.util.*;

/**
 * Represents a Room
 *
 * Store UUID to events
 *
 * Get Event at given time
 * Get Events of a given title
 * Get Events by the same Speaker
 *
 * Add Event to schedule
 * Remove Event from schedule
 *
 * Assumptions: Event starts and ends on the same day
 *
 * @author Justin Chan
 */

public class Room implements Serializable {
    private final UUID roomID;
    private final ArrayList<Event> events = new ArrayList<>();

    public Room() {
        this.roomID = UUID.randomUUID();
    }

    public UUID getRoomID() {
        return roomID;
    }

    public ArrayList<Event> getEvents() {
        return events;
    }

    public HashMap<UUID, Event> getEventIDToEvent() {
        HashMap<UUID, Event> eventIDToEvent = new HashMap<>();
        for (Event event : events) {
            eventIDToEvent.put(event.getEventID(), event);
        }
        return eventIDToEvent;
    }

    public HashMap<Calendar, Event> getTimeSchedule() {
        HashMap<Calendar, Event> timeSchedule = new HashMap<>();
        for (Event event : events) {
            timeSchedule.putIfAbsent(event.getStartTime(), event);
        }
        return timeSchedule;
    }

    public HashMap<String, ArrayList<Event>> getSpeakerNameSchedule() {
        HashMap<String, ArrayList<Event>> speakerNameSchedule = new HashMap<>();
        for (Event event : events) {
            speakerNameSchedule.putIfAbsent(event.getSpeakerName(), new ArrayList<>());
            speakerNameSchedule.get(event.getSpeakerName()).add(event);
        }
        return speakerNameSchedule;
    }

    public HashMap<String, ArrayList<Event>> getTitleSchedule() {
        HashMap<String, ArrayList<Event>> titleSchedule = new HashMap<>();
        for (Event event : events) {
            titleSchedule.putIfAbsent(event.getTitle(), new ArrayList<>());
            titleSchedule.get(event.getTitle()).add(event);
        }
        return titleSchedule;
    }

    /**
     * Returns the Events that start between the specified start and end times
     *
     * @param startTime the lower boundary of the search interval
     * @param endTime the upper boundary of the search interval
     * @return an ArrayList of Events within the given time boundary
     *
     * Precondition: startTime {@literal <=} endTime
     */
    public ArrayList<Event> getEventsByTime(GregorianCalendar startTime, GregorianCalendar endTime) {
        ArrayList<Event> eventsInInterval = new ArrayList<>();
        for (Calendar time : getTimeSchedule().keySet()) {
            if (!time.before(startTime) && time.before(endTime)) { // One sided boundary acceptance
                eventsInInterval.add(getTimeSchedule().get(time));
            }
        }
        return eventsInInterval;
    }

    public ArrayList<Event> getEventsByTitle(String title) {
        ArrayList<Event> eventsWithTitle = getTitleSchedule().get(title);
        if (eventsWithTitle == null) {
            eventsWithTitle = new ArrayList<>();
        }
        return eventsWithTitle;
    }

    public ArrayList<Event> getEventsBySpeakerID(Speaker speaker) {
        ArrayList<Event> eventsBySpeakerID = getSpeakerNameSchedule().get(speaker.getUsername());
        if (eventsBySpeakerID == null) {
            eventsBySpeakerID = new ArrayList<>();
        }
        return eventsBySpeakerID;
    }

    /**
     * @return the list of eventIDs in the schedule
     */
    public ArrayList<UUID> getEventIDs() {
        return new ArrayList<>(getEventIDToEvent().keySet());
    }

    public Event getEvent(UUID eventID) {
        return getEventIDToEvent().get(eventID);
    }

    public boolean eventOverlapping(Event newEvent, Event comparisonEvent) {
        Calendar[] newEventTimes = { newEvent.getStartTime(), newEvent.getEndTime() };
        Calendar[] comparisonEventTimes = { comparisonEvent.getStartTime(), comparisonEvent.getEndTime()};

        // Check for exact same times
        if (newEvent.getStartTime().equals(comparisonEvent.getStartTime()) || newEvent.getEndTime().equals(comparisonEvent.getEndTime())) {
            return true;
        }

        for (int i = 0; i <= 1; i++) {
            if ((newEventTimes[i].after(comparisonEventTimes[0]) && newEventTimes[i].before(comparisonEventTimes[1])) ||
                    (comparisonEventTimes[i].after(newEventTimes[0]) && comparisonEventTimes[i].before(newEventTimes[1]) )) {
                return true;
            }
        }
        return false;
    }

    private boolean eventOutOfBounds(Event newEvent) {
        int startHour = newEvent.getStartTime().get(Calendar.HOUR_OF_DAY);
        int endMinute = newEvent.getEndTime().get(Calendar.MINUTE);
        int endHour = newEvent.getEndTime().get(Calendar.HOUR_OF_DAY);
        return (startHour < 9 || (endHour == 17 && endMinute != 0) || endHour > 17);


    }

    public boolean eventIsValid(Event newEvent) {
        if (eventOutOfBounds(newEvent)) {
            return false;
        }

        for (Calendar time : getTimeSchedule().keySet()) {
            Event comparisonEvent = getTimeSchedule().get(time);
            if (eventOverlapping(newEvent, comparisonEvent)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds an Event to all three schedule types and returns true if the Event is valid, and returns false if the Event
     * could not be added due to overlap or time.
     *
     * @param eventToAdd the event to be added
     * @return true if the event was added or false if there was a conflict
     *
     * Precondition: eventToAdd.getStartTime() is in the same day as eventToAdd.getEndTime()
     */
    public boolean addEvent(Event eventToAdd) {
        if (eventIsValid(eventToAdd)) {
            events.add(eventToAdd);
            return true;
        }
        return false;
    }

    /**
     * Removes a desired Event from the list of events.
     *
     * @param eventToRemove the event to be removed
     * @return true if the event was removed or false if there was no such event in the schedule
     */
    public boolean removeEvent(Event eventToRemove) {
        if (events.contains(eventToRemove)) {
            events.remove(eventToRemove);
            return true;
        }
        return false;
    }
}
