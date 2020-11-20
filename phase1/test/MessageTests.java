import com.group0179.entities.Message;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * @author Zachariah Vincze
 * @author Liam Ogilvie
 */

public class MessageTests {
    private Message message1;
    private Message message2;
    private String messageContent;

    @Before
    public void setupBefore() {
        String messageContent = "hello";
        message1 = new Message(messageContent);
        message2 = new Message(messageContent);
    }

    @Test
    public void testGetMessageID() {
        UUID messageID = message1.getMessageID();
        assertEquals(message1.getMessageID(), messageID);
    }

    @Test
    public void testGetMessageContent() {
        assertEquals(message1.getMessageContent(), "hello");
    }

    @Test
    public void testRandomUUIDGeneration() {
        assertNotSame(message1.getMessageID(), message2.getMessageID());
    }
}
