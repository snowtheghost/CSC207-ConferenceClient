import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents an Attendee
 */
public class Attendee extends User {
    private ArrayList<UUID> messages;
    private ArrayList<UUID> contacts;
    private ArrayList<UUID> events;

    /**
     * Creates a new Attendee with a unique ID, a username,
     * no messages, no contacts and no reserved Events.
     * @param username The user's username.
     */
    public Attendee(String username){
        super(username);
        messages = new ArrayList<>();
        contacts = new ArrayList<>();
        events = new ArrayList<>();
    }

    /**
     * @return the Attendee's Events
     */
    public ArrayList<UUID> getEvents() {
        return events;
    }

    /**
     * @return the Attendee's Messages
     */
    public ArrayList<UUID> getMessages() {
        return messages;
    }

    /**
     * @return the Attendee's Contacts
     */
    public ArrayList<UUID> getContacts() {
        return contacts;
    }

    /**
     * Add another User to the Attendee's Contacts
     * @param user Another User
     */
    public void addContact(User user){contacts.add(user.getUserID());}

}