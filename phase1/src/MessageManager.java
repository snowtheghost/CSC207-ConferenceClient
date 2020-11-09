import java.util.*;

/**
 * Manages message entities.
 * @author Zachariah Vincze
 */
public class MessageManager {
    private final Map<UUID, Message> messages;

    /**
     * Creates a new MessageManager.
     * @param userManager the users that this message system will be sending messages to.
     */
    public MessageManager(UserManager userManager, RoomManager roomManager) {
        this.messages = new HashMap<>();
    }

    /**
     * Sends a single message from a user to another user.
     * @param userManager the UserManager where the users are stored.
     * @param senderID the UUID of the message sender.
     * @param recipientID the UUID of the message receiver.
     * @param messageContent the string content of the message.
     */
    public void sendMessage(UserManager userManager, UUID senderID, UUID recipientID, String messageContent) {
        Message message = new Message(messageContent);
        messages.put(message.getMessageID(), message);
        userManager.getUser(recipientID).addMessage(senderID, message.getMessageID());
    }

    /**
     * Sends messages from a single user to a list of recipients.
     * @param userManager the UserManager where the users are stored.
     * @param messageContent the string content of the message.
     * @param recipientIDs a list of users that are receiving the message.
     */
    private void sendMessages(UserManager userManager, UUID senderID, List<UUID> recipientIDs, String messageContent) {
        Message message = new Message(messageContent);
        messages.put(message.getMessageID(), message);
        for (UUID id : recipientIDs) {
            userManager.getUser(id).addMessage(senderID, message.getMessageID());
        }
    }

    /**
     * Sends a message from an organizer to all Attendees in the system.
     *
     * Precondition: the senderID must belong to an Organizer.
     *
     * @param userManager the UserManager where the users are stored.
     * @param messageContent the string content of the message.
     */
    public void sendMessageToAllAttendees(UserManager userManager, UUID senderID, String messageContent) {
        sendMessages(userManager, senderID, userManager.getAttendeeUUIDs(), messageContent);
    }

    /**
     * Return a list of messages that were sent by a specific user to
     * the current user. If no messages were sent to this user from senderID,
     * then an empty list is returned.
     *
     * Precondition: The recipientID must belong to the currently logged in user.
     *
     * @param userManager the UserManager where the users are stored.
     * @param senderID the UUID of the user who sent this message.
     * @return a string list of messages sent from another user.
     */
    public List<String> getMessagesFromUser(UserManager userManager, UUID recipientID, UUID senderID) {
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
     * @param userManager the UserManager where the users are stored.
     * @param roomManager the RoomManager where the events and rooms are stored.
     * @param senderID the UUID of the sender of this message.
     * @param eventID the UUID of the event that the users are in.
     * @param messageContent the content of the message to send.
     */
    public void sendMessageToEventAttendees(UserManager userManager, RoomManager roomManager,
                                            UUID senderID, UUID eventID, String messageContent) {
        Event event = roomManager.getEvent(eventID);
        ArrayList<UUID> attendeeIDs = roomManager.getEventAttendeeIDs(event);
        sendMessages(userManager, senderID, attendeeIDs, messageContent);
    }
}
