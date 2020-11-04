import org.junit.*;
import static org.junit.Assert.*;

import java.util.*;

/**
 * @author Justin Chan
 */

public class EventTests {
    Speaker speaker1 = new Speaker("speaker1");
    Attendee attendee1 = new Attendee("attendee1");
    Attendee attendee2 = new Attendee("attendee2");
    Attendee attendee3 = new Attendee("attendee3");

    Event event1;

    @Before
    public void setUpBefore() {
        event1 = new Event("event1", speaker1,
                new GregorianCalendar(2000, Calendar.MAY, 1, 100, 0, 0),
                new GregorianCalendar(2000, Calendar.MAY, 1, 100, 30, 0));
    }

    // initial number of candies
    @Test
    public void testGetTitle() {
        assertEquals("event1", event1.getTitle());
    }

    @Test
    public void testGetEventID() {
        UUID uuid = event1.getEventID();
        assertEquals(event1.getEventID(), uuid);
    }

    @Test
    public void testGetStartTime() {
        assertEquals(event1.getStartTime(), new GregorianCalendar(2000, Calendar.MAY, 1, 100, 0, 0));
    }

    @Test
    public void testGetEndTime() {
        assertEquals(event1.getEndTime(), new GregorianCalendar(2000, Calendar.MAY, 1, 100, 30, 0));
    }

    @Test
    public void testGetAddRemoveAttendeeIDs() {
        assertEquals(event1.getAttendeeIDs().size(), 0);

        assertEquals(event1.addAttendeeIDs(new Attendee[] {attendee1}), 0);
        assertEquals(event1.getAttendeeIDs().size(), 1);
        assertEquals(event1.getAttendeeIDs().get(0), attendee1.getUserID());

        assertEquals(event1.addAttendeeIDs(new Attendee[] {attendee1}), 1);
        assertEquals(event1.getAttendeeIDs().size(), 1);
        assertEquals(event1.getAttendeeIDs().get(0), attendee1.getUserID());

        Attendee[] attendees = new Attendee[] {attendee1, attendee2, attendee3};
        assertEquals(event1.addAttendeeIDs(attendees), 1);
        assertEquals(event1.getAttendeeIDs().size(), 3);

        for (Attendee attendee : attendees) {
            assertTrue(event1.getAttendeeIDs().contains(attendee.getUserID()));
        }

        assertTrue(event1.removeAttendeeID(attendee1));
        assertEquals(event1.getAttendeeIDs().size(), 2);
        for (Attendee attendee : attendees) {
            if (attendee1.equals(attendee)) {
                assertFalse(event1.getAttendeeIDs().contains(attendee.getUserID()));
            } else {
                assertTrue(event1.getAttendeeIDs().contains(attendee.getUserID()));
            }
        }
    }

    @Test
    public void testGetSpeakerID() {
        assertEquals(event1.getSpeakerID(), speaker1.getUserID());
    }
}