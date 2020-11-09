import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.prefs.PreferenceChangeEvent;

/**
 * Manages message entities.
 * @author Zachariah Vincze
 */
public class MessageManager {
    private final Map<UUID, Message> messages;
    private final UserManager userManager;
    private RoomManager roomManager;

    /**
     * Creates a new MessageManager.
     * @param userManager the users that this message system will be sending messages to.
     */
    public MessageManager(UserManager userManager, RoomManager roomManager) {
        this.userManager = userManager;
        this.roomManager = roomManager;
        this.messages = new HashMap<>();
    }

    /**
     * Sends a single message from a user to another user.
     * @param senderID the UUID of the message
     * @param recipientID
     * @param messageContent
     */
    public void sendMessage(UUID senderID, UUID recipientID, String messageContent) {
        Message message = new Message(messageContent);
        messages.put(message.getMessageID(), message);
        userManager.getUser(recipientID).addMessage(senderID, message.getMessageID());
    }

    /**
     * Sends messages from a single user to a list of recipients.
     * @param messageContent the string content of the message.
     * @param recipientIDs a list of users that are receiving the message.
     */
    private void sendMessages(UUID senderID, List<UUID> recipientIDs, String messageContent) {
        Message message = new Message(messageContent);
        messages.put(message.getMessageID(), message);
        for (UUID id : recipientIDs) {
            this.userManager.getUser(id).addMessage(senderID, message.getMessageID());
        }
    }

    /**
     * Sends a message from an organizer to all Attendees in the system.
     *
     * Precondition: the senderID must belong to an Organizer.
     *
     * @param messageContent the string content of the message.
     */
    public void sendMessageToAllAttendees(UUID senderID, String messageContent) {
        sendMessages(senderID, userManager.getAttendeeUUIDs(), messageContent);
    }

    /**
     * Return a list of messages that were sent by a specific user to
     * the current user. If no messages were sent to this user from senderID,
     * then an empty list is returned.
     *
     * Precondition: The recipientID must belong to the currently logged in user.
     *
     * @param senderID the UUID of the user who sent this message.
     * @return a string list of messages sent from another user.
     */
    public List<String> getMessagesFromUser(UUID recipientID, UUID senderID) {
        ArrayList<String> messageContents = new ArrayList<>();
        List<UUID> messageIDs = userManager.getUser(recipientID).getMessages(senderID);
        for (UUID id : messageIDs) {
            messageContents.add(messages.get(id).getMessageContent());
        }
        return messageContents;
    }

    /**
     * Sends a single message from a speaker to attendees of an event.
     *
     * Precondition: senderID is the UUID of a speaker only.
     *
     * @param senderID the UUID of the sender of this message.
     * @param eventID the UUID of the event that the users are in.
     * @param messageContent the content of the message to send.
     */
    public void sendMessageToEventAttendees(UUID senderID, UUID eventID, String messageContent) {
        // TODO: Waiting on RoomManager implementation to continue this
        ArrayList<UUID> attendeeIDs = new ArrayList<>();
        sendMessages(senderID, attendeeIDs, messageContent);
    }
}
