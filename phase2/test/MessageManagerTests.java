import com.group0179.entities.Room;
import com.group0179.use_cases.MessageManager;
import com.group0179.use_cases.RoomManager;
import com.group0179.use_cases.UserManager;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

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
        UUID a1UUID = userManager.createAttendeeAccount("attendee1");
        UUID a2UUID = UUID.randomUUID();
        String messageContent = "You Do Not Exist";

        assertNull(messageManager.sendMessage(userManager, a1UUID, a2UUID, messageContent));
    }
    /*
     * sendMessage()
     * getMessageContentsFromUser()
     */
    @Test
    public void testSendMessageA1toA2() {
        UUID a1UUID = userManager.createAttendeeAccount("attendee1");
        UUID a2UUID = userManager.createAttendeeAccount("attendee2");
        String messageContent = "hello";

        messageManager.sendMessage(userManager, a1UUID, a2UUID, messageContent);
        List<String> msg = messageManager.getMessageContentsFromUser(userManager, a2UUID, a1UUID);
        assertEquals(msg.get(0), "hello");
    }
    /*
     * sendMessageToAllAttendees()
     * getMessageContentsFromUser()
     */
    @Test
    public void testSendMessagesToAllAttendeesOrganizerToA2andA3() {
        UUID orgUUID = userManager.createOrganizerAccount("organizer");
        UUID a2UUID = userManager.createAttendeeAccount("attendee2");
        UUID a3UUID = userManager.createAttendeeAccount("attendee2");
        String messageContent = "goodbye";

        messageManager.sendMessageToAllAttendees(userManager, orgUUID, messageContent);
        List<String> msg1 = messageManager.getMessageContentsFromUser(userManager, a2UUID, orgUUID);
        assertEquals(msg1.get(0), "goodbye");
        List<String> msg2 = messageManager.getMessageContentsFromUser(userManager, a3UUID, orgUUID);
        assertEquals(msg2.get(0), "goodbye");
        List<String> msg3 = messageManager.getMessageContentsFromUser(userManager, orgUUID, orgUUID);
        assertEquals(msg3.size(), 0);
    }
    /*
     * sendMessageToEventAttendees()
     * getMessageContentsFromUser()
     */
    @Test
    public void testSendMessagesToAllEventAttendeesSpeakerToA1andA2() {
        UUID a1UUID = userManager.createAttendeeAccount("attendee1");
        UUID a2UUID = userManager.createAttendeeAccount("attendee2");

        roomManager.newRoom(30);
        UUID speakerUUID = userManager.createSpeakerAccount("speaker");
        String messageContent = "event";

        UUID eventID = roomManager.newEvent("1", userManager.getUsername(speakerUUID),
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                0, userManager, 20);
        roomManager.addEventAttendee(a1UUID, eventID, userManager, false);
        roomManager.addEventAttendee(a2UUID, eventID, userManager, false);

        messageManager.sendMessageToEventAttendees(userManager, roomManager,
                speakerUUID, eventID, messageContent);
        List<String> msg1 = messageManager.getMessageContentsFromUser(userManager, a1UUID, speakerUUID);
        assertEquals(msg1.get(0), "event");
        List<String> msg2 = messageManager.getMessageContentsFromUser(userManager, a2UUID, speakerUUID);
        assertEquals(msg2.get(0), "event");
        List<String> msg3 = messageManager.getMessageContentsFromUser(userManager, speakerUUID, speakerUUID);
        assertEquals(msg3.size(), 0);
    }
    /*
     *
     */
    @Test
    public void testSendMessagesToAllEventAttendeesWhoDoNotExist() {

        UUID a1UUID = UUID.randomUUID();
        UUID a2UUID = UUID.randomUUID();

        roomManager.newRoom(30);
        UUID speakerUUID = userManager.createSpeakerAccount("speaker");
        String messageContent = "event";

        UUID eventID = roomManager.newEvent("1", userManager.getUsername(speakerUUID),
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                0, userManager, 20);
        UUID eventUUID = eventID;

        assertSame(null, messageManager.sendMessageToEventAttendees(userManager, roomManager,
                speakerUUID, eventUUID, messageContent) );
    }
}