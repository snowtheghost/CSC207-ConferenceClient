import com.group0179.entities.*;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * @author Zihan Wang
 */
public class AttendeeTest {
    Speaker speaker1 = new Speaker("speaker1");
    Speaker speaker2 = new Speaker("speaker2");
    Event event1 = new Event("event1",speaker1.getUsername(),
            new GregorianCalendar(2000, Calendar.MAY, 1, 99, 0, 0),
            new GregorianCalendar(2000, Calendar.MAY, 1, 99, 30, 0));
    Event event2 = new Event("event2",speaker2.getUsername(),
            new GregorianCalendar(2000, Calendar.MAY, 1, 100, 0, 0),
            new GregorianCalendar(2000, Calendar.MAY, 1, 100, 30, 0));
    Room room = new Room();
    Attendee attendee1 = new Attendee("attendee1");
    Attendee attendee2 = new Attendee("attendee2");
    Message message1 = new Message("message1");
    Message message2 = new Message("message2");

    @Test
    public void TestGetUserName(){assertEquals("attendee1", attendee1.getUsername()); }

    @Test
    public void TestGetAddMessages(){
        ArrayList<UUID> res = new ArrayList<>();
        assertEquals(res, attendee1.getMessages(attendee2.getUserID()));
        attendee1.addMessage(attendee2.getUserID(), message1.getMessageID());
        res.add(message1.getMessageID());
        assertEquals(res, attendee1.getMessages(attendee2.getUserID()));
        res.add(message2.getMessageID());
        attendee1.addMessage(attendee2.getUserID(), message2.getMessageID());
        assertEquals(res, attendee1.getMessages(attendee2.getUserID()));
    }

    @Test
    public void TestAddGetRemoveEvents(){
        Map<UUID, List<UUID>> e = new HashMap<>();
        assertEquals(attendee1.getEvents(),e);
        e.put(room.getRoomID(), new ArrayList<>());
        e.get(room.getRoomID()).add(event1.getEventID());
        e.get(room.getRoomID()).add(event2.getEventID());
        attendee1.addEvents(room.getRoomID(),event1.getEventID());
        attendee1.addEvents(room.getRoomID(),event1.getEventID());
        attendee1.addEvents(room.getRoomID(),event2.getEventID());
        assertEquals(attendee1.getEvents(),e);
        e.get(room.getRoomID()).remove(event1.getEventID());
        attendee1.removeReservedEvents(room.getRoomID(), event1.getEventID());
        attendee1.removeReservedEvents(room.getRoomID(), event1.getEventID());
        assertEquals(attendee1.getEvents(),e);
    }

    @Test
    public void TestIsOrganizer(){
        assertEquals(attendee1.isOrganizer(), false);
    }

    @Test
    public void TestIsAttendee(){assertTrue(attendee1.isAttendee());}

    @Test
    public void TestIsSpeaker(){assertFalse(attendee1.isSpeaker());}

    @Test
    public void TestGetStringType(){assertEquals(attendee1.getStringType(), "attendee");}
}
