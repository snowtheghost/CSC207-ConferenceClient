import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents an Organizer, the most powerful form of User
 * Author: Tanuj Devjani
 */
public class Organizer extends Attendee {
    private List<UUID> eventsOrganized;
    /**
     * Creates a new user with a unique ID and a username.
     *
     * @param username The user's username.
     */
    public Organizer(String username) {
        super(username);
        eventsOrganized = new ArrayList<>();
    }

    @Override
    /*
     method used as an attribute to check whether or not a given user is an organizer or not
     */
    public boolean isOrganizer() {
        return true;
    }
    public List<UUID> getEvents() {
        return eventsOrganized;
    }


}
