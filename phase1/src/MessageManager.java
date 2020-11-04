import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Manages message entities.
 * @author Zachariah Vincze
 */
public class MessageManager {
    private Map<UUID, Message> messages;

    /**
     * Creates a new MessageManager.
     */
    public MessageManager() {
        this.messages = new HashMap<UUID, Message>();
    }
}
