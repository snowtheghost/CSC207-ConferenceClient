import org.junit.*;
import static org.junit.Assert.*;

import java.util.UUID;

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
        event1 = new Event("event1", speaker1, new int[]{10, 1, 2021}, 0100);
    }

    // initial number of candies
    @Test
    public void testGetName() {
        assertEquals("event1", event1.getName());
    }

    @Test
    public void testGetEventID() {
        UUID uuid = event1.getEventID();
        assertEquals(event1.getEventID(), uuid);
    }

    @Test
    public void testGetDate() {
        int[] arrayCompare = new int[]{10, 01, 2021};
        for (int i = 0; i < 3; i++) {
            assertEquals(event1.getDate()[i], arrayCompare[i]);
        }
    }

    @Test
    public void testGetTime() {
        assertEquals(event1.getTime(), 0100);
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