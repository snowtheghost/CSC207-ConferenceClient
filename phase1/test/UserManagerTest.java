import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * @author Zihan Wang, Kaiyi Liu
 */

public class UserManagerTest {
    UserManager um = new UserManager();
    UUID attendee1ID = um.createAttendeeAccount("attendee1");
    UUID organizer1ID = um.createOrganizerAccount("organizer1");
    UUID speaker1ID = um.createSpeakerAccount("speaker1");
    UUID attendee2ID = um.createAttendeeAccount("attendee2");
    UUID testRoomID = UUID.randomUUID();
    UUID testRoomID2 = UUID.randomUUID();
    UUID testEventID = UUID.randomUUID();
    UUID testEventID2 = UUID.randomUUID();


    @Test
    public void TestCreateAccount() {
        assertEquals(um.getUsername(attendee1ID), "attendee1");
        assertEquals(um.getUsername(organizer1ID), "organizer1");
        assertEquals(um.getAttendeeUUIDs().size(), 2);
        assertTrue(um.getAttendeeUUIDs().contains(attendee1ID));
        assertTrue(um.getAttendeeUUIDs().contains(attendee2ID));
        assertEquals(um.getOrganizerUUIDs().size(), 1);
        assertTrue(um.getOrganizerUUIDs().contains(organizer1ID));
        assertEquals(um.getSpeakerUUIDs().size(), 1);
        assertTrue(um.getSpeakerUUIDs().contains(speaker1ID));
    }
    @Test
    public void TestUserExist(){
        assertTrue(um.userExists(attendee1ID));
        assertTrue(um.userExists(speaker1ID));
        assertTrue(um.userExists(organizer1ID));
        ArrayList<UUID> l = new ArrayList<>();
        l.add(attendee2ID);
        l.add(organizer1ID);
        l.add(speaker1ID);
        assertEquals(um.usersExist(l), true);
    }
    @Test
    public void TestGetUserNames(){
        assertTrue(um.getUsernames().contains("attendee1"));
        assertTrue(um.getUsernames().contains("attendee2"));
        assertTrue(um.getUsernames().contains("organizer1"));
        assertTrue(um.getUsernames().contains("speaker1"));
        assertFalse(um.getUsernames().contains("speaker2"));
        assertEquals(um.getUsernames().size(),4);
    }

    @Test
    public void TestCurrentUser(){
        assertEquals(um.getCurrentUser(), null);
        um.setCurrentUser("attendee1");
        assertEquals(um.getCurrentUser(), attendee1ID);
        um.setCurrentUser("speaker1");
        assertEquals(um.getCurrentUser(), speaker1ID);
        um.setCurrentUserFromUserName("attendee1");
        assertEquals(um.getCurrentUser(), attendee1ID);
        um.setCurrentUserFromUserName("speaker1");
        assertEquals(um.getCurrentUser(), speaker1ID);
    }

    @Test
    public void TestIsValidUserName(){assertFalse(um.isValidUsername("attendee1"));
    assertTrue(um.isValidUsername("attendee3"));
    assertFalse(um.isValidUsername("organizer1"));
    }

    @Test
    public void TestUserType(){
        assertEquals(um.userType("attendee1"), "attendee");
        assertFalse(um.userType("attendee") == "attendee");
        assertEquals(um.userType("organizer1"), "organizer");
        assertEquals(um.userType("speaker1"), "speaker");
    }

    @Test
    public void TestAddRemoveGetSpeakerEvent(){
        um.speakerAddEvent("speaker1", testRoomID, testEventID);
        um.speakerAddEvent("speaker1", testRoomID2, testEventID2);
        assertEquals(um.getSpeakerEventIDs("speaker1").size(), 2);
        assertTrue(um.getSpeakerEventIDs("speaker1").contains(testEventID));
        assertTrue(um.getSpeakerEventIDs("speaker1").contains(testEventID2));
        um.speakerAddEvent("speaker1", testRoomID, testEventID2);
        assertEquals(um.getSpeakerEventIDs(speaker1ID).size(), 3);
        assertTrue(um.getSpeakerEventIDs(speaker1ID).contains(testEventID2));
        assertTrue(um.getSpeakerEventIDs(speaker1ID).contains(testEventID));
        um.speakerRemoveEvent("speaker1", testRoomID, testEventID2);
        assertEquals(um.getSpeakerEventIDs("speaker1").size(), 2);
        assertTrue(um.getSpeakerEventIDs(speaker1ID).contains(testEventID2));
        assertTrue(um.getSpeakerEventIDs(speaker1ID).contains(testEventID));
        um.speakerRemoveEvent("speaker1", testRoomID, testEventID);
        assertEquals(um.getSpeakerEventIDs("speaker1").size(), 1);
        assertTrue(um.getSpeakerEventIDs(speaker1ID).contains(testEventID2));
        assertFalse(um.getSpeakerEventIDs(speaker1ID).contains(testEventID));
    }

//    @Test
//    public void TestAddRemoveAttendeeEvent(){
//        um.attendeeAddEvent(attendee1ID, testRoomID, testEventID);
//        um.attendeeAddEvent(attendee2ID, testRoomID2, testEventID2);
//
//    }

    @Test
    public void TestIsSpeaker(){
        assertFalse(um.isSpeaker("attendee1"));
        assertFalse(um.isSpeaker("attendee2"));
        assertFalse(um.isSpeaker("organizer1"));
        assertTrue(um.isSpeaker("speaker1"));
    }

    @Test
    public void TestStringAvailableSpeakers(){
        String returnedValue = um.stringAvailableSpeakers();
        assertTrue(returnedValue.contains("speaker1"));
        assertFalse(returnedValue.contains("speaker2"));
        um.createSpeakerAccount("speaker3");
        returnedValue = um.stringAvailableSpeakers();
        assertTrue(returnedValue.contains("speaker3"));
        assertTrue(returnedValue.contains("speaker1"));
    }

    @Test
    public void TestAddGetMessagesFromUser(){
        UUID testMessageID = UUID.randomUUID();
        UUID testMessageIDTwo = UUID.randomUUID();
        UUID testMessageIDThree = UUID.randomUUID();
        um.addMessage(attendee1ID, attendee2ID, testMessageID);
        um.addMessage(attendee2ID, attendee1ID, testMessageIDTwo);
        List<UUID> messagesForRecipientAttendeeOne = um.getMessagesFromUser(attendee1ID, attendee2ID);
        List<UUID> messagesForRecipientAttendeeTwo = um.getMessagesFromUser(attendee2ID, attendee1ID);
        assertEquals(messagesForRecipientAttendeeOne.size(), 1);
        assertTrue(messagesForRecipientAttendeeOne.contains(testMessageID));
        assertEquals(messagesForRecipientAttendeeTwo.size(), 1);
        assertTrue(messagesForRecipientAttendeeTwo.contains(testMessageIDTwo));
        um.addMessage(attendee1ID, attendee2ID, testMessageIDThree);
        messagesForRecipientAttendeeOne = um.getMessagesFromUser(attendee1ID, attendee2ID);
        assertEquals(messagesForRecipientAttendeeOne.size(), 2);
        assertTrue(messagesForRecipientAttendeeOne.contains(testMessageIDThree));
        assertTrue(messagesForRecipientAttendeeOne.contains(testMessageID));
    }

    @Test
    public void TestGetUserName(){
        String user = um.getUsername(attendee1ID);
        assertEquals(user, "attendee1");
        user = um.getUsername(organizer1ID);
        assertEquals(user, "organizer1");
        user = um.getUsername(speaker1ID);
        assertEquals(user, "speaker1");
        user = um.getUsername(attendee2ID);
        assertEquals(user, "attendee2");
    }

    @Test
    public void testGetUserID(){
        UUID user = um.getUserID("attendee1");
        assertEquals(user, attendee1ID);
        user = um.getUserID("attendee2");
        assertEquals(user, attendee2ID);
        user = um.getUserID("speaker1");
        assertEquals(user, speaker1ID);
        user = um.getUserID("organizer1");
        assertEquals(user, organizer1ID);
    }
}
