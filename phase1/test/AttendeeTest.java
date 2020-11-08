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
            new GregorianCalendar(2000, Calendar.MAY, 1, 100, 0, 0),
            new GregorianCalendar(2000, Calendar.MAY, 1, 100, 30, 0));
    Event event2 = new Event("event2",speaker2,
            new GregorianCalendar(2000, Calendar.MAY, 1, 100, 0, 0),
            new GregorianCalendar(2000, Calendar.MAY, 1, 100, 30, 0));
    Attendee attendee1 = new Attendee("attendee1");
    Attendee attendee2 = new Attendee("attendee2");

    //@Before
    //public void setUpBefore(){ Attendee attendee1 = new Attendee("attendee1");}

    @Test
    public void TestGetEvents(){
        List<UUID> e = new ArrayList<>();
        assertEquals(attendee1.getEvents(),e);
        e.add(event1.getEventID());
        e.add(event2.getEventID());
        attendee1.addEvents(event1);
        attendee1.addEvents(event2);
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

}