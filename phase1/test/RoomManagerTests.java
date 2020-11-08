import org.junit.*;
import java.util.*;

import static org.junit.Assert.*;

/**
 * @author Justin Chan
 */

public class RoomManagerTests {
    RoomManager rm = new RoomManager();
    ArrayList<UUID> roomIDs = new ArrayList<>();

    @Before
    public void setUpBefore() {
        rm = new RoomManager();
        roomIDs = new ArrayList<>();
    }

    @Test
    public void testNewRoomGetRoom() {
        roomIDs.add(rm.newRoom());
        assertEquals(roomIDs.size(), 1);
        assertEquals(rm.getRoom(roomIDs.get(0)), rm.getRooms().get(0));
        roomIDs.add(rm.newRoom());
        assertEquals(roomIDs.size(), 2);
        assertEquals(rm.getRoom(roomIDs.get(1)), rm.getRooms().get(1));
    }

    @Test
    public void testNewEventValidNormal() {
        roomIDs.add(rm.newRoom());
        Speaker speaker1 = new Speaker("speaker1");

        assertTrue(rm.newEventValid("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                roomIDs.get(0)));
    }

    @Test
    public void testNewEventValidOOB() {
        roomIDs.add(rm.newRoom());
        Speaker speaker1 = new Speaker("speaker1");

        assertFalse(rm.newEventValid("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 1, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 2, 0, 0),
                roomIDs.get(0)));
    }

    @Test
    public void testNewEventValidOverlap() {
        roomIDs.add(rm.newRoom());
        Speaker speaker1 = new Speaker("speaker1");
        Speaker speaker2 = new Speaker("speaker2");

        assertTrue(rm.newEventValid("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                roomIDs.get(0)));

        UUID eventID = rm.newEvent("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                roomIDs.get(0));

        assertTrue(rm.getRoom(roomIDs.get(0)).getEventIDs().contains(eventID));
        assertEquals(rm.getRoom(roomIDs.get(0)).getEventIDs().size(), 1);

        assertFalse(rm.newEventValid("2", speaker2,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 30, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 30, 0),
                roomIDs.get(0)));

        assertFalse(rm.newEventValid("2", speaker2,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                roomIDs.get(0)));

        assertFalse(rm.newEventValid("2", speaker2,
                new GregorianCalendar(2000, Calendar.MAY, 1, 10, 30, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 30, 0),
                roomIDs.get(0)));

        assertTrue(rm.newEventValid("2", speaker2,
                new GregorianCalendar(2000, Calendar.MAY, 1, 10, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                roomIDs.get(0)));

        assertTrue(rm.newEventValid("2", speaker2,
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 13, 0, 0),
                roomIDs.get(0)));

        rm.newEvent("2", speaker2,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 30, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 30, 0),
                roomIDs.get(0));

        rm.newEvent("2", speaker2,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                roomIDs.get(0));

        rm.newEvent("2", speaker2,
                new GregorianCalendar(2000, Calendar.MAY, 1, 10, 30, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 30, 0),
                roomIDs.get(0));

        UUID eventID1 = rm.newEvent("2", speaker2,
                new GregorianCalendar(2000, Calendar.MAY, 1, 10, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                roomIDs.get(0));

        UUID eventID2 = rm.newEvent("2", speaker2,
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 13, 0, 0),
                roomIDs.get(0));

        assertTrue(rm.getRoom(roomIDs.get(0)).getEventIDs().contains(eventID));
        assertTrue(rm.getRoom(roomIDs.get(0)).getEventIDs().contains(eventID1));
        assertTrue(rm.getRoom(roomIDs.get(0)).getEventIDs().contains(eventID2));
        assertEquals(rm.getRoom(roomIDs.get(0)).getEventIDs().size(), 3);

        assertTrue(rm.removeEvent(roomIDs.get(0), eventID));
        assertFalse(rm.removeEvent(roomIDs.get(0), eventID));
        assertTrue(rm.removeEvent(roomIDs.get(0), eventID1));
        assertFalse(rm.removeEvent(roomIDs.get(0), eventID1));
        assertTrue(rm.removeEvent(roomIDs.get(0), eventID2));
        assertFalse(rm.removeEvent(roomIDs.get(0), eventID2));
        assertEquals(rm.getRoom(roomIDs.get(0)).getEventIDs().size(), 0);
    }

    @Test
    public void testNewEventValidSpeakerOverlap() {
        roomIDs.add(rm.newRoom());
        roomIDs.add(rm.newRoom());
        Speaker speaker1 = new Speaker("speaker1");

        assertTrue(rm.newEventValid("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                roomIDs.get(0)));

        UUID eventID = rm.newEvent("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                roomIDs.get(0));

        assertTrue(rm.getRoom(roomIDs.get(0)).getEventIDs().contains(eventID));
        assertEquals(rm.getRoom(roomIDs.get(0)).getEventIDs().size(), 1);

        assertFalse(rm.newEventValid("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 30, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 30, 0),
                roomIDs.get(1)));

        assertFalse(rm.newEventValid("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 10, 30, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 30, 0),
                roomIDs.get(1)));

        assertFalse(rm.newEventValid("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                roomIDs.get(1)));

        assertTrue(rm.newEventValid("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 10, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                roomIDs.get(1)));

        assertTrue(rm.newEventValid("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 13, 0, 0),
                roomIDs.get(1)));

        UUID eventID1 = rm.newEvent("2", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 10, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                roomIDs.get(1));

        UUID eventID2 = rm.newEvent("2", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 13, 0, 0),
                roomIDs.get(1));

        assertTrue(rm.getRoom(roomIDs.get(0)).getEventIDs().contains(eventID));
        assertTrue(rm.getRoom(roomIDs.get(1)).getEventIDs().contains(eventID1));
        assertTrue(rm.getRoom(roomIDs.get(1)).getEventIDs().contains(eventID2));
        assertEquals(rm.getRoom(roomIDs.get(0)).getEventIDs().size(), 1);
        assertEquals(rm.getRoom(roomIDs.get(1)).getEventIDs().size(), 2);

        assertTrue(rm.removeEvent(roomIDs.get(0), eventID));
        assertFalse(rm.removeEvent(roomIDs.get(0), eventID));
        assertTrue(rm.removeEvent(roomIDs.get(1), eventID1));
        assertFalse(rm.removeEvent(roomIDs.get(0), eventID1));
        assertTrue(rm.removeEvent(roomIDs.get(1), eventID2));
        assertFalse(rm.removeEvent(roomIDs.get(1), eventID2));
        assertEquals(rm.getRoom(roomIDs.get(0)).getEventIDs().size(), 0);
        assertEquals(rm.getRoom(roomIDs.get(1)).getEventIDs().size(), 0);
    }

    @Test
    public void testGetEventAttendeeIDs() {
        roomIDs.add(rm.newRoom());
        Speaker speaker1 = new Speaker("speaker1");
        Attendee attendee1 = new Attendee("attendee1");
        Attendee attendee2 = new Attendee("attendee2");

        UUID eventID = rm.newEvent("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                roomIDs.get(0));

        assertEquals(rm.getEventAttendeeIDs(roomIDs.get(0), eventID).size(), 0);

        // Pieced together method of adding an attendee until UserManager implements the relevant methods
        attendee1.addEvents(new Event[] {rm.getEvent(roomIDs.get(0), eventID)}); // This implementation in User may change
        rm.getEvent(roomIDs.get(0), eventID).addAttendee(attendee1);
        assertEquals(rm.getEventAttendeeIDs(roomIDs.get(0), eventID).size(), 1);
    }
}