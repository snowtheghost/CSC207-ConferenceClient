import org.junit.*;
import static org.junit.Assert.*;

import java.util.UUID;
import java.util.ArrayList;

public class EventTests {
    Speaker speaker1 = new Speaker("speaker1");
    Event event1;

    @Before
    public void setUpBefore() {
        event1 = new Event("event1", speaker1, new int[]{10, 01, 2021}, 0100);
    }

    // initial number of candies
    @Test
    public void testGetName() {
        assertTrue(event1.getName().equals("event1"));
    }

    @Test
    public void testGetEventID() {
        UUID uuid = event1.getEventID();
        assertTrue(event1.getEventID().equals(uuid));
    }
}