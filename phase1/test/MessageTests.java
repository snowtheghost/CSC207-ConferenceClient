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
    private UUID senderUUID;
    private UUID receiver1;
    private UUID receiver2;

    private List<UUID> receiverUUIDs;

    @Before
    public void setupBefore() {
        senderUUID = UUID.randomUUID();
        receiverUUIDs = new ArrayList<UUID>();
        receiverUUIDs.add(receiver1);
        receiverUUIDs.add(receiver2);
        message1 = new Message(messageContent, senderUUID, receiverUUIDs);
    }

    @Test
    public void testGetMessageID() {
        UUID messageID = message1.getMessageID();
        assertEquals(message1.getMessageID(), messageID);
    }

    @Test
    public void testGetSenderID() {
        assertEquals(message1.getSenderID(), senderUUID);
    }

    @Test
    public void testGetRecipientIDs() {
        List<UUID> UUIDs = message1.getRecipientIDs();
        for (int i = 0; i < receiverUUIDs.size(); i++) {
            assertEquals(UUIDs.get(i), receiverUUIDs.get(i));
        }
    }

    @Test
    public void testGetMessageContent() {
        assertEquals(message1.getMessageContent(), messageContent);
    }
}
