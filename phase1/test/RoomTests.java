import org.junit.*;
import static org.junit.Assert.*;

import java.util.*;

/**
 * @author Justin Chan
 */

public class RoomTests {
    Speaker speakerA = new Speaker("A");
    Speaker speakerB = new Speaker("B");
    Speaker speakerC = new Speaker("C");
    Speaker speakerD = new Speaker("D");

    GregorianCalendar timeStartNormal = new GregorianCalendar(2020, Calendar.MAY, 14, 12, 30, 0);
    GregorianCalendar timeEndNormal = new GregorianCalendar(2020, Calendar.MAY, 14, 13, 30, 0);
    Event eventNormal = new Event("Normal", speakerA, timeStartNormal, timeEndNormal);


    GregorianCalendar timeStartEarlyBoundary = new GregorianCalendar(2020, Calendar.MAY, 14, 9, 0, 0);
    GregorianCalendar timeEndEarlyBoundary = new GregorianCalendar(2020, Calendar.MAY, 14, 10, 0, 0);
    Event eventEarlyBoundary = new Event("Early Boundary", speakerA, timeStartEarlyBoundary, timeEndEarlyBoundary);

    GregorianCalendar timeStartLateBoundary = new GregorianCalendar(2020, Calendar.MAY, 14, 16, 0, 0);
    GregorianCalendar timeEndLateBoundary = new GregorianCalendar(2020, Calendar.MAY, 14, 17, 0, 0);
    Event eventLateBoundary = new Event("Late Boundary", speakerA, timeStartLateBoundary, timeEndLateBoundary);

    GregorianCalendar timeStartOutOfBounds1 = new GregorianCalendar(2020, Calendar.MAY, 14, 16, 30, 0);
    GregorianCalendar timeEndOutOfBounds1 = new GregorianCalendar(2020, Calendar.MAY, 14, 17, 30, 0);
    Event eventOutOfBounds1 = new Event("OOB Type 1", speakerA, timeStartOutOfBounds1, timeEndOutOfBounds1);

    GregorianCalendar timeStartOutOfBounds2A = new GregorianCalendar(2020, Calendar.MAY, 14, 7, 30, 0);
    GregorianCalendar timeEndOutOfBounds2A = new GregorianCalendar(2020, Calendar.MAY, 14, 8, 30, 0);
    Event eventOutOfBounds2A = new Event("OOB Type 2A", speakerA, timeStartOutOfBounds2A, timeEndOutOfBounds2A);

    GregorianCalendar timeStartOutOfBounds2B = new GregorianCalendar(2020, Calendar.MAY, 14, 22, 30, 0);
    GregorianCalendar timeEndOutOfBounds2B = new GregorianCalendar(2020, Calendar.MAY, 14, 23, 30, 0);
    Event eventOutOfBounds2B = new Event("OOB Type 2B", speakerA, timeStartOutOfBounds2B, timeEndOutOfBounds2B);

    GregorianCalendar timeStartOutOfBounds3 = new GregorianCalendar(2020, Calendar.MAY, 14, 8, 30, 0);
    GregorianCalendar timeEndOutOfBounds3 = new GregorianCalendar(2020, Calendar.MAY, 14, 9, 30, 0);
    Event eventOutOfBounds3 = new Event("OOB Type 3", speakerA, timeStartOutOfBounds3, timeEndOutOfBounds3);

    GregorianCalendar timeStartOverlapA = new GregorianCalendar(2020, Calendar.MAY, 14, 12, 0, 0);
    GregorianCalendar timeEndOverlapA = new GregorianCalendar(2020, Calendar.MAY, 14, 13, 0, 0);
    Event eventOverlapA = new Event("Overlap with B", speakerA, timeStartOverlapA, timeEndOverlapA);

    GregorianCalendar timeStartOverlapB1 = new GregorianCalendar(2020, Calendar.MAY, 14, 12, 30, 0);
    GregorianCalendar timeEndOverlapB1 = new GregorianCalendar(2020, Calendar.MAY, 14, 13, 30, 0);
    Event eventOverlapB1 = new Event("Overlap with A", speakerB, timeStartOverlapB1, timeEndOverlapB1);

    GregorianCalendar timeStartOverlapB2 = new GregorianCalendar(2020, Calendar.MAY, 14, 11, 30, 0);
    GregorianCalendar timeEndOverlapB2 = new GregorianCalendar(2020, Calendar.MAY, 14, 12, 30, 0);
    Event eventOverlapB2 = new Event("Overlap with A", speakerB, timeStartOverlapB2, timeEndOverlapB2);

    Event eventOverlapC = new Event("Same time as A", speakerC, timeStartOverlapA, timeEndOverlapA);

    GregorianCalendar timeEndOverlapD1 = new GregorianCalendar(2020, Calendar.MAY, 14, 14, 0, 0);
    Event eventOverlapD1 = new Event("Back to back with A", speakerD, timeEndOverlapA, timeEndOverlapD1);

    GregorianCalendar timeStartOverlapD2 = new GregorianCalendar(2020, Calendar.MAY, 14, 11, 0, 0);
    Event eventOverlapD2 = new Event("Back to back with A", speakerD, timeStartOverlapD2, timeStartOverlapA);

    GregorianCalendar timeStartNormal1 = new GregorianCalendar(2020, Calendar.MAY, 14, 9, 0, 0);
    GregorianCalendar timeEndNormal1 = new GregorianCalendar(2020, Calendar.MAY, 14, 10, 0, 0);
    Event eventNormal1 = new Event("Different Name", speakerA, timeStartNormal1, timeEndNormal1);

    GregorianCalendar timeStartNormal2 = new GregorianCalendar(2020, Calendar.MAY, 14, 13, 30, 0);
    GregorianCalendar timeEndNormal2 = new GregorianCalendar(2020, Calendar.MAY, 14, 14, 30, 0);
    Event eventNormal2 = new Event("Same Name", speakerA, timeStartNormal2, timeEndNormal2);

    GregorianCalendar timeStartNormal3 = new GregorianCalendar(2020, Calendar.MAY, 14, 16, 0, 0);
    GregorianCalendar timeEndNormal3 = new GregorianCalendar(2020, Calendar.MAY, 14, 17, 0, 0);
    Event eventNormal3 = new Event("Same Name", speakerB, timeStartNormal3, timeEndNormal3);

    Room room;

    @Before
    public void setUpBefore() {
        room = new Room();
    }

    @Test
    public void testGetRoomID() {
        UUID uuid = room.getRoomID();
        assertEquals(room.getRoomID(), uuid);
    }

    @Test
    public void testInitialSchedule() {
        assertEquals(room.getTimeSchedule().size(), 0);
    }

    @Test
    public void testAddEventNormal() {
        assertTrue(room.addEvent(eventNormal));
        assertEquals(room.getTimeSchedule().size(), 1);
        assertEquals(room.getEvents().size(), 1);
    }

    @Test
    public void testAddEventBoundaries() {
        assertTrue(room.addEvent(eventEarlyBoundary)); // Start at 9
        assertTrue(room.addEvent(eventLateBoundary)); // End at 17
        assertEquals(room.getTimeSchedule().size(), 2);
        assertEquals(room.getEvents().size(), 2);
        assertEquals(room.getTimeSchedule().get(timeStartEarlyBoundary), eventEarlyBoundary);
        assertEquals(room.getTimeSchedule().get(timeStartLateBoundary), eventLateBoundary);
    }

    @Test
    public void testAddEventOutOfBounds() {
        assertFalse(room.addEvent(eventOutOfBounds1)); // Start in bound end oob
        assertFalse(room.addEvent(eventOutOfBounds2A)); // Both oob late
        assertFalse(room.addEvent(eventOutOfBounds2B)); // Both oob early
        assertFalse(room.addEvent(eventOutOfBounds3)); // Start oob end in bound
        assertEquals(room.getTimeSchedule().size(), 0);
        assertEquals(room.getEvents().size(), 0);
    }

    @Test
    public void testAddEventOverlapping() {
        assertTrue(room.addEvent(eventOverlapA));
        assertFalse(room.addEvent(eventOverlapB1)); // Normal overlap with A after
        assertFalse(room.addEvent(eventOverlapB2)); // Normal overlap with A before
        assertFalse(room.addEvent(eventOverlapC)); // Same time as A
        assertTrue(room.addEvent(eventOverlapD1)); // Back to back with A after
        assertTrue(room.addEvent(eventOverlapD2)); // Back to back with A before
    }

    @Test
    public void testRemoveEvent() {
        room.addEvent(eventNormal1);
        room.addEvent(eventNormal2);
        room.addEvent(eventNormal3);

        assertEquals(room.getTimeSchedule().size(), 3);
        assertEquals(room.getEvents().size(), 3);

        assertTrue(room.removeEvent(eventNormal1));
        assertEquals(room.getTimeSchedule().size(), 2);
        assertEquals(room.getEvents().size(), 2);
        assertFalse(room.getTimeSchedule().containsValue(eventNormal1));
        assertTrue(room.getTimeSchedule().containsValue(eventNormal2));
        assertTrue(room.getTimeSchedule().containsValue(eventNormal3));

        assertFalse(room.removeEvent(eventNormal1));
        assertEquals(room.getTimeSchedule().size(), 2);
        assertEquals(room.getEvents().size(), 2);
        assertFalse(room.getTimeSchedule().containsValue(eventNormal1));
        assertTrue(room.getTimeSchedule().containsValue(eventNormal2));
        assertTrue(room.getTimeSchedule().containsValue(eventNormal3));
    }

    @Test
    public void testGetEventsByTime() {
        room.addEvent(eventNormal1);
        room.addEvent(eventNormal2);
        room.addEvent(eventNormal3);

        assertTrue(room.getEventsByTime(timeStartNormal1, timeEndNormal3).contains(eventNormal1));
        assertTrue(room.getEventsByTime(timeStartNormal1, timeEndNormal3).contains(eventNormal2));
        assertTrue(room.getEventsByTime(timeStartNormal1, timeEndNormal3).contains(eventNormal3));
        assertEquals(room.getEventsByTime(timeStartNormal1, timeEndNormal3).size(), 3);

        assertTrue(room.getEventsByTime(timeStartNormal1, timeEndNormal2).contains(eventNormal1));
        assertTrue(room.getEventsByTime(timeStartNormal1, timeEndNormal2).contains(eventNormal2));
        assertEquals(room.getEventsByTime(timeStartNormal1, timeEndNormal2).size(), 2);

        assertTrue(room.getEventsByTime(timeStartNormal3, timeEndNormal3).contains(eventNormal3));
        assertEquals(room.getEventsByTime(timeStartNormal3, timeEndNormal3).size(), 1);

        GregorianCalendar timeStartBeforeLapNormal2 = new GregorianCalendar(2020, Calendar.MAY, 14, 13, 0, 0);
        GregorianCalendar timeMidLapNormal2 = new GregorianCalendar(2020, Calendar.MAY, 14, 14, 0, 0);
        GregorianCalendar timeEndAfterLapNormal2 = new GregorianCalendar(2020, Calendar.MAY, 14, 15, 0, 0);
        assertTrue(room.getEventsByTime(timeStartBeforeLapNormal2, timeMidLapNormal2).contains(eventNormal2));
        assertEquals(room.getEventsByTime(timeStartBeforeLapNormal2, timeMidLapNormal2).size(), 1);
        assertFalse(room.getEventsByTime(timeMidLapNormal2, timeEndAfterLapNormal2).contains(eventNormal2));
        assertEquals(room.getEventsByTime(timeMidLapNormal2, timeEndAfterLapNormal2).size(), 0);

        GregorianCalendar timeStartMissAllNormal = new GregorianCalendar(2020, Calendar.MAY, 14, 15, 0, 0);
        GregorianCalendar timeEndMissAllNormal = new GregorianCalendar(2020, Calendar.MAY, 14, 16, 0, 0);
        assertEquals(room.getEventsByTime(timeStartMissAllNormal, timeEndMissAllNormal).size(), 0);
    }

    @Test
    public void testGetSpeakerIDSchedule() {
        assertEquals(room.getSpeakerIDSchedule().size(), 0);
        room.addEvent(eventNormal1);
        room.addEvent(eventNormal2);
        room.addEvent(eventNormal3);
        assertEquals(room.getSpeakerIDSchedule().size(), 2); // 1 and 2 same speaker
    }

    @Test
    public void testGetEventsBySpeakerID() {
        room.addEvent(eventNormal1);
        room.addEvent(eventNormal2);
        room.addEvent(eventNormal3);

        assertTrue(room.getEventsBySpeakerID(speakerA).contains(eventNormal1));
        assertTrue(room.getEventsBySpeakerID(speakerA).contains(eventNormal2));
        assertEquals(room.getEventsBySpeakerID(speakerA).size(), 2);

        assertTrue(room.getEventsBySpeakerID(speakerB).contains(eventNormal3));
        assertEquals(room.getEventsBySpeakerID(speakerB).size(), 1);

        assertEquals(room.getEventsBySpeakerID(speakerC).size(), 0);
    }

    @Test
    public void testGetTitleSchedule() {
        assertEquals(room.getTitleSchedule().size(), 0);
        room.addEvent(eventNormal1);
        room.addEvent(eventNormal2);
        room.addEvent(eventNormal3);
        assertEquals(room.getTitleSchedule().size(), 2); // 2 and 3 same title
    }

    @Test
    public void testGetTimeSchedule() {
        assertEquals(room.getTimeSchedule().size(), 0);
        room.addEvent(eventNormal1);
        room.addEvent(eventNormal2);
        room.addEvent(eventNormal3);
        assertEquals(room.getTimeSchedule().size(), 3); 
    }

    @Test
    public void testGetEventsByTitle() {
        room.addEvent(eventNormal1);
        room.addEvent(eventNormal2);
        room.addEvent(eventNormal3);

        assertTrue(room.getEventsByTitle("Same Name").contains(eventNormal2));
        assertTrue(room.getEventsByTitle("Same Name").contains(eventNormal3));
        assertEquals(room.getEventsByTitle("Same Name").size(), 2);

        assertTrue(room.getEventsByTitle("Different Name").contains(eventNormal1));
        assertEquals(room.getEventsByTitle("Different Name").size(), 1);

        assertEquals(room.getEventsByTitle("Unused Name").size(), 0);
    }

    @Test
    public void testGetEvents() {
        assertTrue(room.addEvent(eventNormal1));
        assertTrue(room.addEvent(eventNormal2));
        assertTrue(room.addEvent(eventNormal3));

        assertTrue(room.getEvents().containsKey(eventNormal1.getEventID()));
        assertTrue(room.getEvents().containsKey(eventNormal2.getEventID()));
        assertTrue(room.getEvents().containsKey(eventNormal3.getEventID()));
        assertEquals(room.getEvents().size(), 3);

        assertTrue(room.removeEvent(eventNormal1));
        assertFalse(room.removeEvent(eventNormal1));

        assertFalse(room.getEvents().containsKey(eventNormal1.getEventID()));
        assertTrue(room.getEvents().containsKey(eventNormal2.getEventID()));
        assertTrue(room.getEvents().containsKey(eventNormal3.getEventID()));
        assertEquals(room.getEvents().size(), 2);
    }

    @Test
    public void testGetEventIDs() {
        assertTrue(room.addEvent(eventNormal1));
        assertTrue(room.addEvent(eventNormal2));
        assertTrue(room.addEvent(eventNormal3));

        assertTrue(room.getEventIDs().contains(eventNormal1.getEventID()));
        assertTrue(room.getEventIDs().contains(eventNormal2.getEventID()));
        assertTrue(room.getEventIDs().contains(eventNormal3.getEventID()));
        assertEquals(room.getEventIDs().size(), 3);

        assertTrue(room.removeEvent(eventNormal1));
        assertFalse(room.removeEvent(eventNormal1));

        assertFalse(room.getEventIDs().contains(eventNormal1.getEventID()));
        assertTrue(room.getEventIDs().contains(eventNormal2.getEventID()));
        assertTrue(room.getEventIDs().contains(eventNormal3.getEventID()));
        assertEquals(room.getEventIDs().size(), 2);
    }
}