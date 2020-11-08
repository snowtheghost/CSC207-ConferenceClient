import java.util.*;

/**
 * Represent an Organizer
 *
 *
 *
 * @author Justin Chan, Tanuj Devjani
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
    public boolean isOrganizer() {
        return true;
    }

}
