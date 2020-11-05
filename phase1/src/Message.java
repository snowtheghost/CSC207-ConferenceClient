import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a message sent by a user.
 * @author Zachariah Vincze
 */
public class Message {
    private final UUID senderID;
    private final List<UUID> recipientIDs;
    private final UUID messageID;
    private final String messageContent;

    /**
     * Creates a new message.
     * @param messageContent the string content of this message.
     * @param sender the sender of this message.
     * @param recipients a list of recipients of this message.
     */
    public Message(String messageContent, User sender, List<User> recipients) {
        this.messageID = UUID.randomUUID();
        this.senderID = sender.getUserID();
        this.recipientIDs = new ArrayList<UUID>();
        for (User user : recipients) {
            this.recipientIDs.add(user.getUserID());
        }
        this.messageContent = messageContent;
    }

    /**
     * Returns the sender UUID.
     * @return the sender UUID.
     */
    public UUID getSenderID() { return this.senderID; }

    /**
     * Returns a list of user UUIDs who have received this message.
     * @return the list of recipient UUIDs.
     */
    public List<UUID> getRecipientIDs() { return this.recipientIDs; }

    /**
     * Gets the message's content.
     * @return A string of the message's content.
     */
    public String getMessageContent() {
        return this.messageContent;
    }

    /**
     * Gets the unique ID of this message.
     * @return A UUID which represents the ID of this message.
     */
    public UUID getMessageID() {
        return this.messageID;
    }
}
