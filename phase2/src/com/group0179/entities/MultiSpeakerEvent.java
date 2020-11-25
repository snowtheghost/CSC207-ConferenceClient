package com.group0179.entities;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

/**
 * Represents a type of event with multiple speakers, like a panel discussion.
 * @author Tanuj Devjani
 */

public class MultiSpeakerEvent extends NonSpeakerEvent {
    private List<String> speakerNames;
    /**
     * Constructor for subclass MultiSpeaker_Event
     * @param eventTitle the title of the event
     * @param speakerNames a list containing the names of the speakers
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     * @param capacity the capacity of the event
     *                 Precondition: startTime.before(endTime)
     *                 Precondition: startTime has the same date as endTime
     */

    public MultiSpeakerEvent(String eventTitle, List<String> speakerNames, Calendar startTime, Calendar endTime, int capacity){
        super(eventTitle, startTime, endTime, capacity);
        this.speakerNames = speakerNames;
    }

    public List<String> getSpeakerNames(){
        return this.speakerNames;
    }
    }


