import java.util.*;

/**
 * Represent an Organizer
 *
 *
 *
 * @author Justin Chan, TODO: Add your name here for the non-room related method contributor
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



}
