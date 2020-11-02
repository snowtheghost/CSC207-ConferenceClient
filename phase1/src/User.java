import java.util.UUID;

/**
 * Represents an abstract User
 * @author Zachariah Vincze
 */
public abstract class User {
    private UUID userID;
    private String username;

    /**
     * Creates a new user with a unique ID and a username.
     * @param username The user's username.
     */
    public User(String username) {
        this.userID = UUID.randomUUID();
        this.username = username;
    }

    /**
     * @return the User's ID
     */
    public UUID getUserID() {
        return userID;
    }

    /**
     * @return the User's name
     */
    public String getUsername() {
        return username;
    }
}
