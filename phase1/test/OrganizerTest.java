import org.junit.Test;

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
}
