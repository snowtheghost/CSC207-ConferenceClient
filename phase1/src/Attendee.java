import java.util.ArrayList;

/**
 * Represents an Attendee
 */
public class Attendee extends User {
    private ArrayList<Message> messages;
    private ArrayList<User> contacts;
    private ArrayList<Event> events;

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
    public ArrayList<Event> getEvents() {
        return events;
    }

    /**
     * @return the Attendee's Messages
     */
    public ArrayList<Message> getMessages() {
        return messages;
    }

    /**
     * @return the Attendee's Contacts
     */
    public ArrayList<User> getContacts() {
        return contacts;
    }
}