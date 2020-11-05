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
     * @param sender the user sending the message.
     * @param recipients a list of users that are receiving the message.
     */
    public void sendMessages(String messageContent, User sender, List<User> recipients) {
        Message message = new Message(messageContent, sender, recipients);
        messages.put(message.getMessageID(), message);
        for (User user : recipients) {
            this.userManager.getUser(user.getUserID()).addMessage(sender, message);
        }
    }
}
