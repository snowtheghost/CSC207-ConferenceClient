import org.junit.Test;
import java.util.UUID;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author Zihan Wang
 */
public class OrganizerTest {
    Organizer organizer1 = new Organizer("organizer1");
    Message message1 = new Message("message1");
    Attendee attendee1 = new Attendee("attendee1");


    @Test
    public void TestIsOrganizer(){
        assertTrue(organizer1.isOrganizer());
    }

    @Test
    public void TestIsAttendee(){assertFalse(organizer1.isAttendee());}

    @Test
    public void TestIsSpeaker(){assertFalse(organizer1.isSpeaker());}

    @Test
    public void TestGetUserName(){assertEquals(organizer1.getUsername(), "organizer1");}

    @Test
    public void TestAddGetMessage(){
        ArrayList<UUID> res = new ArrayList<>();
        assertEquals(res, organizer1.getMessages(attendee1.getUserID()));
        organizer1.addMessage(attendee1.getUserID(), message1.getMessageID());
        res.add(message1.getMessageID());
        assertEquals(res, organizer1.getMessages(attendee1.getUserID()));
    }
}
