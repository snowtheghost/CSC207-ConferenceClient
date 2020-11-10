import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * @author Liam Ogilvie
 */

public class MessageManagerTests {
    private MessageManager messageManager = new MessageManager();
    private UserManager userManager = new UserManager();
    private Attendee attendee1;
    private Attendee attendee2;
    private Attendee attendee3;
    private UUID a1UUID;
    private UUID a2UUID;
    private UUID a3UUID;
    private String messageContent;
    private List<UUID> recipientIDs;

    @Before
    public void setupBefore() {
        messageManager = new MessageManager();
        userManager = new UserManager();
        //Attendee attendee1 = userManager.createAttendeeAccount("attendee1");
        //Attendee attendee2 = userManager.createAttendeeAccount("attendee2");
        //UUID a1UUID = attendee1.getUserID();
        //UUID a2UUID = attendee2.getUserID();
        //String messageContent = "hello";
    }

    @Test
    public void testSendMessageA1toA2() {
        Attendee attendee1 = userManager.createAttendeeAccount("attendee1");
        Attendee attendee2 = userManager.createAttendeeAccount("attendee2");
        UUID a1UUID = attendee1.getUserID();
        UUID a2UUID = attendee2.getUserID();
        String messageContent = "hello";

        messageManager.sendMessage(userManager, a1UUID, a2UUID, messageContent);
        List<Message> msg = messageManager.getMessagesFromUser(userManager, a2UUID, a1UUID);
        assertEquals(msg.get(0).getMessageContent(), "hello");
    }
/* @Liam: Shouldn't sendmessages be available publicly so organizers can send group messages?
          Or maybe I'm just misunderstanding the hierarchy of MessageManager.

    @Test
    public void testSendMessagesA1toA2andA3() {
        Attendee attendee1 = userManager.createAttendeeAccount("attendee1");
        Attendee attendee2 = userManager.createAttendeeAccount("attendee2");
        Attendee attendee3 = userManager.createAttendeeAccount("attendee2");
        System.out.println(userManager.getAttendees());
        UUID a1UUID = attendee1.getUserID();
        UUID a2UUID = attendee2.getUserID();
        UUID a3UUID = attendee3.getUserID();
        String messageContent = "hello";
        List<UUID> recips = Arrays.asList(a2UUID, a3UUID);

        messageManager.sendMessages(userManager, a1UUID, recips,messageContent);
        List<Message> msg1 = messageManager.getMessagesFromUser(userManager, a2UUID, a1UUID);
        List<Message> msg2 = messageManager.getMessagesFromUser(userManager, a3UUID, a1UUID);
        assertEquals(msg1.get(0).getMessageContent(), "hello");
        assertEquals(msg2.get(0).getMessageContent(), "hello");
    }
 */
    @Test
    public void testSendMessagesToAllAttendeesA1toA2andA3() {
        Attendee attendee1 = userManager.createAttendeeAccount("attendee1");
        Attendee attendee2 = userManager.createAttendeeAccount("attendee2");
        Attendee attendee3 = userManager.createAttendeeAccount("attendee2");
        //System.out.println(userManager.getAttendees());
        UUID a1UUID = attendee1.getUserID();
        UUID a2UUID = attendee2.getUserID();
        UUID a3UUID = attendee3.getUserID();
        String messageContent = "goodbye";

        messageManager.sendMessageToAllAttendees(userManager, a1UUID, messageContent);
        List<Message> msg1 = messageManager.getMessagesFromUser(userManager, a2UUID, a1UUID);
        assertEquals(msg1.get(0).getMessageContent(), "goodbye");
        List<Message> msg2 = messageManager.getMessagesFromUser(userManager, a3UUID, a1UUID);
        assertEquals(msg2.get(0).getMessageContent(), "goodbye");
    }
}
    //