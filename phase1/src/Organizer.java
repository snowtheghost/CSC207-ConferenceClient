import java.util.*;

/**
 * Represent an Organizer
 *
 *
 *
 * @author Tanuj Devjani
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
     * @return true as this instance is an Organizer
     */
    @Override
    public boolean isOrganizer() {
        return true;
    }

    @Override
    public boolean isAttendee() { return true; }

}
