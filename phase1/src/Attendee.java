import java.util.ArrayList;
import java.util.UUID;
import java.util.List;

/**
 * Represents an Attendee
 * @author Zihan Wang
 */
public class Attendee extends User {
    private List<UUID> contacts;
    private List<UUID> events;

    /**
     * Creates a new Attendee with a unique ID, a username,
     * no messages, no contacts and no reserved Events.
     * @param username The user's username.
     */
    public Attendee(String username){
        super(username);
        contacts = new ArrayList<>();
        events = new ArrayList<>();
    }

    /**
     * @return the Attendee's Events
     */
    public List<UUID> getEvents() {
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
     * @return whether the Event be added successfully
     */
    public boolean addEvents(Event event){
        if(this.events.contains(event.getEventID())){
            return false;
        } else {
            this.events.add(event.getEventID());
        }
        return true;
    }

    /**
     * Remove the eventID from this.event.
     * @param event the Event object to be removed from this.events
     * @return true if it is successfully removed, and false if it failed.
     */
    public boolean removeReservedEvents(Event event){
        return this.events.remove(event.getEventID());
    }

    @Override
    /**
     * @return whether the Attendee is an Organizer
     */
    public boolean isOrganizer(){
        return false;
    }

}