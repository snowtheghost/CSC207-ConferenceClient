import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.List;

public class UserManager {
    private Map<UUID, User> users;
    private List<String> userNames;
    private User currentUser;

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

    /**
     * Returns the currently logged in User.
     * @return the currently logged in User.
     */
    public User getCurrentUser() { return currentUser; }

    /**
     * Sets the currently logged in user.
     * @param currentUser the logged in user.
     */
    public void setCurrentUser(User currentUser) { this.currentUser = currentUser; }

    /**
     * Returns a list of all Attendee UUIDs.
     * @return list of all Attendee UUIDs.
     * */
    public List<UUID> getAttendees() {
        List<UUID> attendees = new ArrayList<>();
        for(UUID userID : this.users.keySet()){
            if(!(this.users.get(userID)).isOrganizer()){
                attendees.add(userID);
            }
        }
        return attendees;
    }
    /**
     * Creates a user account with the specified username
     * @return true if user account was successfully made, else return false.
     *
     * */
    public boolean createAccount(String userName, boolean isOrganizer){
        if(this.userNames.contains(userName)){
            return false;
        } else {
            User newUser;
            UUID userID = UUID.randomUUID();
            if(isOrganizer){
                newUser = new Organizer(userName);
            } else {
                newUser = new Attendee(userName);
            }
            this.users.put(userID, newUser);
            this.userNames.add(userName);
            return true;
        }
    }
    /**
     * @return list of user names
     * */
    public List<String> getUserNames(){
        return new ArrayList<>(this.userNames);
    }

}
