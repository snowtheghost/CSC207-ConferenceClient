import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Zihan Wang
 */
public class OrganizerTest {
    Organizer organizer1 = new Organizer("organizer1");

    @Test
    public void TestIsOrganizer(){
        assertTrue(organizer1.isOrganizer());
    }

    @Test
    public void TestIsAttendee(){assertTrue(organizer1.isAttendee());}

    @Test
    public void TestIsSpeaker(){assertFalse(organizer1.isSpeaker());}
}
