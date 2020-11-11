import java.util.*;

/**
 * Create rooms
 * Store rooms in a map by UUID
 * Get rooms by UUID
 *
 * Create events
 * Store events in existing rooms
 * Get events from rooms by UUID
 * Get all events from room
 *
 * Reschedule an event
 *
 * Sign up an attendee for an Event
 * Remove an attendee from an Event
 *
 * @author Justin Chan
 */

public class RoomManager {
    private final ArrayList<Room> rooms = new ArrayList<>();

    RoomManager() { }

    /**
     * @param roomID the UUID of the room
     * @return the room corresponding to the UUID
     *
     * Precondition: the roomID is a key in rooms
     */
    public Room getRoom(UUID roomID) {
        return getRoomIDToRoom().get(roomID);
    }

    /**
     * @return an ArrayList of all rooms in existence
     */
    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * @param eventID the eventID of the event
     * @return the event corresponding to the eventID
     *
     * Precondition: event must exist in one of the rooms
     */
    public Event getEvent(UUID eventID) {
        return getEventIDToEvent().get(eventID);
    }

    public ArrayList<Event> getEvents() {
        ArrayList<Event> events = new ArrayList<>();
        for (Room room : rooms) {
            events.addAll(room.getEvents());
        }
        return events;
    }

    /**
     * @param room the room that contains the desired events
     * @return an ArrayList of Events in the Room
     */
    public ArrayList<Event> getEventsFromRoom(Room room) {
        return room.getEvents();
    }

    /**
     * @param event the event storing the attendees
     * @return an ArrayList of AttendeeIDs that are attending the specified event in the specified room
     *
     * Precondition: roomID must exist and event must exist within the corresponding room
     */
    public ArrayList<UUID> getEventAttendeeIDs(Event event) {
        return event.getAttendeeIDs();
    }

    public HashMap<UUID, Room> getRoomIDToRoom() {
        HashMap<UUID, Room> RoomIDToRoom = new HashMap<>();
        for (Room room : rooms) {
            RoomIDToRoom.put(room.getRoomID(), room);
        }
        return RoomIDToRoom;
    }

    public HashMap<UUID, Event> getEventIDToEvent() {
        HashMap<UUID, Event> EventIDToEvent = new HashMap<>();
        for (Room room : rooms) {
            for (Event event : room.getEvents()) {
                EventIDToEvent.put(event.getEventID(), event);
            }
        }
        return EventIDToEvent;
    }

    public Room getEventRoom(Event event) {
        for (Room room : rooms) {
            for (Event comparisonEvent : room.getEvents()) {
                if (event.equals(comparisonEvent)) {
                    return room;
                }
            }
        }
        return new Room();
    }

    /**
     * @return the created room
     */
    public Room newRoom() {
        Room roomToCreate = new Room();
        rooms.add(roomToCreate);
        return roomToCreate;
    }

    /**
     * @param eventTitle the title of the event
     * @param speaker the speaker of the event
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     * @param room the room of the event
     * @return true if the event can be added and false if the event cannot be added. An event may not be added if
     * the time is out of bounds, is overlapping another event in the same room, or the speaker is giving a talk
     * at the same time.
     *
     * Preconditions: room exists
     */
    public boolean newEventValid(String eventTitle, Speaker speaker, Calendar startTime, Calendar endTime, Room room) {
        Event newEvent = new Event(eventTitle, speaker, startTime, endTime);
        for (UUID roomID : speaker.getEventsSpeaking().keySet()) {
            for (Event event : getRoom(roomID).getEvents()) {
                if (getRoom(roomID).eventOverlapping(newEvent, event)) {
                    return false;
                }
            }
        }
        return room.eventIsValid(newEvent);
    }

    /**
     * @param eventTitle the title of the event
     * @param speaker the speaker of the event
     * @param startTime the start time of the event
     * @param endTime the end time of the event
     * @param room the room of the room that the event should be added to
     * @return the UUID of the created event
     *
     * This method adds the newEvent to the specified room and the specified speaker.
     * Precondition: the event can be added to the room without conflict and the room exists
     */
    public Event newEvent(String eventTitle, Speaker speaker, Calendar startTime, Calendar endTime, Room room) {
        Event newEvent = new Event(eventTitle, speaker, startTime, endTime);
        room.addEvent(newEvent);
        speaker.addEvent(room, newEvent);
        return newEvent;
    }

    /**
     * Removes a desired Event from the list of events and all participants
     *
     * @param event the event to be removed
     * @return true if the event was removed or false if there was no such event in the schedule
     */
    public boolean removeEvent(UserManager um, Event event) {
        Room room = getEventRoom(event);
        for (UUID attendeeID : event.getAttendeeIDs()) {
            Attendee attendee = (Attendee) um.getUser(attendeeID);
            attendee.removeReservedEvents(room, event);
        }

        Speaker speaker = (Speaker) um.getUser(event.getSpeakerID());
        speaker.removeEvent(room, event);
        return room.removeEvent(event);
    }

    /**
     * @param attendee the attendee that is applying
     * @param event the event being applied for
     * @return true if the sign up was successful, or false if the attendee could not sign up (as they're already signed up)
     */
    public boolean addEventAttendee(Attendee attendee, Event event) {
        if (event.getAttendeeIDs().contains(attendee.getUserID())) {
            return false;
        }
        attendee.addEvents(getEventRoom(event), event);
        event.addAttendee(attendee);
        return true;
    }

    public boolean removeEventAttendee(Attendee attendee, Event event) {
        if (event.getAttendeeIDs().contains(attendee.getUserID())) {
            attendee.removeReservedEvents(getEventRoom(event), event);
            event.removeAttendee(attendee);
            return true;
        }
        return false;
    }

    /**
     * @param event the event to be rescheduled
     * @param startTime the new start time
     * @param endTime the new end time
     * @return true if the event could be rescheduled and false if no changes were made due to failed reschedule
     */
    public boolean rescheduleEvent(UserManager um, Event event, Calendar startTime, Calendar endTime) {
        Room room = getEventRoom(event);
        room.removeEvent(event);
        if (newEventValid(event.getTitle(), (Speaker) um.getUser(event.getSpeakerID()), startTime, endTime, room)) {
            event.setTime(startTime, endTime);
            room.addEvent(event);
            return true;
        }
        room.addEvent(event);
        return false;
    }

    public ArrayList<Event> getEventsFromRoom(int roomNumber) {
        return getEventsFromRoom(getRooms().get(roomNumber));
    }

    public Room getRoom(int roomNumber) {
        return getRooms().get(roomNumber);
    }


}
