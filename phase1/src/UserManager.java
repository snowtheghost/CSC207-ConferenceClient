import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserManager {
    private Map<UUID, User> users;

    /**
     * Creates a new UserManager.
     */
    public UserManager() {
        this.users = new HashMap<UUID, User>();
    }


    /**
     * Returns a User object based on their UUID.
     * @param userID the UUID of the user you wish to return.
     * @return the User that the provided UUID belongs to or null if that user is not found.
     */
    public User getUser(UUID userID) {
        return this.users.get(userID);
    }
}
