import java.util.*;

/**
 * Represent a Speaker
 *
 * Store the events and their corresponding rooms that the Speaker is responsible for
 * Get the events that the Speaker is responsible for
 * Add and remove events that the Speaker is responsible for
 *
 *
 *
 * @author Justin Chan, Zihan Wang
 */

public class Speaker extends User {
    // Last modified: Justin Chan
    private final HashMap<UUID, ArrayList<UUID>> eventsSpeaking = new HashMap<>();

    public Speaker(String username) {
        super(username);
    }

    @Override
    public boolean isOrganizer() {
        return false;
    }

    // Last modified: Justin Chan
    public HashMap<UUID, ArrayList<UUID>> getEventsSpeaking() { // Changed return type
        return eventsSpeaking;
    }

    // Last modified: Justin Chan
    public void addEvent(Room room, Event event){
        eventsSpeaking.putIfAbsent(room.getRoomID(), new ArrayList<>());
        eventsSpeaking.get(room.getRoomID()).add(event.getEventID());
    }
}
