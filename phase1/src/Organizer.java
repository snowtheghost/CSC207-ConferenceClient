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
    /**
     * @return true as this instance is an Organizer, otherwise return false
     * */
    @Override
    public boolean isOrganizer() {
        return true;
    }
    /**
     * @return true as this instance is an Attendee, otherwise return false
     * */
    @Override
    public boolean isAttendee() {
        return false;
    }




}
