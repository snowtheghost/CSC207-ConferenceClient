import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.junit.Assert.*;

/**
 * @author Arya Joshi
 */

public class UserTests {
    private Attendee attendee;
    private Organizer organizer;
    private Speaker speaker;

    @Before
    public void setupBefore() {
        String username_att = "Andy";
        String username_org = "Bob";
        String username_spe = "Larry";
        attendee = new Attendee(username_att);
        organizer = new Organizer(username_org);
        speaker = new Speaker(username_spe);
    }

    @Test
    public void testGetUserID() {
        UUID att_id = attendee.getUserID();
        UUID org_id = organizer.getUserID();
        UUID spe_id = speaker.getUserID();
        assertEquals(attendee.getUserID(), att_id);
        assertEquals(organizer.getUserID(), org_id);
        assertEquals(speaker.getUserID(), spe_id);
    }

    @Test
    public void testGetUsername() {
        assertEquals(attendee.getUsername(), "Andy");
        assertEquals(organizer.getUsername(), "Bob");
        assertEquals(speaker.getUsername(), "Larry");
    }

    @Test
    public void testGetMessages() {
        List<UUID> message_list = attendee.getMessages(organizer.getUserID());
        assertTrue(message_list.isEmpty());
        //Tested a non-empty message_list in the next test along with addMessage instead of here.
    }

    @Test
    public void testAddMessage() {
        Message message1 = new Message("Hello!");
        Message message2 = new Message("Hi!");
        Message message3 = new Message("Hey!");

        //Testing when there are no received messages:
        attendee.addMessage(organizer.getUserID(), message1.getMessageID());
        List<UUID> message_list = attendee.getMessages(organizer.getUserID());
        List<UUID> correct_list = new ArrayList<>();
        correct_list.add(message1.getMessageID());
        assertEquals(message_list, correct_list);

        //Testing when there is a received message:
        attendee.addMessage(organizer.getUserID(), message2.getMessageID());
        List<UUID> message_list2 = attendee.getMessages(organizer.getUserID());
        correct_list.add(message2.getMessageID());
        assertEquals(message_list2, correct_list);

        //Testing when there are received messages from multiple senders:
        attendee.addMessage(speaker.getUserID(), message3.getMessageID());
        List<UUID> message_list3 = attendee.getMessages(organizer.getUserID());
        List<UUID> message_list4 = attendee.getMessages(speaker.getUserID());
        assertEquals(message_list3, correct_list);
        correct_list = new ArrayList<>();
        correct_list.add(message3.getMessageID());
        assertEquals(message_list4, correct_list);
    }

    @Test
    public void testisOrganizer() {
        assertTrue(organizer.isOrganizer());
        assertFalse(attendee.isOrganizer());
        assertFalse(speaker.isOrganizer());
    }

    @Test
    public void testisAttendee() {
        assertTrue(attendee.isAttendee());
        assertFalse(organizer.isAttendee());
        assertFalse(speaker.isAttendee());
    }

    @Test
    public void testisSpeaker() {
        assertTrue(speaker.isSpeaker());
        assertFalse(attendee.isSpeaker());
        assertFalse(organizer.isSpeaker());
    }
}
