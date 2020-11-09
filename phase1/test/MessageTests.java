import org.junit.*;
import static org.junit.Assert.*;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

/**
 * @author Zachariah Vincze
 */

public class MessageTests {
    private Message message1;
    private String messageContent;

    @Before
    public void setupBefore() {
        message1 = new Message(messageContent);
    }

    @Test
    public void testGetMessageID() {
        UUID messageID = message1.getMessageID();
        assertEquals(message1.getMessageID(), messageID);
    }

    @Test
    public void testGetMessageContent() {
        assertEquals(message1.getMessageContent(), messageContent);
    }
}
