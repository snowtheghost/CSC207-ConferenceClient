import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents an Organizer, the most powerful form of User
 *
 */
public class Organizer extends Attendee {
    /**
     * Creates a new user with a unique ID and a username.
     *
     * @param username The user's username.
     */
    public Organizer(String username) {
        super(username);
    }

    @Override
    /*
     method used as an attribute to check whether or not a given user is an organizer or not
     */
    public boolean isOrganizer() {
        return true;
    }

}
