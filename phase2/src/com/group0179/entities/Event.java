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

public class Event extends NonSpeakerEvent {
    private final String speakerName;  // The UUID of the Speaker

    /**
     * Event constructor
     *
     * @param eventTitle  the title of the event
     * @param speakerName the speaker of the event
     * @param startTime   the startTime of the event using GregorianCalendar object.
     * @param endTime     the time of the event using GregorianCalendar object.
     *                    <p>
     *                    Precondition: startTime.before(endTime)
     *                    Precondition: startTime has the same date as endTime
     */
    public Event(String eventTitle, String speakerName, Calendar startTime, Calendar endTime, int capacity) {
        super(eventTitle, startTime, endTime, capacity);
        this.speakerName = speakerName;
    }

    public String getSpeakerName() {
        return speakerName;
    }
}

