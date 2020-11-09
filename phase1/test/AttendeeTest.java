import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Zihan Wang
 */
public class AttendeeTest {
    Speaker speaker1 = new Speaker("speaker1");
    Speaker speaker2 = new Speaker("speaker2");
    Event event1 = new Event("event1",speaker1,
            new GregorianCalendar(2000, Calendar.MAY, 1, 99, 0, 0),
            new GregorianCalendar(2000, Calendar.MAY, 1, 99, 30, 0));
    Event event2 = new Event("event2",speaker2,
            new GregorianCalendar(2000, Calendar.MAY, 1, 100, 0, 0),
            new GregorianCalendar(2000, Calendar.MAY, 1, 100, 30, 0));
    Room room = new Room();
    Attendee attendee1 = new Attendee("attendee1");
    Attendee attendee2 = new Attendee("attendee2");

    @Test
    public void TestAddGetRemoveEvents(){
        Map<UUID, List<UUID>> e = new HashMap<>();
        assertEquals(attendee1.getEvents(),e);
        e.put(room.getRoomID(), new ArrayList<>());
        e.get(room.getRoomID()).add(event1.getEventID());
        e.get(room.getRoomID()).add(event2.getEventID());
        attendee1.addEvents(room,event1);
        attendee1.addEvents(room,event1);
        attendee1.addEvents(room,event2);
        assertEquals(attendee1.getEvents(),e);
        e.get(room.getRoomID()).remove(event1.getEventID());
        attendee1.removeReservedEvents(room, event1);
        attendee1.removeReservedEvents(room, event1);
        assertEquals(attendee1.getEvents(),e);
    }

    @Test
    public void TestGetAddRemoveContact(){
        ArrayList<UUID> a = new ArrayList<>();
        assertEquals(a, attendee1.getContacts());
        a.add(attendee2.getUserID());
        attendee1.addContact(attendee2);
        assertEquals(a, attendee1.getContacts());
        a.remove(attendee2.getUserID());
        User[] l = {attendee2};
        attendee1.removeContacts(l);
        assertEquals(a, attendee1.getContacts());
    }

    @Test
    public void TestIsOrganizer(){
        assertEquals(attendee1.isOrganizer(), false);
    }

    @Test
    public void TestIsAttendee(){assertTrue(attendee1.isAttendee());}

    @Test
    public void TestIsSpeaker(){assertFalse(attendee1.isSpeaker());}
}
