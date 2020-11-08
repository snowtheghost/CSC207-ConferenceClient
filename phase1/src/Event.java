import java.util.*;

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
 * Store SpeakerID
 * Get SpeakerID
 *
 * @author Justin Chan
 */

public class Event {
    private final String title;
    private final UUID eventID;
    private Calendar startTime;
    private Calendar endTime;
    private final UUID speakerID;  // The UUID of the Speaker
    private final ArrayList<UUID> attendeeIDs = new ArrayList<>();  // List of attendees by UUID

    /**
     * Event constructor
     *
     * @param eventTitle the title of the event
     * @param speaker the speaker of the event
     * @param startTime the startTime of the event using GregorianCalendar object.
     * @param endTime the time of the event using GregorianCalendar object.
     *
     * Precondition: startTime.before(endTime)
     * Precondition: startTime has the same date as endTime
     *                TODO: We need to add the eventID to the Speaker involved
     */
    Event(String eventTitle, Speaker speaker, Calendar startTime, Calendar endTime) {
        eventID = UUID.randomUUID();
        this.title = eventTitle;
        this.speakerID = speaker.getUserID();
        this.startTime = startTime;
        this.endTime = endTime;
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
     * @param attendeeToAdd an array of Attendee objects to be added to the Event attendees.
     *                       Note that we take an ArrayList of Attendees and NOT UUIDs.
     *
     * @return the number of Attendees that were not added (as a result to an already present UUID)
     * TODO: We need to add the eventID to the attendee involved
     */
    public boolean addAttendee(Attendee attendeeToAdd) {
        if (attendeeIDs.contains(attendeeToAdd.getUserID())) {
            return false;
        }
        attendeeIDs.add(attendeeToAdd.getUserID());
        return true;
    }

    /**
     * This method the UUID of the Attendee attendeesToRemove from the Event attendees. Returns true if the UUID was
     * successfully removed, and false if the UUID was not present in the Event attendees.
     *
     * @param attendeeToRemove an Attendee object to be removed from the Event attendees.
     *                         Note that we take an Attendee and NOT its UUID
     *
     * @return true if the Attendee was removed and false if the Attendee was not present in the first place
     */
    public boolean removeAttendee(Attendee attendeeToRemove) {
        return attendeeIDs.remove(attendeeToRemove.getUserID());
    }

    public UUID getSpeakerID() {
        return speakerID;
    }

    @Override
    public String toString() {
        return "Event: " + title + '\n' + "Time: " + startTime.getTime() + " to " + endTime.getTime();
    }
}
