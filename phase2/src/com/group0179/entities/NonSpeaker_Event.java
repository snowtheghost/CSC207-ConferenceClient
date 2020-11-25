package com.group0179.entities;

import java.util.Calendar;

/**
 * Represents a type of event without any speaker, like a party.
 * @author Tanuj Devjani
 */

public class NonSpeaker_Event extends Event {
    /**
     * Constructor for subclass NonSpeaker_Event
     * @param eventTitle the title of the evnt
     * @param speakerName the name of the speaker
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     * @param capacity the capacity of the event
     *                 Precondition: startTime.before(endTime)
     *                 Precondition: startTime has the same date as endTime
     */
    public NonSpeaker_Event(String eventTitle, String speakerName, Calendar startTime, Calendar endTime, int capacity) {
        super(eventTitle, speakerName, startTime, endTime, capacity);
    }
}
