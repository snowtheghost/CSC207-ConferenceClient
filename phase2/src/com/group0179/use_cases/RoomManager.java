package com.group0179.use_cases;

import com.group0179.entities.Event;
import com.group0179.entities.Room;

import java.io.Serializable;
import java.util.*;

/**
 * Create rooms
 * Store rooms in a map by UUID
 * Get rooms by UUID
 * <p>
 * Create events
 * Store events in existing rooms
 * Get events from rooms by UUID
 * Get all events from room
 * <p>
 * Reschedule an event
 * <p>
 * Sign up an attendee for an Event
 * Remove an attendee from an Event
 *
 * @author Justin Chan
 */

public class RoomManager implements Serializable {
    private final ArrayList<Room> rooms = new ArrayList<>();

    public RoomManager() {
    }

    /**
     * @param roomID the UUID of the room
     * @return the room corresponding to the UUID
     * <p>
     * Precondition: the roomID is a key in rooms
     */
    private Room getRoom(UUID roomID) {
        return getRoomIDToRoom().get(roomID);
    }

    private Room getRoom(int roomNumber) {
        return getRooms().get(roomNumber);
    }

    private Room getEventRoom(Event event) {
        for (Room room : rooms) {
            for (Event comparisonEvent : room.getEvents()) {
                if (event.equals(comparisonEvent)) {
                    return room;
                }
            }
        }
        return new Room(30);
    }

    /**
     * Takes in a room number and returns its capacity info.
     * @param roomNumber the room number of the desired room
     * @return [room capacity, remaining capacity]
     */
    public ArrayList<Integer> getRoomCapacity(int roomNumber){
        int totalCapacity = this.getRoom(roomNumber).getRoomCapcity();
        int usedCapacity = 0;
        for (Event event: this.getRoom(roomNumber).getEvents()){
            usedCapacity = usedCapacity + event.getCapacity();
        }
        ArrayList<Integer> info = new ArrayList<>();
        info.add(totalCapacity); info.add(totalCapacity-usedCapacity);
        return info;
    }

    /**
     * @param eventID the eventID of the event
     * @return the event corresponding to the eventID
     * <p>
     * Precondition: event must exist in one of the rooms
     */
    private Event getEvent(UUID eventID) {
        return getEventIDToEvent().get(eventID);
    }

    private Event getEvent(int roomNumber, int eventNumber) {
        return getEventsFromRoom(roomNumber).get(eventNumber);
    }

    private Event getEventFromRoom(int roomNumber, int eventNumber) {
        return getEventsFromRoom(roomNumber).get(eventNumber);
    }

    private ArrayList<Room> getRooms() {
        return rooms;
    }

    private ArrayList<Event> getEvents() {
        ArrayList<Event> events = new ArrayList<>();
        for (Room room : rooms) {
            events.addAll(room.getEvents());
        }
        return events;
    }

    private ArrayList<Event> getEventsFromRoom(Room room) {
        return room.getEvents();
    }

    private ArrayList<Event> getEventsFromRoom(int roomNumber) {
        return getEventsFromRoom(getRooms().get(roomNumber));
    }

    private ArrayList<Event> eventIDsToEvents(ArrayList<UUID> eventIDs) {
        ArrayList<Event> events = new ArrayList<>();
        for (UUID eventID : eventIDs) {
            events.add(getEvent(eventID));
        }
        return events;
    }

    /**
     * @param eventID the event storing the attendees
     * @return an ArrayList of AttendeeIDs that are attending the specified event in the specified room
     * <p>
     * Precondition: roomID must exist and event must exist within the corresponding room
     */
    public ArrayList<UUID> getEventAttendeeIDs(UUID eventID) {
        Event event = getEvent(eventID);
        return event.getAttendeeIDs();
    }

    /**
     * @return a HashMap where key is roomID and value is Room
     */
    private HashMap<UUID, Room> getRoomIDToRoom() {
        HashMap<UUID, Room> RoomIDToRoom = new HashMap<>();
        for (Room room : rooms) {
            RoomIDToRoom.put(room.getRoomID(), room);
        }
        return RoomIDToRoom;
    }

    private HashMap<UUID, Event> getEventIDToEvent() {
        HashMap<UUID, Event> EventIDToEvent = new HashMap<>();
        for (Room room : rooms) {
            for (Event event : room.getEvents()) {
                EventIDToEvent.put(event.getEventID(), event);
            }
        }
        return EventIDToEvent;
    }

    /**
     * @return the number of rooms
     */
    public int getNumRooms() {
        return rooms.size();
    }

    /**
     * @return the number of Events
     */
    public int getNumEvents() {
        return getEvents().size();
    }

    /**
     * @param roomNumber the room number of the room
     * @return the number of Events in the room
     */
    public int getNumEventsInRoom(int roomNumber) {
        return getEventsFromRoom(roomNumber).size();
    }

    /**
     * @return an Arraylist of Event ID.
     */
    public ArrayList<UUID> getEventIDs() {
        ArrayList<UUID> eventIDs = new ArrayList<>();
        for (Event event : getEvents()) {
            eventIDs.add(event.getEventID());
        }
        return eventIDs;
    }

    /**
     * Create a new Room
     * @param capacity the capacity of the new room.
     */
    public void newRoom(int capacity) {
        Room roomToCreate = new Room(capacity);
        rooms.add(roomToCreate);
    }

    /**
     * @param eventTitle  the title of the event
     * @param speakerName the speaker of the event
     * @param startTime   the start time of the event
     * @param endTime     the end time of the event
     * @param roomNumber  the room of the event
     * @return true if the event can be added and false if the event cannot be added. An event may not be added if
     * the time is out of bounds, is overlapping another event in the same room, or the speaker is giving a talk
     * at the same time.
     * <p>
     * Preconditions: room exists
     */
    public boolean newEventValid(String eventTitle, String speakerName, Calendar startTime, Calendar endTime, int roomNumber, UserManager um) {
        Room room = getRoom(roomNumber);

        Event newEvent = new Event(eventTitle, speakerName, startTime, endTime, 0);
        for (UUID existingEventID : um.getSpeakerEventIDs(speakerName)) {
            if (room.eventOverlapping(newEvent, getEvent(existingEventID))) { // TODO: Method does not require room!
                return false;
            }
        }
        return room.eventIsValid(newEvent);
    }

    /**
     * @param eventTitle  the title of the event
     * @param speakerName the speaker of the event
     * @param startTime   the start time of the event
     * @param endTime     the end time of the event
     * @param roomNumber  the roomNumber of the room that the event should be added to
     * @param capacity the events capacity
     * @return the UUID of the created event
     * <p>
     * This method adds the newEvent to the specified roomNumber and the specified speakerName.
     * Precondition: the event can be added to the roomNumber without conflict and the roomNumber exists
     */
    public UUID newEvent(String eventTitle, String speakerName, Calendar startTime, Calendar endTime, int roomNumber, UserManager um, int capacity) {
        Room room = getRoom(roomNumber);
        Event newEvent = new Event(eventTitle, speakerName, startTime, endTime, capacity);
        room.addEvent(newEvent);
        um.speakerAddEvent(speakerName, room.getRoomID(), newEvent.getEventID());
        return newEvent.getEventID();
    }

    /**
     * Takes in a room uuid and a vip only status and changes the status of the event.
     * @param isVipOnly whether the event is vip only
     * @param eventId the uuid of the event
     */
    public void updateVipStatus(boolean isVipOnly, UUID eventId){
        this.getEvent(eventId).setVipOnlyStatus(isVipOnly);
    }
    /**
     * @param roomNumber  the room that the event is in
     * @param eventNumber the event to be rescheduled
     * @param startTime   the new start time
     * @param endTime     the new end time
     * @return true if the event could be rescheduled and false if no changes were made due to failed reschedule
     */
    public boolean rescheduleEvent(UserManager um, int roomNumber, int eventNumber, Calendar startTime, Calendar endTime) {
        Event event = getEventFromRoom(roomNumber, eventNumber);
        if (newEventValid(event.getTitle(), event.getSpeakerName(), startTime, endTime, roomNumber, um)) {
            event.setTime(startTime, endTime);
            return true;
        }
        return false;
    }

    /**
     * Removes a desired Event from the list of events and all participants
     *
     * @param eventNumber the event to be removed
     * @return true if the event was removed or false if there was no such event in the schedule
     */
    public boolean removeEvent(UserManager um, int roomNumber, int eventNumber) {
        Event event = getEvent(roomNumber, eventNumber);

        Room room = getEventRoom(event);
        for (UUID attendeeID : event.getAttendeeIDs()) {
            um.attendeeRemoveEvent(attendeeID, room.getRoomID(), event.getEventID());
        }

        um.speakerRemoveEvent(event.getSpeakerName(), room.getRoomID(), event.getEventID());
        return room.removeEvent(event);
    }
    /**
     * Returns a list of 5 events that are most popular not in order
     *
    * @return a list containing the titles of the 5 most popular events.
    * */
    public Map<String, Integer> getTop5MostPopularEvents(){
        Map<String, Integer> mostPopularEventAsTitles = new HashMap<>();
        List<Event> events = this.getEvents();
        events.sort(Collections.reverseOrder());
        int rangeOfValues = Math.min(events.size(), 5);
        for(int i = 0; i < rangeOfValues; i++){
            mostPopularEventAsTitles.put(events.get(i).getTitle(), events.get(i).getAttendeeIDs().size());
        }
        return mostPopularEventAsTitles;
    }

    /**
     * @param attendeeID the attendee that is applying
     * @param eventID    the eventID being applied for
     * @return true if the sign up was successful, or false if the attendee could not sign up (as they're already signed up)
     */
    public boolean addEventAttendee(UUID attendeeID, UUID eventID, UserManager um, boolean isVip) {
        Event event = getEvent(eventID);

        if (event.getAttendeeIDs().contains(attendeeID) || (event.getVipOnlyStatus()&&!isVip)) {
            return false;
        }

        if (event.addAttendee(attendeeID)){
            um.attendeeAddEvent(attendeeID, getEventRoom(event).getRoomID(), eventID);
            return true;
        }
        return false;
    }

    /**
     * @param attendeeID the User ID of the Attendee
     * @param roomNumber the room number of the room
     * @param eventNumber the event number of the event
     * @param um User Manager
     * @param isVip true if the Attendee is VIP, otherwise false
     * @return true if the event add the Attendee successfully, otherwise return false.
     */
    public boolean addEventAttendee(UUID attendeeID, int roomNumber, int eventNumber, UserManager um, boolean isVip) {
        Event event = getEvent(roomNumber, eventNumber);

        if ((event.getVipOnlyStatus()&&!isVip)) return false;

        if (event.getAttendeeIDs().contains(attendeeID)) {
            return false;
        }

        um.attendeeAddEvent(attendeeID, getEventRoom(event).getRoomID(), event.getEventID());
        event.addAttendee(attendeeID);
        return true;
    }

    /**
     * @param attendeeID the User ID of the Attendee
     * @param roomNumber the room number of the room
     * @param eventNumber the event number of the event
     * @param um User Manager
     * @return true if the event remove the Attendee successfully, otherwise return false.
     */
    public boolean removeEventAttendee(UUID attendeeID, int roomNumber, int eventNumber, UserManager um) {
        Event event = getEvent(roomNumber, eventNumber);

        if (event.getAttendeeIDs().contains(attendeeID)) {
            um.attendeeRemoveEvent(attendeeID, getEventRoom(event).getRoomID(), event.getEventID());
            event.removeAttendee(attendeeID);
            return true;
        }
        return false;
    }

    /**
     * @param um User Manager
     * @param speakerName the name of the Speaker
     * @return a string including the Speaker's Events
     */
    public String stringEventsOfSpeaker(UserManager um, String speakerName) {
        StringBuilder s = new StringBuilder("Events by Speaker " + speakerName + ": \n");
        ArrayList<UUID> eventIDs = um.getSpeakerEventIDs(speakerName);
        ArrayList<Event> events = eventIDsToEvents(eventIDs);

        for (int eventNumber = 0; eventNumber < events.size(); eventNumber++) {
            Event event = events.get(eventNumber);
            s.append("(").append(eventNumber + 1).append(") ").append(event.toString()).append("\n");
        }
        return s.toString();
    }

    /**
     * @param roomNumber the room number
     * @return a string including the Room's Events.
     */
    public String stringEventsOfRoom(int roomNumber) {
        StringBuilder s = new StringBuilder("Events in Room " + (roomNumber + 1) + ": \n");
        ArrayList<Event> events = getEventsFromRoom(roomNumber);
        for (int i = 0; i < events.size(); i++) {
            Event event = events.get(i);
            int eventNumber = i + 1;
            s.append("(").append(eventNumber).append(") ").append(event.toString()).append("\n");
        }
        return s.toString();
    }

    /**
     * @param roomNumber the room number
     * @return a list of events as string
     */
    public ArrayList<String> getEventsOfRoom(int roomNumber) {
        ArrayList<Event> events = getEventsFromRoom(roomNumber);
        ArrayList<String> eventsAsString = new ArrayList<>();
        for (Event event : events) {
            eventsAsString.add(event.toString());
        }
        return eventsAsString;
    }

    /**
     * @return a string including all events.
     */
    public String stringEventInfoAll() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < rooms.size(); i++) {
            s.append(stringEventsOfRoom(i));
        }
        return s.toString();
    }

    /**
     * @param attendeeID the User ID of the Attendee
     * @return a string including all this Attendee's Events
     */
    public String stringEventInfoAttending(UUID attendeeID) {
        StringBuilder s = new StringBuilder("All events: \n");
        for (Event event : getEvents()){
            if (getEventAttendeeIDs(event.getEventID()).contains(attendeeID)){
                s.append(event.toString());
            }
        } return s.toString();
    }

    /**
     * @param eventID the event ID
     * @return a string of this event.
     */
    public String stringEvent(UUID eventID) {
        return getEvent(eventID).toString();
    }
}
