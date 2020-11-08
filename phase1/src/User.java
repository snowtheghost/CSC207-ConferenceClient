import java.util.*;

/**
 * Represents an abstract User
 * @author Zachariah Vincze
 */
public abstract class User {
    private UUID userID;
    private String username;
    private Map<UUID, List<UUID>> conversations;

    /**
     * Creates a new user with a unique ID and a username.
     * @param username The user's username.
     */
    public User(String username) {
        this.userID = UUID.randomUUID();
        this.username = username;
        this.conversations = new HashMap<UUID, List<UUID>>();
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

    /**
     * Returns a list of message UUIDs that this user has received from a specific sender.
     * @param sender the UUID of the sender of the messages.
     * @return a list of messages UUIDs that have been sent by sender.
     */
    public List<UUID> getMessages(UUID sender) {
        return this.conversations.get(sender);
    }

    /**
     * Adds a message to this user's received message history.
     * @param sender the UUID of the user who sent this message.
     * @param messageID the UUID of the message that was sent.
     */
    public void addMessage(UUID sender, UUID messageID) {
        if (!this.conversations.containsKey(sender)) {
            this.conversations.put(sender, new ArrayList<UUID>());
        }
        this.conversations.get(sender).add(messageID);
    }

    /**
     * Checks if User is an Organizer.
     * @return returns true if User is an Organizer, otherwise return false.
     * */
    public abstract boolean isOrganizer();


}
