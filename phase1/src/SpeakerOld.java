import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represent a Speaker
 * @author Zihan Wang
 */
public class SpeakerOld extends User {
    private List<UUID> eventsSpeaking;

    public SpeakerOld(String username) {
        super(username);
        eventsSpeaking = new ArrayList<>();
    }

    public List<UUID> getEventsSpeaking() {
        return eventsSpeaking;
    }

    public void addEvent(Event event){eventsSpeaking.add(event.getEventID());}
}
