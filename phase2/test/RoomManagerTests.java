import com.group0179.use_cases.RoomManager;
import com.group0179.use_cases.UserManager;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * @author Arya Joshi
 */

public class RoomManagerTests {
    RoomManager rm = new RoomManager();
    UserManager um = new UserManager();
    Calendar startTime1 = new GregorianCalendar(2020, Calendar.MAY, 1, 11, 0, 0);
    Calendar endTime1 = new GregorianCalendar(2020, Calendar.MAY, 1, 12, 0, 0);
    Calendar startTime2 = new GregorianCalendar(2020, Calendar.MAY, 1, 13, 0, 0);
    Calendar endTime2 = new GregorianCalendar(2020, Calendar.MAY, 1, 14, 0, 0);
    Calendar startTime3 = new GregorianCalendar(2020, Calendar.MAY, 1, 11, 30, 0);
    Calendar endTime3 = new GregorianCalendar(2020, Calendar.MAY, 1, 12, 30, 0);
    Calendar startTime4 = new GregorianCalendar(2020, Calendar.MAY, 1, 4, 0, 0);
    Calendar endTime4 = new GregorianCalendar(2020, Calendar.MAY, 1, 5, 0, 0);
    Calendar startTime5 = new GregorianCalendar(2020, Calendar.MAY, 1, 20, 0, 0);
    Calendar endTime5 = new GregorianCalendar(2020, Calendar.MAY, 1, 21, 0, 0);

    @Test
    public void testNewRoom() {
        assertEquals(rm.getNumRooms(), 0);
        rm.newRoom(20);
        assertEquals(rm.getNumRooms(), 1);
        rm.newRoom(20);
        assertEquals(rm.getNumRooms(), 2);
    }

    @Test
    public void testNewEventValid() {
        rm.newRoom(20);

        Random random = new Random();
        int roomNumber = random.nextInt(rm.getNumRooms());
        um.createSpeakerAccount("John");

        //One Speaker, one room:
        assertTrue(rm.newEventValid("Speech 1", "John", startTime1, endTime1, roomNumber, um));
        assertFalse(rm.newEventValid("Speech 1", "John", startTime4, endTime4, roomNumber, um));
        assertFalse(rm.newEventValid("Speech 1", "John", startTime5, endTime5, roomNumber, um));

        rm.newEvent("Speech 1", "John", startTime1, endTime1, roomNumber, um, 20);
        assertTrue(rm.newEventValid("Speech 2", "John", startTime2, endTime2, roomNumber, um));
        assertFalse(rm.newEventValid("Speech 1", "John", startTime1, endTime1, roomNumber, um));
        assertFalse(rm.newEventValid("Speech 2", "John", startTime3, endTime3, roomNumber, um));

        //Multiple speakers, one room
        um.createSpeakerAccount("Jack");
        assertTrue(rm.newEventValid("Speech 2", "Jack", startTime2, endTime2, roomNumber, um));
        assertFalse(rm.newEventValid("Speech 1", "Jack", startTime1, endTime1, roomNumber, um));

        //Multiple speakers, multiple rooms
        rm.removeEvent(um, roomNumber, 0);
        rm.newRoom(20);
        roomNumber = random.nextInt(rm.getNumRooms());

        assertTrue(rm.newEventValid("Speech 1", "Jack", startTime1, endTime1, roomNumber, um));

        rm.newEvent("Speech 1", "John", startTime1, endTime1, 0, um, 20);
        assertTrue(rm.newEventValid("Speech 1", "Jack", startTime1, endTime1, 1, um));
        assertFalse(rm.newEventValid("Speech 1", "John", startTime1, endTime1, 1, um));
    }

    @Test
    public void testNewEvent() {
        assertEquals(rm.getNumEvents(), 0);

        rm.newRoom(20);
        um.createSpeakerAccount("John");

        UUID eventID = rm.newEvent("Speech 1", "John", startTime1, endTime1, 0, um, 20);

        assertEquals(rm.getNumEvents(), 1);
        assertNotNull(eventID);
    }

    @Test
    public void testGetNumEventsInRoom() {
        rm.newRoom(20);
        assertEquals(rm.getNumEventsInRoom(0), 0);

        um.createSpeakerAccount("John");
        rm.newEvent("Speech 1", "John", startTime1, endTime1, 0, um, 20);
        assertEquals(rm.getNumEventsInRoom(0), 1);
    }

    @Test
    public void testGetEventIDs() {
        ArrayList<UUID> eventIDs= rm.getEventIDs();
        assertEquals(eventIDs.size(), 0);

        rm.newRoom(20);
        rm.newRoom(20);
        rm.newRoom(20);
        um.createSpeakerAccount("John");
        um.createSpeakerAccount("Jack");
        um.createSpeakerAccount("Jason");
        UUID eventID1 = rm.newEvent("Speech 1", "John", startTime1, endTime1, 0, um, 20);
        UUID eventID2 = rm.newEvent("Speech 2", "Jack", startTime1, endTime1, 1, um, 20);
        UUID eventID3 = rm.newEvent("Speech 3", "Jason", startTime1, endTime1, 2, um, 20);

        ArrayList<UUID> new_eventIDs = rm.getEventIDs();
        assertEquals(new_eventIDs.size(), 3);
        assertEquals(new_eventIDs.get(0), eventID1);
        assertEquals(new_eventIDs.get(1), eventID2);
        assertEquals(new_eventIDs.get(2), eventID3);

    }


    @Test
    public void testRescheduleEvent() {
        rm.newRoom(20);
        Random random = new Random();

        um.createSpeakerAccount("John");
        rm.newEvent("Speech 1", "John", startTime1, endTime1, 0, um, 20);

        assertTrue(rm.rescheduleEvent(um, 0, 0, startTime2, endTime2));
        assertFalse(rm.rescheduleEvent(um, 0, 0, startTime2, endTime2));
    }

    @Test
    public void testRemoveEvent() {
        rm.newRoom(20);
        um.createSpeakerAccount("Josh");
        rm.newEvent("Speech 1", "Josh", startTime1, endTime1, 0, um, 20);

        rm.removeEvent(um, 0, 0);
        assertEquals(rm.getNumEvents(), 0);
    }


    @Test
    public void testAddEventAttendee() {
        rm.newRoom(20);
        um.createSpeakerAccount("John");
        UUID eventID1 = rm.newEvent("Speech 1", "John", startTime1, endTime1, 0, um, 1);

        assertEquals(rm.getEventAttendeeIDs(eventID1).size(), 0);

        um.createAttendeeAccount("Chad");
        UUID attendeeID = um.getAttendeeUUIDs().get(0);
        assertTrue(rm.addEventAttendee(attendeeID, eventID1, um, false));

        um.createAttendeeAccount("Sad");
        UUID attendeeID2 = um.getAttendeeUUIDs().get(0);
        assertFalse(rm.addEventAttendee(attendeeID2, eventID1, um, false));

        assertEquals(rm.getEventAttendeeIDs(eventID1).size(), 1);
        
    }

    @Test
    public void testRemoveEventAttendee() {
        rm.newRoom(20);
        um.createSpeakerAccount("John");
        UUID eventID1 = rm.newEvent("Speech 1", "John", startTime1, endTime1, 0, um, 20);
        um.createAttendeeAccount("Chad");
        UUID attendeeID = um.getAttendeeUUIDs().get(0);
        rm.addEventAttendee(attendeeID, eventID1, um, false);
        rm.removeEventAttendee(attendeeID, 0, 0, um);

        assertEquals(rm.getEventAttendeeIDs(eventID1).size(), 0);
    }

    @Test
    public void testChangeGetVipStatus(){
        rm.newRoom(20);
        um.createSpeakerAccount("John");
        UUID eventID1 = rm.newEvent("Speech 1", "John", startTime1, endTime1, 0, um, 1);
        assertFalse(rm.getVipStatus(eventID1));
        rm.changeVipStatus(eventID1);
        assertTrue(rm.getVipStatus(eventID1));

    }
}