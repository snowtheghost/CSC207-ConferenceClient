import java.util.*;

/**
 * Represents an Attendee
 * @author Zihan Wang
 */
public class Attendee extends User {
    private List<UUID> contacts;
    private Map<UUID, ArrayList<UUID>> events;

    /**
     * Creates a new Attendee with a unique ID, a username,
     * no messages, no contacts and no reserved Events.
     * @param username The user's username.
     */
    public Attendee(String username){
        super(username);
        contacts = new ArrayList<>();
        events = new HashMap<>();
    }

    /**
     * @return the Attendee's Events
     */
    public Map<UUID, ArrayList<UUID>> getEvents() {
        return events;
    }

    /**
     * @return the Attendee's Contacts
     */
    public List<UUID> getContacts() {
        return contacts;
    }

    /**
     * Add another User to the Attendee's Contacts
     * @param user Another User
     */
    public void addContact(User user){contacts.add(user.getUserID());}

    /**
     * Remove a list of Users from this.contacts
     * @param ContactsToRemove A list of User who will be removed
     * @return the number of User in ContactsTo Remove who is not in this.contacts
     */
    public int removeContacts(User[] ContactsToRemove){
        int res = 0;
        for(User contact: ContactsToRemove){
        if(!contacts.contains(contact.getUserID())) res += 1;
        else {contacts.remove(contact.getUserID());}
        }
        return res;
    }

    /**
     * Add all all the UUIDs of the Events in EventstoAdd to the Attendee's events except duplication.
     * @param event An Event to be added.
     * @param room the Room of the Event.
     * @return whether the Event be added successfully
     */
    public boolean addEvents(Room room, Event event){
        events.putIfAbsent(room.getRoomID(), new ArrayList<>());
        if(events.get(room.getRoomID()).contains(event.getEventID()))return false;
        events.get(room.getRoomID()).add(event.getEventID());
        return true;
    }

    /**
     * Remove the eventID from this.event.
     * @param event the Event object to be removed from this.events
     * @return true if it is successfully removed, and false if it failed.
     *
     * Last modified for bug fix: Justin Chan
     */
    public boolean removeReservedEvents(Room room, Event event){
        if (!events.containsKey(room.getRoomID())) {
            return false;
        }
        if (!events.get(room.getRoomID()).contains(event.getEventID())) {
            return false;
        }
        events.get(room.getRoomID()).remove(event.getEventID());
        if (events.get(room.getRoomID()).isEmpty()) {
            events.remove(room.getRoomID());
        }
        return true;
    }

    @Override
    public boolean isOrganizer(){
        return false;
    }

    @Override
    public boolean isAttendee(){return true;}

    @Override
    public boolean isSpeaker() {
        return false;
    }
}