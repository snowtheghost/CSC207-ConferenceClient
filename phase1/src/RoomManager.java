import java.util.*;

/**
 * Create rooms
 * Store rooms in a map by UUID
 * Get rooms by UUID
 *
 * Create events
 * Store events in existing rooms
 * Get events from rooms by UUID
 *
 * @author Justin Chan
 */

public class RoomManager {
    private final HashMap<UUID, Room> rooms = new HashMap<>();

    RoomManager() { }

    /**
     * @return the UUID of the created room
     */
    public UUID newRoom() {
        Room roomToCreate = new Room();
        rooms.put(roomToCreate.getRoomID(), roomToCreate);
        return roomToCreate.getRoomID();
    }

    /**
     *
     * @param roomID the UUID of the room
     * @return the room corresponding to the UUID
     *
     * Precondition: the roomID is a key in rooms
     */
    public Room getRoom(UUID roomID) {
        return rooms.get(roomID);
    }

    /**
     * @param eventTitle the title of the event
     * @param speaker the speaker of the event
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     * @param roomID the roomID of the event
     * @return true if the event can be added and false if the event cannot be added. An event may not be added if
     * the time is out of bounds, is overlapping another event in the same room, or the speaker is giving a talk
     * at the same time.
     *
     * Preconditions: roomID exists
     */
    public boolean newEventValid(String eventTitle, Speaker speaker, Calendar startTime, Calendar endTime, UUID roomID) {
        Event newEvent = new Event(eventTitle, speaker, startTime, endTime);
        for (UUID eventID : speaker.getEventsSpeaking()) {
            // TODO: write the false condition. Dependent on Speaker which has not been correctly implemented at the moment.
            if (!false) { // if condition involving speaker and overlap
                return false;
            }
        }
        return true;
    }

    /**
     * @param eventTitle the title of the event
     * @param speaker the speaker of the event
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     * @param roomID the roomID of the room that the event should be added to
     * @return the UUID of the created event
     *
     * This method adds the newEvent to the specified room and the specified speaker.
     * Precondition: the event can be added to the room and the roomID exists
     */
    public UUID newEvent(String eventTitle, Speaker speaker, Calendar startTime, Calendar endTime, UUID roomID) {
        Event newEvent = new Event(eventTitle, speaker, startTime, endTime);
        rooms.get(roomID).addEvent(newEvent);
        speaker.addEvent(newEvent);
        return newEvent.getEventID();
    }

    /**
     *
     * @param roomID the roomID for the room that this event belongs to
     * @param eventID the eventID of the event
     * @return the event corresponding to the eventID
     *
     * Precondition: roomID must exist and eventID must exist within the corresponding room
     */
    public Event getEvent(UUID roomID, UUID eventID) {
        return rooms.get(roomID).getEvent(eventID);
    }

    /**
     *
     * @param roomID the roomID for the room that this event belongs to
     * @param eventID the eventID of the event
     * @return an ArrayList of AttendeeIDs that are attending the specified event in the specified room
     *
     * Precondition: roomID must exist and eventID must exist within the corresponding room
     */
    public ArrayList<UUID> getEventAttendeeIDs(UUID roomID, UUID eventID) {
        return getEvent(roomID, eventID).getAttendeeIDs();
    }
}
