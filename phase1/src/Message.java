import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a message sent by a user.
 * @author Zachariah Vincze
 */
public class Message {
    private final UUID messageID;
    private final String messageContent;

    /**
     * Creates a new message.
     * @param messageContent the string content of this message.
     * @param senderID the sender of this message.
     * @param recipientIDs a list of recipients of this message.
     */
    public Message(String messageContent) {
        this.messageID = UUID.randomUUID();
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
