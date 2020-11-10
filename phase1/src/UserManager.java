import java.util.*;

/**
 * Get list of all users, attendees, speakers, or organizers in existence
 *
 * Get a list of all usernames in existence
 *
 * @author Zihan Wang, Justin Chan, Kaiyi Liu
 * Last modified: Justin Chan
 */

public class UserManager {
    private final ArrayList<Attendee> attendees = new ArrayList<>();
    private final ArrayList<Organizer> organizers = new ArrayList<>();
    private final ArrayList<Speaker> speakers = new ArrayList<>();
    private User currentUser;

    /**
     * Last modified: Justin Chan
     */
    public UserManager() { }

    /**
     * Created: Justin Chan
     * @return an ArrayList of all users in existence
     */
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.addAll(attendees);
        users.addAll(organizers);
        users.addAll(speakers);
        return users;
    }

    public ArrayList<String> getUsernames() {
        ArrayList<String> usernames = new ArrayList<>();
        for (User user : getUsers()) {
            usernames.add(user.getUsername());
        }
        return usernames;
    }

    /**
     * Created: Justin Chan
     * @return a dictionary mapping UserID to User
     */
    public HashMap<UUID, User> getUserIDToUser() {
        HashMap<UUID, User> userIDToUser =  new HashMap<>();
        for (User user : getUsers()) {
            userIDToUser.put(user.getUserID(), user);
        }
        return userIDToUser;
    }

    /**
     * Created: Zihan Wang
     * @return a dictionary mapping UserName to User
     */
    public HashMap<String, User> getUsernameToUser(){
        HashMap<String, User> usernameToUser = new HashMap<>();
        for(User user: getUsers()){
            usernameToUser.put(user.getUsername(), user);
        }
        return usernameToUser;
    }

    /**
     * Returns a User object based on their UUID.
     * @param userID the UUID of the user you wish to return.
     * @return the User that the provided UUID belongs to or null if that user is not found.
     * Last modified: Justin Chan
     */
    public User getUser(UUID userID) {
        return getUserIDToUser().get(userID);
    }

    public User getUser(String username) {
        return getUsernameToUser().get(username);
    }

    /**
     * @return list of attendees in existence
     * Last modified: Justin Chan
     */
    public ArrayList<Attendee> getAttendees() {
        return attendees;
    }

    /**
     * @return list of attendeesUUIDs in existence
     * Last modified: Justin Chan
     */
    public ArrayList<UUID> getAttendeeUUIDs() {
        ArrayList<UUID> attendeeUUIDs = new ArrayList<>();
        for (Attendee attendee : getAttendees()) {
            attendeeUUIDs.add(attendee.getUserID());
        }
        return attendeeUUIDs;
    }

    /**
     * Created: Justin Chan
     * @return list of speakers in existence
     */
    public ArrayList<Speaker> getSpeakers() {
        return speakers;
    }

    /**
     * @return list of speakersUUIDs in existence
     * Last modified: Justin Chan
     */
    public ArrayList<UUID> getSpeakerUUIDs() {
        ArrayList<UUID> speakerUUIDs = new ArrayList<>();
        for (Speaker speaker : getSpeakers()) {
            speakerUUIDs.add(speaker.getUserID());
        }
        return speakerUUIDs;
    }

    /**
     * Created: Justin Chan
     * @return list of organizers in existence
     */
    public ArrayList<Organizer> getOrganizers() {
        return organizers;
    }

    /**
     * @return currently logged in User.
     */
    public User getCurrentUser() { return currentUser; }

    /**
     * Sets the currently logged in user.
     * @param username the username of the logged in user.
     *
     * Last modified: Justin Chan
     */
    public boolean setCurrentUser(String username) {
        if (getUsernames().contains(username)) {
            this.currentUser = getUser(username);
            return true;
        }
        return false;
    }

    /**
     * Created: Zihan Wang
     * Sets the currently logged in user from username.
     * @param UserName the username of the logged in user.
     */
    public boolean setCurrentUserFromUserName(String UserName){
        if(getUsernameToUser().containsKey(UserName)){
            this.currentUser = getUsernameToUser().get(UserName);
            return true;}
        System.out.println("This Username doesn't exists, please try another Username.");
        return false;
    }

    /**
     * @param username unique username requested
     * @return the Attendee that was created
     * Last modified: Justin Chan
     *
     * Precondition: isValidUsername(username)
     */
    public Attendee createAttendeeAccount(String username) {
        Attendee attendee = new Attendee(username);
        attendees.add(attendee);
        return attendee;
    }

    /**
     * @return list of organizersUUIDs in existence
     * Last modified: Justin Chan
     */
    public ArrayList<UUID> getOrganizerUUIDs() {
        ArrayList<UUID> organizerUUIDs = new ArrayList<>();
        for (Organizer organizer : getOrganizers()) {
            organizerUUIDs.add(organizer.getUserID());
        }
        return organizerUUIDs;
    }

    /**
     * @param username unique username requested
     * @return the Organizer that was created
     * Last modified: Justin Chan
     *
     * Precondition: isValidUsername(username)
     */
    public Organizer createOrganizerAccount(String username) {
        Organizer organizer = new Organizer(username);
        organizers.add(organizer);
        return organizer;
    }

    /**
     * @param username unique username requested
     * @return the speaker that was created
     * Last modified: Justin Chan
     *
     * Precondition: isValidUsername(username)
     */
    public Speaker createSpeakerAccount(String username) {
        Speaker speaker = new Speaker(username);
        speakers.add(speaker);
        return speaker;
    }

    /**
     * @param username the desired username to create
     * @return true if the username is unique or false if the username already exists
     */
    public boolean isValidUsername(String username) {
        return !getUsernames().contains(username);
    }

    /**
     * @param username the username of the user
     * @return the String of the type of user
     *
     * Created: Justin Chan
     * TODO: This code was thrown together - needs optimizing!
     */
    public String userType(String username) {
        if (getUsernames().contains(username)) {
            User user = getUser(username);
            if (user.isOrganizer()) {
                return "organizer";
            } else if (user.isAttendee()) {
                return "attendee";
            } else {
                return "speaker";
            }
        }
        return "";
    }
}
