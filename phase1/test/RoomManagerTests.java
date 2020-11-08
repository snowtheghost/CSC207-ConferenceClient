import org.junit.*;
import java.util.*;

import static org.junit.Assert.*;

/**
 * @author Justin Chan
 */

public class RoomManagerTests {
    RoomManager rm = new RoomManager();
    ArrayList<Room> rooms = new ArrayList<>();

    @Before
    public void setUpBefore() {
        rm = new RoomManager();
        rooms = new ArrayList<>();
    }

    @Test
    public void testNewRoomGetRoom() {
        rooms.add(rm.newRoom());
        assertEquals(rooms.size(), 1);
        assertEquals(rooms.get(0), rm.getRooms().get(0));
        rooms.add(rm.newRoom());
        assertEquals(rooms.size(), 2);
        assertEquals(rooms.get(1), rm.getRooms().get(1));
    }

    @Test
    public void testNewEventValidNormal() {
        rooms.add(rm.newRoom());
        Speaker speaker1 = new Speaker("speaker1");

        assertTrue(rm.newEventValid("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                rooms.get(0)));
    }

    @Test
    public void testNewEventValidOOB() {
        rooms.add(rm.newRoom());
        Speaker speaker1 = new Speaker("speaker1");

        assertFalse(rm.newEventValid("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 1, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 2, 0, 0),
                rooms.get(0)));
    }

    @Test
    public void testNewEventValidOverlap() {
        rooms.add(rm.newRoom());
        Speaker speaker1 = new Speaker("speaker1");
        Speaker speaker2 = new Speaker("speaker2");

        assertTrue(rm.newEventValid("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                rooms.get(0)));

        Event event0 = rm.newEvent("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                rooms.get(0));

        assertTrue(rooms.get(0).getEvents().contains(event0));
        assertEquals(rooms.get(0).getEvents().size(), 1);

        assertFalse(rm.newEventValid("2", speaker2,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 30, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 30, 0),
                rooms.get(0)));

        assertFalse(rm.newEventValid("2", speaker2,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                rooms.get(0)));

        assertFalse(rm.newEventValid("2", speaker2,
                new GregorianCalendar(2000, Calendar.MAY, 1, 10, 30, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 30, 0),
                rooms.get(0)));

        assertTrue(rm.newEventValid("2", speaker2,
                new GregorianCalendar(2000, Calendar.MAY, 1, 10, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                rooms.get(0)));

        assertTrue(rm.newEventValid("2", speaker2,
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 13, 0, 0),
                rooms.get(0)));

        rm.newEvent("2", speaker2,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 30, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 30, 0),
                rooms.get(0));

        rm.newEvent("2", speaker2,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                rooms.get(0));

        rm.newEvent("2", speaker2,
                new GregorianCalendar(2000, Calendar.MAY, 1, 10, 30, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 30, 0),
                rooms.get(0));

        Event event1 = rm.newEvent("2", speaker2,
                new GregorianCalendar(2000, Calendar.MAY, 1, 10, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                rooms.get(0));

        Event event2 = rm.newEvent("2", speaker2,
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 13, 0, 0),
                rooms.get(0));

        assertTrue(rooms.get(0).getEvents().contains(event0));
        assertTrue(rooms.get(0).getEvents().contains(event1));
        assertTrue(rooms.get(0).getEvents().contains(event2));
        assertEquals(rooms.get(0).getEvents().size(), 3);

        assertTrue(rm.removeEvent(rooms.get(0), event0));
        assertFalse(rm.removeEvent(rooms.get(0), event0));
        assertTrue(rm.removeEvent(rooms.get(0), event1));
        assertFalse(rm.removeEvent(rooms.get(0), event1));
        assertTrue(rm.removeEvent(rooms.get(0), event2));
        assertFalse(rm.removeEvent(rooms.get(0), event2));
        assertEquals(rooms.get(0).getEvents().size(), 0);
    }

    @Test
    public void testNewEventValidSpeakerOverlap() {
        rooms.add(rm.newRoom());
        rooms.add(rm.newRoom());
        Speaker speaker1 = new Speaker("speaker1");

        assertTrue(rm.newEventValid("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                rooms.get(0)));

        Event event0 = rm.newEvent("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                rooms.get(0));

        assertTrue(rooms.get(0).getEvents().contains(event0));
        assertEquals(rooms.get(0).getEvents().size(), 1);

        assertFalse(rm.newEventValid("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 30, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 30, 0),
                rooms.get(1)));

        assertFalse(rm.newEventValid("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 10, 30, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 30, 0),
                rooms.get(1)));

        assertFalse(rm.newEventValid("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                rooms.get(1)));

        assertTrue(rm.newEventValid("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 10, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                rooms.get(1)));

        assertTrue(rm.newEventValid("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 13, 0, 0),
                rooms.get(1)));

        Event event1 = rm.newEvent("2", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 10, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                rooms.get(1));

        Event event2 = rm.newEvent("2", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 13, 0, 0),
                rooms.get(1));

        assertTrue(rooms.get(0).getEvents().contains(event0));
        assertTrue(rooms.get(1).getEvents().contains(event1));
        assertTrue(rooms.get(1).getEvents().contains(event2));
        assertEquals(rooms.get(0).getEvents().size(), 1);
        assertEquals(rooms.get(1).getEvents().size(), 2);

        assertTrue(rm.removeEvent(rooms.get(0), event0));
        assertFalse(rm.removeEvent(rooms.get(0), event0));
        assertTrue(rm.removeEvent(rooms.get(1), event1));
        assertFalse(rm.removeEvent(rooms.get(0), event1));
        assertTrue(rm.removeEvent(rooms.get(1), event2));
        assertFalse(rm.removeEvent(rooms.get(1), event2));
        assertEquals(rooms.get(0).getEvents().size(), 0);
        assertEquals(rooms.get(0).getEvents().size(), 0);
    }

    @Test
    public void testGetEventAttendeeIDs() {
        rooms.add(rm.newRoom());
        Speaker speaker1 = new Speaker("speaker1");
        Attendee attendee1 = new Attendee("attendee1");
        Attendee attendee2 = new Attendee("attendee2");

        Event event = rm.newEvent("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                rooms.get(0));

        assertEquals(rm.getEventAttendeeIDs(event).size(), 0);

        // Pieced together method of adding an attendee until UserManager implements the relevant methods
        // The next two lines should be a method in UserManager
        attendee1.addEvents(event);
        event.addAttendee(attendee1);
        assertEquals(rm.getEventAttendeeIDs(event).size(), 1);

        attendee2.addEvents(event);
        event.addAttendee(attendee2);
        assertEquals(rm.getEventAttendeeIDs(event).size(), 2);

        assertTrue(rm.getEvents(rooms.get(0)).contains(event));
    }

    @Test
    public void testGetEvent() {
        rooms.add(rm.newRoom());
        Speaker speaker1 = new Speaker("speaker1");
        Event event = rm.newEvent("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                rooms.get(0));

        assertEquals(rm.getEvent(rooms.get(0), event.getEventID()), event);
    }
}