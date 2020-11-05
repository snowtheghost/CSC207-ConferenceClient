import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represent a Speaker
 * @author Zihan Wang
 */
public class Speaker extends User {
    private List<UUID> eventsSpeaking;

    public Speaker(String username) {
        super(username);
        eventsSpeaking = new ArrayList<>();
    }

    public List<UUID> getEventsSpeaking() {
        return eventsSpeaking;
    }
}
