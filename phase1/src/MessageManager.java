import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Manages message entities.
 * @author Zachariah Vincze
 */
public class MessageManager {
    private Map<UUID, Message> messages;
    private UserManager userManager;

    /**
     * Creates a new MessageManager.
     * @param userManager the users that this message system will be sending messages to.
     */
    public MessageManager(UserManager userManager) {
        this.userManager = userManager;
        this.messages = new HashMap<UUID, Message>();
    }

    /**
     * Sends messages from a single user to a list of recipients.
     * @param messageContent the string content of the message.
     * @param senderID the user sending the message.
     * @param recipientIDs a list of users that are receiving the message.
     */
    public void sendMessages(String messageContent, UUID senderID, List<UUID> recipientIDs) {
        Message message = new Message(messageContent, senderID, recipientIDs);
        messages.put(message.getMessageID(), message);
        for (UUID id : recipientIDs) {
            this.userManager.getUser(id).addMessage(senderID, message.getMessageID());
        }
    }
}
