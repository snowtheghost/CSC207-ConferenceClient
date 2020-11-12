import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * @author Derrik Wang, Kaiyi Liu
 */

public class UserManagerTest {
    UserManager um = new UserManager();
    UUID attendee1ID = um.createAttendeeAccount("attendee1");
    UUID organizer1ID = um.createOrganizerAccount("organizer1");
    UUID speaker1ID = um.createSpeakerAccount("speaker1");
    UUID attendee2ID = um.createAttendeeAccount("attendee2");
    UUID[] users = {attendee1ID, organizer1ID, speaker1ID, attendee2ID};

    @Test
    public void TestCreateAccount() {
        assertEquals(um.getUsername(attendee1ID), "attendee1");
        assertEquals(um.getUsername(organizer1ID), "organizer1");
        assertEquals(um.getAttendeeUUIDs().size(), 2);
        assertEquals(um.getAttendeeUUIDs().contains(attendee1ID),true);
        assertEquals(um.getAttendeeUUIDs().contains(attendee2ID), true);
        assertEquals(um.getOrganizerUUIDs().size(), 1);
        assertEquals(um.getOrganizerUUIDs().contains(organizer1ID),true);
        assertEquals(um.getSpeakerUUIDs().size(), 1);
        assertEquals(um.getSpeakerUUIDs().contains(speaker1ID), true);
    }
    @Test
    public void TestUserExist(){
        assertEquals(um.userExists(attendee1ID),true);
        assertEquals(um.userExists(speaker1ID), true);
        assertEquals(um.userExists(organizer1ID), true);
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
    public void TestGetUser(){
        User value = um.getUser("attendee1");
        assertEquals(value.getUserID(), attendee1ID);
        User secondValue = um.getUser(organizer1ID);
        assertEquals(secondValue.getUsername(), "organizer1");
        List<User> listOfUsers = um.getUsers();
        int numOfValids = 0;
        for(User user : listOfUsers){
            for(UUID id : users){
                if(id.equals(user.getUserID())){
                    numOfValids++;
                }
            }
        }
        assertEquals(numOfValids, listOfUsers.size());
    }
    @Test
    public void TestGetUserIDToUser(){
        HashMap<UUID, User> map = um.getUserIDToUser();
        for(UUID id: map.keySet()){
            UUID userID = map.get(id).getUserID();
            assertEquals(id, userID);
        }
    }
    @Test
    public void TestGetAttendees(){
        List<Attendee> attendees = um.getAttendees();
        assertEquals(attendees.size(), 2);
        assertTrue(attendees.contains("attendee2"));
        assertTrue(attendees.contains("attendee1"));
        List<UUID> attendeesUUID = um.getAttendeeUUIDs();
        assertEquals(attendeesUUID.size(), 2);
        assertTrue(attendeesUUID.contains(attendee1ID));
        assertTrue(attendeesUUID.contains(attendee2ID));
    }
    @Test
    public void TestGetOrganizers(){
        List<Organizer> organizers = um.getOrganizers();
        assertEquals(organizers.size(), 1);
        assertTrue(organizers.contains("organizer1"));
        List<UUID> organizersUUID = um.getOrganizerUUIDs();
        assertEquals(organizersUUID.size(), 1);
        assertTrue(organizersUUID.contains(organizer1ID));
    }
    @Test
    public void TestGetSpeakers(){
        List<Speaker> speakers = um.getSpeakers();
        assertEquals(speakers.size(), 1);
        assertTrue(speakers.contains("speaker1"));
        List<UUID> speakersUUID = um.getSpeakerUUIDs();
        assertEquals(speakersUUID.size(), 1);
        assertTrue(speakersUUID.contains(speaker1ID));
    }


}
