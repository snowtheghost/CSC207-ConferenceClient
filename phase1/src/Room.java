import java.util.*;

/**
 * Represents a Room
 *
 * Store schedule of time -> Event
 *
 * Get Event at given time
 * Get Events of a given title
 * Get Events by the same Speaker
 *
 * Add Event to schedule
 * Remove Event from schedule
 *
 * @author Justin Chan
 */

public class Room {
    private final UUID roomID;
    private final HashMap<Calendar, Event> schedule = new HashMap<>();

    public Room() {
        this.roomID = UUID.randomUUID();
    }

    public UUID getRoomID() {
        return roomID;
    }

    public HashMap<Calendar, Event> getSchedule() {
        return schedule;
    }

    public HashMap<UUID, ArrayList<Event>> getSpeakerIDSchedule() {
        HashMap<UUID, ArrayList<Event>> speakerIDSchedule = new HashMap<>();
        for (Event event : schedule.values()) {
            speakerIDSchedule.putIfAbsent(event.getSpeakerID(), new ArrayList<Event>());
            speakerIDSchedule.get(event.getSpeakerID()).add(event);
        }
        return speakerIDSchedule;
    }

    public HashMap<String, ArrayList<Event>> getTitleSchedule() {
        HashMap<String, ArrayList<Event>> titleSchedule = new HashMap<>();
        for (Event event : schedule.values()) {
            titleSchedule.putIfAbsent(event.getTitle(), new ArrayList<Event>());
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
     * Precondition: startTime <= endTime
     */
    public ArrayList<Event> getEventsByTime(GregorianCalendar startTime, GregorianCalendar endTime) {
        ArrayList<Event> eventsInInterval = new ArrayList<>();
        for (Calendar time : schedule.keySet()) {
            if (time.after(startTime) && time.before(endTime)) {
                eventsInInterval.add(schedule.get(time));
            }
        }
        return eventsInInterval;
    }

    public ArrayList<Event> getEventsByTitle(String title) {
        ArrayList<Event> eventsWithTitle = getTitleSchedule().get(title);
        if (eventsWithTitle == null) {
            eventsWithTitle = new ArrayList<Event>();
        }
        return eventsWithTitle;
    }

    public ArrayList<Event> getEventsBySpeakerID(Speaker speaker) {
        ArrayList<Event> eventsBySpeakerID = getSpeakerIDSchedule().get(speaker.getUserID());
        if (eventsBySpeakerID == null) {
            eventsBySpeakerID = new ArrayList<Event>();
        }
        return eventsBySpeakerID;
    }

    private boolean eventIsOverlapping(Event newEvent, Event comparisonEvent) {
        Calendar[] newEventTimes = { newEvent.getStartTime(), newEvent.getEndTime() };
        Calendar[] comparisonEventTimes = { comparisonEvent.getStartTime(), comparisonEvent.getEndTime()};

        for (int i = 0; i <= 1; i++) {
            if ((newEventTimes[i].after(comparisonEventTimes[0]) && newEventTimes[i].before(comparisonEventTimes[1]) ||
                    comparisonEventTimes[i].after(newEventTimes[0]) && comparisonEventTimes[i].before(newEventTimes[1]) )) {
                return false;
            }
        }
        return true;
    }

    /**
     * Adds an Event to all three schedule types and returns true if the Event is valid, and returns false if the Event
     * could not be added.
     *
     * @param eventToAdd the event to be added
     * @return true if the event was added or false if there was a conflict
     */
    public boolean addEvent(Event eventToAdd) {
        for (Calendar time : schedule.keySet()) {
            Event comparisonEvent = schedule.get(time);
            if (eventIsOverlapping(eventToAdd, comparisonEvent)) {
                return false;
            }
        }

        schedule.put(eventToAdd.getStartTime(), eventToAdd);
        return true;
    }

    /**
     * Removes a desired Event from the list of events.
     *
     * @param eventToRemove the event to be removed
     * @return true if the event was removed or false if there was no such event in the schedule
     */
    public boolean removeEvent(Event eventToRemove) {
        for (Calendar time : schedule.keySet()) {
            if (schedule.get(time).getEventID().equals(eventToRemove.getEventID())) {
                schedule.remove(time);
                return true;
            }
        }
        return false;
    }
}
