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
     * Created: Zachariah Vincze
     * @param userID the UUID of the user to check.
     * @return True iff there exists a user with this UUID.
     */
    public boolean userExists(UUID userID) {
        return getUserIDToUser().containsKey(userID);
    }

    /**
     * Created: Zachariah Vincze
     * @param userIDs the list of UUIDs to check.
     * @return True iff all users exist in the system.
     */
    public boolean usersExist(List<UUID> userIDs) {
        HashMap<UUID, User> users = getUserIDToUser();
        for (UUID id : userIDs) {
            if (!users.containsKey(id)) return false;
        }
        return true;
    }

    /**
     * Created: Justin Chan
     * @return an ArrayList of all users in existence
     */
    private ArrayList<User> getUsers() {
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
    private HashMap<UUID, User> getUserIDToUser() {
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
    private HashMap<String, User> getUsernameToUser(){
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
    private User getUser(UUID userID) {
        return getUserIDToUser().get(userID);
    }

    private User getUser(String username) {
        return getUsernameToUser().get(username);
    }

    /**
     * @return list of attendees in existence
     * Last modified: Justin Chan
     */
    private ArrayList<Attendee> getAttendees() {
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
    private ArrayList<Speaker> getSpeakers() {
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
    private ArrayList<Organizer> getOrganizers() {
        return organizers;
    }

    /**
     * @return currently logged in User.
     */
    public UUID getCurrentUser() { return currentUser.getUserID(); }

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
        System.out.println("This Username doesn't exist, please try another Username.");
        return false;
    }

    /**
     * @param username unique username requested
     * @return the Attendee that was created
     * Last modified: Justin Chan
     *
     * Precondition: isValidUsername(username)
     */
    public UUID createAttendeeAccount(String username) {
        Attendee attendee = new Attendee(username);
        attendees.add(attendee);
        return attendee.getUserID();
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
    public UUID createOrganizerAccount(String username) {
        Organizer organizer = new Organizer(username);
        organizers.add(organizer);
        return organizer.getUserID();
    }

    /**
     * @param username unique username requested
     * @return the speaker that was created
     * Last modified: Justin Chan
     *
     * Precondition: isValidUsername(username)
     */
    public UUID createSpeakerAccount(String username) {
        Speaker speaker = new Speaker(username);
        speakers.add(speaker);
        return speaker.getUserID();
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
     */
    public String userType(String username) {
        return getUser(username).getStringType();
    }

    public ArrayList<UUID> getSpeakerEventIDs(String speakerName) {
        Speaker speaker = (Speaker) getUser(speakerName);

        ArrayList<UUID> eventIDs = new ArrayList<>();
        for (ArrayList<UUID> speakerEventIDs : speaker.getEventsSpeaking().values()) {
            speakerEventIDs.addAll(eventIDs);
        } return eventIDs;
    }

    public ArrayList<UUID> getSpeakerEventIDs(UUID speakerID) {
        Speaker speaker = (Speaker) getUser(speakerID);

        ArrayList<UUID> eventIDs = new ArrayList<>();
        for (ArrayList<UUID> speakerEventIDs : speaker.getEventsSpeaking().values()) {
            speakerEventIDs.addAll(eventIDs);
        } return eventIDs;
    }

    public void speakerAddEvent(String speakerName, UUID roomID, UUID eventID) {
        Speaker speaker = (Speaker) getUser(speakerName);
        speaker.addEvent(roomID, eventID);
    }

    public void speakerRemoveEvent(String speakerName, UUID roomID, UUID eventID) {
        Speaker speaker = (Speaker) getUser(speakerName);
        speaker.removeEvent(roomID, eventID);
    }

    public void attendeeAddEvent(UUID attendeeID, UUID roomID, UUID eventID) {
        Attendee attendee = (Attendee) getUser(attendeeID);
        attendee.addEvents(roomID, eventID);
    }

    public void attendeeRemoveEvent(UUID attendeeID, UUID roomID, UUID eventID) {
        Attendee attendee = (Attendee) getUser(attendeeID);
        attendee.removeReservedEvents(roomID, eventID);
    }

    public boolean userExists(String username) {
        return getUsernames().contains(username);
    }

    public boolean isSpeaker(String username) {
        return getUser(username).isSpeaker();
    }

    public String stringAvailableSpeakers() {
        StringBuilder s = new StringBuilder("Speakers available: ");
        ArrayList<Speaker> speakers = getSpeakers();
        for (int i = 0; i < speakers.size(); i++) {
            s.append(speakers.get(i).getUsername());
            if (i < speakers.size() - 1) {
                System.out.print(", ");
            }
        } s.append('\n');
        return s.toString();
    }

    public List<UUID> getMessagesFromUser(UUID recipientID, UUID senderID) {
        return getUser(recipientID).getMessages(senderID);
    }

    public void addMessage(UUID recipientID, UUID senderID, UUID messageID) {
        getUser(recipientID).addMessage(senderID, messageID);
    }

    public UUID getUserID(String username) {
        return getUser(username).getUserID();
    }

    public String getUsername(UUID UserID) {
        return getUser(UserID).getUsername();
    }

    /**
     * @param userID The UUID of the user to check.
     * @return UUIDs of users that this user has received messages from.
     */
    Set<UUID> getUserContacts(UUID userID) {
        return getUser(userID).getContacts();
    }
}
