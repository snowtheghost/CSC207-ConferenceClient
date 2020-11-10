import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Liam Ogilvie
 */

public class MessageManagerTests {
    private MessageManager messageManager = new MessageManager();
    private UserManager userManager = new UserManager();
    private RoomManager roomManager;
    private ArrayList<Room> rooms;
    private Room room;

    @Before
    public void setupBefore() {
        messageManager = new MessageManager();
        userManager = new UserManager();
        roomManager = new RoomManager();
        rooms = new ArrayList<>();

    }
    /*
     * sendMessage()
     */
    @Test
    public void testSendMessageRecipDoesntExist() {
        Attendee attendee1 = userManager.createAttendeeAccount("attendee1");
        UUID a1UUID = attendee1.getUserID();
        UUID a2UUID = UUID.randomUUID();
        String messageContent = "You Do Not Exist";

        assertFalse(messageManager.sendMessage(userManager, a1UUID, a2UUID, messageContent));
    }
    /*
     * sendMessage()
     * getMessageContentsFromUser()
     */
    @Test
    public void testSendMessageA1toA2() {
        Attendee attendee1 = userManager.createAttendeeAccount("attendee1");
        Attendee attendee2 = userManager.createAttendeeAccount("attendee2");
        UUID a1UUID = attendee1.getUserID();
        UUID a2UUID = attendee2.getUserID();
        String messageContent = "hello";

        messageManager.sendMessage(userManager, a1UUID, a2UUID, messageContent);
        List<String> msg = messageManager.getMessageContentsFromUser(userManager, a2UUID, a1UUID);
        assertEquals(msg.get(0), "hello");
    }
    /*
     * sendMessageToAllAttendees()
     * getMessagesFromUser()
     */
    @Test
    public void testSendMessagesToAllAttendeesOrganizerToA2andA3() {
        Organizer organizer = userManager.createOrganizerAccount("organizer");
        Attendee attendee2 = userManager.createAttendeeAccount("attendee2");
        Attendee attendee3 = userManager.createAttendeeAccount("attendee2");
        UUID orgUUID = organizer.getUserID();
        UUID a2UUID = attendee2.getUserID();
        UUID a3UUID = attendee3.getUserID();
        String messageContent = "goodbye";

        messageManager.sendMessageToAllAttendees(userManager, orgUUID, messageContent);
        List<Message> msg1 = messageManager.getMessagesFromUser(userManager, a2UUID, orgUUID);
        assertEquals(msg1.get(0).getMessageContent(), "goodbye");
        List<Message> msg2 = messageManager.getMessagesFromUser(userManager, a3UUID, orgUUID);
        assertEquals(msg2.get(0).getMessageContent(), "goodbye");
        List<Message> msg3 = messageManager.getMessagesFromUser(userManager, orgUUID, orgUUID);
        assertEquals(msg3.size(), 0);
    }
    /*
     * sendMessageToEventAttendees()
     * getMessagesFromUSer()
     */
    @Test
    public void testSendMessagesToAllEventAttendeesSpeakerToA1andA2() {
        Attendee attendee1 = userManager.createAttendeeAccount("attendee1");
        Attendee attendee2 = userManager.createAttendeeAccount("attendee2");
        UUID a1UUID = attendee1.getUserID();
        UUID a2UUID = attendee2.getUserID();

        room = roomManager.newRoom();
        Speaker speaker = userManager.createSpeakerAccount("speaker");
        String messageContent = "event";
        UUID speakerUUID = speaker.getUserID();

        Event event = roomManager.newEvent("1", speaker,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                room);
        UUID eventUUID = event.getEventID();
        roomManager.addEventAttendee(attendee1, event);
        roomManager.addEventAttendee(attendee2, event);

        messageManager.sendMessageToEventAttendees(userManager, roomManager,
                speakerUUID, eventUUID, messageContent);
        List<Message> msg1 = messageManager.getMessagesFromUser(userManager, a1UUID, speakerUUID);
        assertEquals(msg1.get(0).getMessageContent(), "event");
        List<Message> msg2 = messageManager.getMessagesFromUser(userManager, a2UUID, speakerUUID);
        assertEquals(msg2.get(0).getMessageContent(), "event");
        List<Message> msg3 = messageManager.getMessagesFromUser(userManager, speakerUUID, speakerUUID);
        assertEquals(msg3.size(), 0);
    }
}