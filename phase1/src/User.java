import java.util.ArrayList;
import java.util.UUID;

/**
 * Represents an abstract User
 * @author Zachariah Vincze
 */
public abstract class User {
    private UUID userID;
    private String username;
    private ArrayList<UUID> messages;

    /**
     * Creates a new user with a unique ID and a username.
     * @param username The user's username.
     */
    public User(String username) {
        this.userID = UUID.randomUUID();
        this.username = username;
        messages = new ArrayList<>();
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

    public ArrayList<UUID> getMessages() {return messages;}

    public void receiveMessage(Message message){this.messages.add(message.getMessageID());}

    protected void sendMessageHelper(User recipient, String content){
        Message m = new Message(content, this.userID);
        recipient.receiveMessage(m);
    }
}
