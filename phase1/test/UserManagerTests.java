import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.junit.Assert.*;

/**
 * @author Kaiyi Liu
 */

public class UserManagerTests {
    private UserManager userManager;

    @Before
    public void setupBefore() {
        this.userManager = new UserManager();
    }

    @Test
    public void testCreateOrganizerAccount(){
        String userName = "Jonathon";
        String secondUserName = "Bobby";
        String thirdUserName = "name that doesnt belong";
        UUID organier = this.userManager.createOrganizerAccount(userName);
        UUID organierTwo = this.userManager.createOrganizerAccount(secondUserName);
        boolean located = false;
        boolean locatedTwo = false;
        boolean locatedThree = false;
        for(Organizer organizer : this.userManager.getOrganizers()){
            if(organizer.getUsername().equals(userName)){
                located = true;
            }
            if(organizer.getUsername().equals(secondUserName)){
                locatedTwo = true;
            }
            if(organizer.getUsername().equals(thirdUserName)){
                locatedThree = true;
            }
        }
        assertTrue(locatedTwo);
        assertTrue(located);
        assertFalse(locatedThree);
    }

    @Test
    public void testCreateAttendeeAccount(){
        String userName = "Julianne";
        String secondUserName = "James";
        String thirdUserName = "name that fails";
        UUID organier = this.userManager.createAttendeeAccount(userName);
        UUID organierTwo = this.userManager.createAttendeeAccount(secondUserName);
        boolean located = false;
        boolean locatedTwo = false;
        boolean locatedThree = false;
        for(Attendee attendee : this.userManager.getAttendees()){
            if(attendee.getUsername().equals(userName)){
                located = true;
            }
            if(attendee.getUsername().equals(secondUserName)){
                locatedTwo = true;
            }
            if(attendee.getUsername().equals(thirdUserName)){
                locatedThree = true;
            }
        }
        assertTrue(locatedTwo);
        assertTrue(located);
        assertFalse(locatedThree);
    }

    @Test
    public void testCreateSpeakerAccount(){
        String userName = "Jones";
        String secondUserName = "Jessie";
        String thirdUserName = "name that fails";
        UUID organier = this.userManager.createSpeakerAccount(userName);
        UUID organierTwo = this.userManager.createSpeakerAccount(secondUserName);
        boolean located = false;
        boolean locatedTwo = false;
        boolean locatedThree = false;
        for(Speaker speaker : this.userManager.getSpeakers()){
            if(speaker.getUsername().equals(userName)){
                located = true;
            }
            if(speaker.getUsername().equals(secondUserName)){
                locatedTwo = true;
            }
            if(speaker.getUsername().equals(thirdUserName)){
                locatedThree = true;
            }
        }
        assertTrue(locatedTwo);
        assertTrue(located);
        assertFalse(locatedThree);
    }

    @Test
    public void testisValidUsername() {
        assertTrue(this.userManager.isValidUsername("RandomUserName"));
    }




}
