import com.group0179.entities.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class SpeakerTest {
    Speaker speaker1;
    Attendee attendee2 = new Attendee("attendee2");
    Room room1 = new Room();
    Room room2 = new Room();
    GregorianCalendar timeStartNormal = new GregorianCalendar(2020, Calendar.MAY, 14, 12, 30, 0);
    GregorianCalendar timeEndNormal = new GregorianCalendar(2020, Calendar.MAY, 14, 13, 30, 0);
    Event event1 = new Event("event1", "speaker1", timeStartNormal, timeEndNormal);
    Event event2 = new Event("event2", "speaker1", timeStartNormal, timeEndNormal);
    Message message1 = new Message("message1");
    Message message2 = new Message("message2");



    @Before
    public void SetUpBefore(){speaker1 = new Speaker("speaker1"); }

    @Test
    public void TestIsOrganizerAttendeeSpeaker(){
        assertEquals(true, speaker1.isSpeaker());
        assertEquals(false, speaker1.isAttendee());
        assertEquals(false, speaker1.isOrganizer());
    }

    @Test
    public void TestGetEventSpeakingAddEventRemoveEvent(){
        Map<UUID, ArrayList<UUID>> res = new HashMap<>();
        assertEquals(speaker1.getEventsSpeaking(), res);
        res.putIfAbsent(room1.getRoomID(), new ArrayList<>());
        res.get(room1.getRoomID()).add(event1.getEventID());
        speaker1.addEvent(room1.getRoomID(), event1.getEventID());
        assertEquals(speaker1.getEventsSpeaking(), res);
        speaker1.removeEvent(room1.getRoomID(),event2.getEventID());
        assertEquals(speaker1.getEventsSpeaking(), res);
        speaker1.addEvent(room2.getRoomID(), event2.getEventID());
        speaker1.removeEvent(room1.getRoomID(),event2.getEventID());
        res.putIfAbsent(room2.getRoomID(), new ArrayList<>());
        res.get(room2.getRoomID()).add(event2.getEventID());
        assertEquals(speaker1.getEventsSpeaking(), res);
        speaker1.removeEvent(room1.getRoomID(),event1.getEventID());
        speaker1.removeEvent(room2.getRoomID(),event2.getEventID());
        assertEquals(speaker1.getEventsSpeaking(), new HashMap<>());
    }

    @Test
    public void TestGetUserName(){assertEquals("speaker1", speaker1.getUsername()); }

    @Test
    public void TestGetAddMessages(){
        ArrayList<UUID> res = new ArrayList<>();
        assertEquals(res, speaker1.getMessages(attendee2.getUserID()));
        speaker1.addMessage(attendee2.getUserID(), message1.getMessageID());
        res.add(message1.getMessageID());
        assertEquals(res, speaker1.getMessages(attendee2.getUserID()));
        res.add(message2.getMessageID());
        speaker1.addMessage(attendee2.getUserID(), message2.getMessageID());
        assertEquals(res, speaker1.getMessages(attendee2.getUserID()));
    }
}
