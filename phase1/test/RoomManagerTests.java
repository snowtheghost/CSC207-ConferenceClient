import org.junit.*;
import java.util.*;

import static org.junit.Assert.*;

/**
 * @author Justin Chan
 */

public class RoomManagerTests {
    RoomManager rm = new RoomManager();
    ArrayList<Room> rooms;

    @Before
    public void setUpBefore() {
        rm = new RoomManager();
        rooms = new ArrayList<>();
    }

    @Test
    /*
     * getRooms()
     * newRoom()
     */
    public void testRoomsNormal() {
        assertTrue(rm.getRooms().isEmpty()); // getRooms()
        rooms.add(rm.newRoom()); // newRoom()
        assertEquals(rooms.size(), 1);
        assertEquals(rooms.get(0), rm.getRooms().get(0)); // getRooms()
        rooms.add(rm.newRoom()); // newRoom()
        assertEquals(rooms.size(), 2);
        assertEquals(rooms.get(1), rm.getRooms().get(1)); //getRooms()
    }

    @Test
    /*
     * newEvent()
     * newEventValid()
     * getEvents()
     * getEventsFromRoom()
     * getEvent()
     * getEventRoom()
     * addEventAttendee()
     * removeEventAttendee()
     * removeEvent()
     */
    public void testEventsNormal() {
        UserManager um = new UserManager();
        rooms.add(rm.newRoom());
        rooms.add(rm.newRoom());
        Speaker speaker1 = um.createSpeakerAccount("speaker1");
        Speaker speaker2 = um.createSpeakerAccount("speaker2");

        assertTrue(rm.getEvents().isEmpty()); // getEvents()
        assertTrue(rm.getEventsFromRoom(rooms.get(0)).isEmpty()); // getEventsFromRoom()
        assertTrue(rm.getEventsFromRoom(rooms.get(1)).isEmpty()); // getEventsFromRoom()

        assertEquals(speaker1.getEventsSpeaking().size(), 0);
        assertEquals(speaker2.getEventsSpeaking().size(), 0);

        assertTrue(rm.newEventValid("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                rooms.get(0))); // newEventValid()
        Event event1 = rm.newEvent("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                rooms.get(0)); // newEvent()

        assertEquals(rm.getEventRoom(event1), rooms.get(0)); // getEventRoom()

        assertEquals(speaker1.getEventsSpeaking().size(), 1);
        assertEquals(speaker1.getEventsSpeaking().get(rooms.get(0).getRoomID()).size(), 1);
        assertTrue(speaker1.getEventsSpeaking().containsKey(rooms.get(0).getRoomID()));
        assertTrue(speaker1.getEventsSpeaking().get(rooms.get(0).getRoomID()).contains(event1.getEventID()));

        assertEquals(rm.getEvents().size(), 1); // getEvents()
        assertTrue(rm.getEvents().contains(event1)); // getEvents()
        assertEquals(rm.getEventsFromRoom(rooms.get(0)).size(), 1); // getEventsFromRoom()
        assertTrue(rm.getEventsFromRoom(rooms.get(0)).contains(event1));  // getEventsFromRoom()
        assertEquals(rm.getEventsFromRoom(rooms.get(1)).size(), 0); // getEventsFromRoom()

        assertTrue(rm.newEventValid("2", speaker2,
                new GregorianCalendar(2000, Calendar.MAY, 1, 14, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 15, 0, 0),
                rooms.get(0))); // newEventValid()
        Event event2 = rm.newEvent("2", speaker2,
                new GregorianCalendar(2000, Calendar.MAY, 1, 14, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 15, 0, 0),
                rooms.get(0)); // newEvent()

        assertEquals(rm.getEventRoom(event2), rooms.get(0)); // getEventRoom()

        assertEquals(speaker2.getEventsSpeaking().size(), 1);
        assertEquals(speaker2.getEventsSpeaking().get(rooms.get(0).getRoomID()).size(), 1);
        assertTrue(speaker2.getEventsSpeaking().containsKey(rooms.get(0).getRoomID()));
        assertTrue(speaker2.getEventsSpeaking().get(rooms.get(0).getRoomID()).contains(event2.getEventID()));

        assertEquals(rm.getEvents().size(), 2); // getEvents()
        assertTrue(rm.getEvents().contains(event1)); // getEvents()
        assertTrue(rm.getEvents().contains(event2)); // getEvents()
        assertEquals(rm.getEventsFromRoom(rooms.get(0)).size(), 2); // getEventsFromRoom()
        assertTrue(rm.getEventsFromRoom(rooms.get(0)).contains(event1));  // getEventsFromRoom()
        assertTrue(rm.getEventsFromRoom(rooms.get(0)).contains(event2));  // getEventsFromRoom()
        assertEquals(rm.getEventsFromRoom(rooms.get(1)).size(), 0); // getEventsFromRoom()

        assertTrue(rm.newEventValid("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 9, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 10, 0, 0),
                rooms.get(1))); // newEventValid()
        Event event3 = rm.newEvent("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 9, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 10, 0, 0),
                rooms.get(1)); // newEvent()

        assertEquals(rm.getEventRoom(event3), rooms.get(1)); // getEventRoom()

        assertEquals(speaker1.getEventsSpeaking().size(), 2);
        assertEquals(speaker1.getEventsSpeaking().get(rooms.get(0).getRoomID()).size(), 1);
        assertTrue(speaker1.getEventsSpeaking().containsKey(rooms.get(0).getRoomID()));
        assertEquals(speaker1.getEventsSpeaking().get(rooms.get(1).getRoomID()).size(), 1);
        assertTrue(speaker1.getEventsSpeaking().containsKey(rooms.get(1).getRoomID()));
        assertTrue(speaker1.getEventsSpeaking().get(rooms.get(1).getRoomID()).contains(event3.getEventID()));

        assertEquals(rm.getEvents().size(), 3); // getEvents()
        assertTrue(rm.getEvents().contains(event1)); // getEvents()
        assertTrue(rm.getEvents().contains(event2)); // getEvents()
        assertTrue(rm.getEvents().contains(event3)); // getEvents()
        assertEquals(rm.getEventsFromRoom(rooms.get(0)).size(), 2); // getEventsFromRoom()
        assertTrue(rm.getEventsFromRoom(rooms.get(0)).contains(event1));  // getEventsFromRoom()
        assertTrue(rm.getEventsFromRoom(rooms.get(0)).contains(event2));  // getEventsFromRoom()
        assertEquals(rm.getEventsFromRoom(rooms.get(1)).size(), 1); // getEventsFromRoom()
        assertTrue(rm.getEventsFromRoom(rooms.get(1)).contains(event3));  // getEventsFromRoom()

        assertTrue(rm.newEventValid("2", speaker2,
                new GregorianCalendar(2000, Calendar.MAY, 1, 15, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 16, 0, 0),
                rooms.get(1))); // newEventValid()
        Event event4 = rm.newEvent("2", speaker2,
                new GregorianCalendar(2000, Calendar.MAY, 1, 15, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 16, 0, 0),
                rooms.get(1)); // newEvent()

        assertEquals(rm.getEventRoom(event4), rooms.get(1)); // getEventRoom()

        assertEquals(speaker2.getEventsSpeaking().size(), 2);
        assertEquals(speaker2.getEventsSpeaking().get(rooms.get(0).getRoomID()).size(), 1);
        assertTrue(speaker2.getEventsSpeaking().containsKey(rooms.get(0).getRoomID()));
        assertEquals(speaker2.getEventsSpeaking().get(rooms.get(1).getRoomID()).size(), 1);
        assertTrue(speaker2.getEventsSpeaking().containsKey(rooms.get(1).getRoomID()));
        assertTrue(speaker2.getEventsSpeaking().get(rooms.get(1).getRoomID()).contains(event4.getEventID()));

        assertEquals(rm.getEvents().size(), 4); // getEvents()
        assertTrue(rm.getEvents().contains(event1)); // getEvents()
        assertTrue(rm.getEvents().contains(event2)); // getEvents()
        assertTrue(rm.getEvents().contains(event3)); // getEvents()
        assertEquals(rm.getEventsFromRoom(rooms.get(0)).size(), 2); // getEventsFromRoom()
        assertTrue(rm.getEventsFromRoom(rooms.get(0)).contains(event1));  // getEventsFromRoom()
        assertTrue(rm.getEventsFromRoom(rooms.get(0)).contains(event2));  // getEventsFromRoom()
        assertEquals(rm.getEventsFromRoom(rooms.get(1)).size(), 2); // getEventsFromRoom()
        assertTrue(rm.getEventsFromRoom(rooms.get(1)).contains(event3));  // getEventsFromRoom()
        assertTrue(rm.getEventsFromRoom(rooms.get(1)).contains(event4));  // getEventsFromRoom()

        assertEquals(rm.getEvent(event1.getEventID()), event1); // getEvent()
        assertEquals(rm.getEvent(event2.getEventID()), event2); // getEvent()
        assertEquals(rm.getEvent(event3.getEventID()), event3); // getEvent()
        assertEquals(rm.getEvent(event4.getEventID()), event4); // getEvent()

        Attendee attendee1 = um.createAttendeeAccount("attendee1");
        Attendee attendee2 = um.createAttendeeAccount("attendee2");
        Attendee attendee3 = um.createAttendeeAccount("attendee3");
        Attendee attendee4 = um.createAttendeeAccount("attendee4");

        rm.addEventAttendee(attendee1, event1); // addEventAttendee
        rm.addEventAttendee(attendee1, event2); rm.addEventAttendee(attendee2, event2);
        rm.addEventAttendee(attendee1, event3); rm.addEventAttendee(attendee2, event3); rm.addEventAttendee(attendee3, event3);
        rm.addEventAttendee(attendee1, event4); rm.addEventAttendee(attendee2, event4); rm.addEventAttendee(attendee3, event4); rm.addEventAttendee(attendee4, event4);

        assertEquals(attendee1.getEvents().size(), 2);
        assertEquals(attendee2.getEvents().size(), 2);
        assertEquals(attendee3.getEvents().size(), 1);
        assertEquals(attendee4.getEvents().size(), 1);

        assertTrue(speaker1.getEventsSpeaking().containsKey(rooms.get(1).getRoomID()));
        assertTrue(speaker1.getEventsSpeaking().containsKey(rooms.get(1).getRoomID()));
        assertTrue(speaker2.getEventsSpeaking().containsKey(rooms.get(1).getRoomID()));
        assertTrue(speaker2.getEventsSpeaking().containsKey(rooms.get(1).getRoomID()));
        assertEquals(attendee1.getEvents().get(rooms.get(0).getRoomID()).size(), 2);
        assertEquals(attendee1.getEvents().get(rooms.get(1).getRoomID()).size(), 2);
        assertEquals(attendee2.getEvents().get(rooms.get(0).getRoomID()).size(), 1);
        assertEquals(attendee2.getEvents().get(rooms.get(1).getRoomID()).size(), 2);
        assertFalse(attendee3.getEvents().containsKey(rooms.get(0).getRoomID()));
        assertEquals(attendee3.getEvents().get(rooms.get(1).getRoomID()).size(), 2);
        assertFalse(attendee4.getEvents().containsKey(rooms.get(0).getRoomID()));
        assertEquals(attendee4.getEvents().get(rooms.get(1).getRoomID()).size(), 1);

        assertTrue(rm.removeEvent(um, event1)); // removeEvent()
        assertFalse(speaker1.getEventsSpeaking().containsKey(rooms.get(0).getRoomID()));
        assertTrue(speaker1.getEventsSpeaking().containsKey(rooms.get(1).getRoomID()));
        assertTrue(speaker2.getEventsSpeaking().containsKey(rooms.get(0).getRoomID()));
        assertTrue(speaker2.getEventsSpeaking().containsKey(rooms.get(1).getRoomID()));
        assertEquals(attendee1.getEvents().get(rooms.get(0).getRoomID()).size(), 1);
        assertEquals(attendee1.getEvents().get(rooms.get(1).getRoomID()).size(), 2);
        assertEquals(attendee2.getEvents().get(rooms.get(0).getRoomID()).size(), 1);
        assertEquals(attendee2.getEvents().get(rooms.get(1).getRoomID()).size(), 2);
        assertFalse(attendee3.getEvents().containsKey(rooms.get(0).getRoomID()));
        assertEquals(attendee3.getEvents().get(rooms.get(1).getRoomID()).size(), 2);
        assertFalse(attendee4.getEvents().containsKey(rooms.get(0).getRoomID()));
        assertEquals(attendee4.getEvents().get(rooms.get(1).getRoomID()).size(), 1);
        assertFalse(rm.removeEvent(um, event1));

        assertTrue(rm.removeEventAttendee(attendee1, event3)); // removeEventAttendee()
        assertEquals(attendee1.getEvents().get(rooms.get(0).getRoomID()).size(), 1);
        assertEquals(attendee1.getEvents().get(rooms.get(1).getRoomID()).size(), 1);
        assertFalse(rm.removeEventAttendee(attendee1, event3));

        assertTrue(rm.removeEvent(um, event3)); // removeEvent()
        assertFalse(speaker1.getEventsSpeaking().containsKey(rooms.get(0).getRoomID()));
        assertFalse(speaker1.getEventsSpeaking().containsKey(rooms.get(1).getRoomID()));
        assertTrue(speaker2.getEventsSpeaking().containsKey(rooms.get(0).getRoomID()));
        assertTrue(speaker2.getEventsSpeaking().containsKey(rooms.get(1).getRoomID()));
        assertEquals(attendee1.getEvents().get(rooms.get(0).getRoomID()).size(), 1);
        assertEquals(attendee1.getEvents().get(rooms.get(1).getRoomID()).size(), 1);
        assertEquals(attendee2.getEvents().get(rooms.get(0).getRoomID()).size(), 1);
        assertEquals(attendee2.getEvents().get(rooms.get(1).getRoomID()).size(), 1);
        assertFalse(attendee3.getEvents().containsKey(rooms.get(0).getRoomID()));
        assertEquals(attendee3.getEvents().get(rooms.get(1).getRoomID()).size(), 1);
        assertFalse(attendee4.getEvents().containsKey(rooms.get(0).getRoomID()));
        assertEquals(attendee4.getEvents().get(rooms.get(1).getRoomID()).size(), 1);
        assertFalse(rm.removeEvent(um, event3));

        assertTrue(rm.removeEventAttendee(attendee2, event4)); // removeEventAttendee()
        assertEquals(attendee2.getEvents().get(rooms.get(0).getRoomID()).size(), 1);
        assertFalse(attendee2.getEvents().containsKey(rooms.get(1).getRoomID()));
        assertFalse(rm.removeEventAttendee(attendee2, event4));

        assertTrue(rm.removeEvent(um, event4)); // removeEvent()
        assertFalse(speaker1.getEventsSpeaking().containsKey(rooms.get(0).getRoomID()));
        assertFalse(speaker1.getEventsSpeaking().containsKey(rooms.get(1).getRoomID()));
        assertTrue(speaker2.getEventsSpeaking().containsKey(rooms.get(0).getRoomID()));
        assertFalse(speaker2.getEventsSpeaking().containsKey(rooms.get(1).getRoomID()));
        assertEquals(attendee1.getEvents().get(rooms.get(0).getRoomID()).size(), 1);
        assertFalse(attendee1.getEvents().containsKey(rooms.get(1).getRoomID()));
        assertEquals(attendee2.getEvents().get(rooms.get(0).getRoomID()).size(), 1);
        assertFalse(attendee2.getEvents().containsKey(rooms.get(1).getRoomID()));
        assertFalse(attendee3.getEvents().containsKey(rooms.get(0).getRoomID()));
        assertFalse(attendee3.getEvents().containsKey(rooms.get(1).getRoomID()));
        assertFalse(attendee4.getEvents().containsKey(rooms.get(0).getRoomID()));
        assertFalse(attendee4.getEvents().containsKey(rooms.get(1).getRoomID()));
        assertFalse(rm.removeEvent(um, event4));

        assertTrue(rm.removeEvent(um, event2)); // removeEvent()
        assertFalse(speaker1.getEventsSpeaking().containsKey(rooms.get(0).getRoomID()));
        assertFalse(speaker1.getEventsSpeaking().containsKey(rooms.get(1).getRoomID()));
        assertFalse(speaker2.getEventsSpeaking().containsKey(rooms.get(0).getRoomID()));
        assertFalse(speaker2.getEventsSpeaking().containsKey(rooms.get(1).getRoomID()));
        assertFalse(attendee1.getEvents().containsKey(rooms.get(0).getRoomID()));
        assertFalse(attendee1.getEvents().containsKey(rooms.get(1).getRoomID()));
        assertFalse(attendee2.getEvents().containsKey(rooms.get(0).getRoomID()));
        assertFalse(attendee2.getEvents().containsKey(rooms.get(1).getRoomID()));
        assertFalse(attendee3.getEvents().containsKey(rooms.get(0).getRoomID()));
        assertFalse(attendee3.getEvents().containsKey(rooms.get(1).getRoomID()));
        assertFalse(attendee4.getEvents().containsKey(rooms.get(0).getRoomID()));
        assertFalse(attendee4.getEvents().containsKey(rooms.get(1).getRoomID()));
        assertFalse(rm.removeEvent(um, event2));
    }

    @Test
    public void testNewEventValidBounds() {
        rooms.add(rm.newRoom());
        Speaker speaker1 = new Speaker("speaker1");

        assertFalse(rm.newEventValid("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 1, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 2, 0, 0),
                rooms.get(0)));

        assertFalse(rm.newEventValid("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 17, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 18, 0, 0),
                rooms.get(0)));

        assertFalse(rm.newEventValid("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 8, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 9, 0, 0),
                rooms.get(0)));

        assertFalse(rm.newEventValid("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 16, 30, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 17, 30, 0),
                rooms.get(0)));

        assertFalse(rm.newEventValid("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 8, 30, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 9, 30, 0),
                rooms.get(0)));

        assertTrue(rm.newEventValid("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 9, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 10, 0, 0),
                rooms.get(0)));

        assertTrue(rm.newEventValid("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 16, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 17, 0, 0),
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

        Event event1 = rm.newEvent("1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                rooms.get(0));

        assertEquals(rooms.get(0).getEvents().size(), 1);
        assertTrue(rooms.get(0).getEvents().contains(event1));

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

        assertEquals(rooms.get(0).getEvents().size(), 1);
        assertTrue(rooms.get(0).getEvents().contains(event0));

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
        assertTrue(rm.addEventAttendee(attendee1, event));
        assertFalse(rm.addEventAttendee(attendee1, event));
        assertEquals(rm.getEventAttendeeIDs(event).size(), 1);

        assertTrue(rm.addEventAttendee(attendee2, event));
        assertFalse(rm.addEventAttendee(attendee2, event));
        assertEquals(rm.getEventAttendeeIDs(event).size(), 2);

        assertTrue(rm.getEventsFromRoom(rooms.get(0)).contains(event));

        assertTrue(rm.removeEventAttendee(attendee1, event));
        assertFalse(rm.removeEventAttendee(attendee1, event));
        assertEquals(rm.getEventAttendeeIDs(event).size(), 1);

        assertTrue(rm.removeEventAttendee(attendee2, event));
        assertFalse(rm.removeEventAttendee(attendee2, event));
        assertEquals(rm.getEventAttendeeIDs(event).size(), 0);
    }

    @Test
    public void testRescheduleEvent() {
        UserManager um = new UserManager();
        rooms.add(rm.newRoom());
        Speaker speaker1 = um.createSpeakerAccount("speaker1");
        Speaker speaker2 = um.createSpeakerAccount("speaker2");

        Event event1 = rm.newEvent("Mover", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 11, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                rooms.get(0));

        rm.newEvent("Blocker", speaker2,
                new GregorianCalendar(2000, Calendar.MAY, 1, 13, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 14, 0, 0),
                rooms.get(0));

        assertTrue(rm.rescheduleEvent(um, event1, new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 13, 0, 0)));
        assertEquals(event1.getStartTime(), new GregorianCalendar(2000, Calendar.MAY, 1, 12, 0, 0));
        assertEquals(event1.getEndTime(), new GregorianCalendar(2000, Calendar.MAY, 1, 13, 0, 0));

        assertTrue(rm.rescheduleEvent(um, event1, new GregorianCalendar(2000, Calendar.MAY, 1, 14, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 15, 0, 0)));
        assertEquals(event1.getStartTime(), new GregorianCalendar(2000, Calendar.MAY, 1, 14, 0, 0));
        assertEquals(event1.getEndTime(), new GregorianCalendar(2000, Calendar.MAY, 1, 15, 0, 0));

        assertTrue(rm.rescheduleEvent(um, event1, new GregorianCalendar(2000, Calendar.MAY, 1, 9, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 10, 0, 0)));
        assertEquals(event1.getStartTime(), new GregorianCalendar(2000, Calendar.MAY, 1, 9, 0, 0));
        assertEquals(event1.getEndTime(), new GregorianCalendar(2000, Calendar.MAY, 1, 10, 0, 0));

        assertTrue(rm.rescheduleEvent(um, event1, new GregorianCalendar(2000, Calendar.MAY, 1, 16, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 17, 0, 0)));
        assertEquals(event1.getStartTime(), new GregorianCalendar(2000, Calendar.MAY, 1, 16, 0, 0));
        assertEquals(event1.getEndTime(), new GregorianCalendar(2000, Calendar.MAY, 1, 17, 0, 0));

        assertFalse(rm.rescheduleEvent(um, event1, new GregorianCalendar(2000, Calendar.MAY, 1, 13, 30, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 14, 30, 0)));
        assertEquals(event1.getStartTime(), new GregorianCalendar(2000, Calendar.MAY, 1, 16, 0, 0));
        assertEquals(event1.getEndTime(), new GregorianCalendar(2000, Calendar.MAY, 1, 17, 0, 0));

        assertFalse(rm.rescheduleEvent(um, event1, new GregorianCalendar(2000, Calendar.MAY, 1, 13, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 14, 0, 0)));
        assertEquals(event1.getStartTime(), new GregorianCalendar(2000, Calendar.MAY, 1, 16, 0, 0));
        assertEquals(event1.getEndTime(), new GregorianCalendar(2000, Calendar.MAY, 1, 17, 0, 0));

        assertFalse(rm.rescheduleEvent(um, event1, new GregorianCalendar(2000, Calendar.MAY, 1, 12, 30, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 13, 30, 0)));
        assertEquals(event1.getStartTime(), new GregorianCalendar(2000, Calendar.MAY, 1, 16, 0, 0));
        assertEquals(event1.getEndTime(), new GregorianCalendar(2000, Calendar.MAY, 1, 17, 0, 0));

        assertFalse(rm.rescheduleEvent(um, event1, new GregorianCalendar(2000, Calendar.MAY, 1, 16, 30, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 17, 30, 0)));
        assertEquals(event1.getStartTime(), new GregorianCalendar(2000, Calendar.MAY, 1, 16, 0, 0));
        assertEquals(event1.getEndTime(), new GregorianCalendar(2000, Calendar.MAY, 1, 17, 0, 0));

        assertFalse(rm.rescheduleEvent(um, event1, new GregorianCalendar(2000, Calendar.MAY, 1, 8, 30, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 9, 30, 0)));
        assertEquals(event1.getStartTime(), new GregorianCalendar(2000, Calendar.MAY, 1, 16, 0, 0));
        assertEquals(event1.getEndTime(), new GregorianCalendar(2000, Calendar.MAY, 1, 17, 0, 0));

        assertFalse(rm.rescheduleEvent(um, event1, new GregorianCalendar(2000, Calendar.MAY, 1, 8, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 9, 0, 0)));
        assertEquals(event1.getStartTime(), new GregorianCalendar(2000, Calendar.MAY, 1, 16, 0, 0));
        assertEquals(event1.getEndTime(), new GregorianCalendar(2000, Calendar.MAY, 1, 17, 0, 0));

        assertFalse(rm.rescheduleEvent(um, event1, new GregorianCalendar(2000, Calendar.MAY, 1, 17, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 18, 0, 0)));
        assertEquals(event1.getStartTime(), new GregorianCalendar(2000, Calendar.MAY, 1, 16, 0, 0));
        assertEquals(event1.getEndTime(), new GregorianCalendar(2000, Calendar.MAY, 1, 17, 0, 0));

        assertFalse(rm.rescheduleEvent(um, event1, new GregorianCalendar(2000, Calendar.MAY, 1, 20, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 21, 0, 0)));
        assertEquals(event1.getStartTime(), new GregorianCalendar(2000, Calendar.MAY, 1, 16, 0, 0));
        assertEquals(event1.getEndTime(), new GregorianCalendar(2000, Calendar.MAY, 1, 17, 0, 0));
    }
}