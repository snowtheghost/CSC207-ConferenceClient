import java.util.UUID;

/**
 * Represents a message sent by a user.
 * @author Zachariah Vincze
 */
public class Message {
    private final UUID senderID;
    private final UUID messageID;
    private final String messageContent;

    /**
     * Create a new message object.
     * @param messageContent the content of the message.
     * @param senderID the user ID of the user who sent this message.
     */
    public Message(String messageContent, UUID senderID) {
        this.messageID = UUID.randomUUID();
        this.senderID = senderID;
        this.messageContent = messageContent;
    }

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
