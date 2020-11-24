package com.group0179.entities;

import java.util.Calendar;

/**
 * Represents a type of event with multiple speakers, like a panel discussion.
 * @author Tanuj Devjani
 */

public class MultiSpeaker_Event extends Event {
    /**
     * Constructor for subclass MultiSpeaker_Event
     * @param eventTitle the title of the event
     * @param speakerName the name of the speaker(s)
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     * @param capacity the capacity of the event
     *                 Precondition: startTime.before(endTime)
     *                 Precondition: startTime has the same date as endTime
     */
    public MultiSpeaker_Event(String eventTitle, String speakerName, Calendar startTime, Calendar endTime, int capacity){
        super(eventTitle, speakerName, startTime, endTime, capacity);

    }

}
