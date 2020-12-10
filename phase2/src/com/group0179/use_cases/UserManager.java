package com.group0179.use_cases;

import com.group0179.entities.*;

import java.io.Serializable;
import java.util.*;

/**
 * Get list of all users, attendees, speakers, or organizers in existence
 *
 * Get a list of all usernames in existence
 *
 * @author Zihan Wang, Justin Chan, Kaiyi Liu, Kerry
 */

public class UserManager implements Serializable {
    private final ArrayList<Attendee> attendees = new ArrayList<>();
    private final ArrayList<Organizer> organizers = new ArrayList<>();
    private final ArrayList<Speaker> speakers = new ArrayList<>();
    private final List<Request> userRequests = new ArrayList<>();
    private User currentUser;

    /**
     * Created a new UserManager.
     * Last modified: Justin Chan
     */
    public UserManager() {
        currentUser = null;
    }

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
     * Returns whether the user is an vip.
     * @param userid uuid of user
     * @return whether user is an vip.
     */
    public boolean isUserVip(UUID userid) {
        return this.getUser(userid).isVip();
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

    /**
     * @return an ArrayList of all usernames in existence
     */
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

    /**
     * Return a User object based on their names.
     * @param username the name of the user you wish to return.
     * @return the User that the provided name belongs to or null if that user is not found.
     */
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
    public UUID getCurrentUser() {
        if(currentUser == null) return null;
        return currentUser.getUserID(); }

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
     * @param username unique username requested
     * @param isVip whether its an attendee
     * @return the Attendee that was created
     * Last modified: Justin Chan
     *
     * Precondition: isValidUsername(username)
     */
    public UUID createAttendeeAccount(String username, boolean isVip) {
        Attendee attendee = new Attendee(username, isVip);
        attendees.add(attendee);
        return attendee.getUserID();
    }
    /**
     * @param username unique username requested
     * @return the User ID of the Attendee that was created
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
        if(getUsernames().contains(username)) return getUser(username).getStringType();
        return null;
    }

    /**
     * Precondition: getUser(speakerName).isSpeaker
     * @param speakerName the name of Speaker
     * @return an ArrayList of the Speaker's Events.
     */
    public ArrayList<UUID> getSpeakerEventIDs(String speakerName) {
        Speaker speaker = (Speaker) getUser(speakerName);

        ArrayList<UUID> eventIDs = new ArrayList<>();
        for (ArrayList<UUID> speakerEventIDs : speaker.getEventsSpeaking().values()) {
            eventIDs.addAll(speakerEventIDs);
        }
        return eventIDs;
    }

    /**
     * Precondition: getUser(speakerName).isSpeaker()
     * @param speakerID the unique UUID of Speaker.
     * @return an ArrayList of the Speaker's Events.
     */
    public ArrayList<UUID> getSpeakerEventIDs(UUID speakerID) {
        Speaker speaker = (Speaker) getUser(speakerID);

        ArrayList<UUID> eventIDs = new ArrayList<>();
        for (ArrayList<UUID> speakerEventIDs : speaker.getEventsSpeaking().values()) {
            eventIDs.addAll(speakerEventIDs);
        } return eventIDs;
    }

    /**
     * The Speaker with speakerName add an Event with eventID to the Room with roomID.
     * @param speakerName the name of the Speaker
     * @param roomID the UUID of Room you want to add the Event
     * @param eventID the UUID of Event you want to add
     * Precondition: getUser(speakerName).isSpeaker()
     */
    public void speakerAddEvent(String speakerName, UUID roomID, UUID eventID) {
        Speaker speaker = (Speaker) getUser(speakerName);
        speaker.addEvent(roomID, eventID);
    }

    /**
     * The Speaker with speakerName remove an Event with eventID to the Room with roomID.
     * @param speakerName the name of the Speaker
     * @param roomID the UUID of Room you want to remove the Event
     * @param eventID the UUID of Event you want to remove
     * Precondition: getUser(speakerName).isSpeaker()
     */
    public void speakerRemoveEvent(String speakerName, UUID roomID, UUID eventID) {
        Speaker speaker = (Speaker) getUser(speakerName);
        speaker.removeEvent(roomID, eventID);
    }

    /**
     * The Attendee with attendeeID add an Event with eventID in Room with roomID.
     * @param attendeeID the UUID of the Attendee
     * @param roomID the UUID of Room you want to add the event
     * @param eventID the UUID of the Event you want to add
     * precondition: getUser(attendeeID).isAttendee()
     */
    public void attendeeAddEvent(UUID attendeeID, UUID roomID, UUID eventID) {
        Attendee attendee = (Attendee) getUser(attendeeID);
        attendee.addEvents(roomID, eventID);
    }

    /**
     * The Attendee with attendeeID remove an Event with eventID in Room with roomID.
     * @param attendeeID the UUID of the Attendee
     * @param roomID the UUID of Room you want to remove the event
     * @param eventID the UUID of the Event you want to remove
     * precondition: getUser(attendeeID).isAttendee()
     */
    public void attendeeRemoveEvent(UUID attendeeID, UUID roomID, UUID eventID) {
        Attendee attendee = (Attendee) getUser(attendeeID);
        attendee.removeReservedEvents(roomID, eventID);
    }
    //kaiyi

    /**
     * add logged in time on the current User
     * @param time the length of logged in time
     * @return true if the time added successfully, otherwise false.
     */
    public boolean addNewTimeLoggedInForCurrentUser(double time){ //dont need
        return this.currentUser.addNewTimeLoggedIn(time);
    }

    //kaiyi

    /**
     * add last logged in time to current user
     * @param calendar the last logged in time
     */
    public void addLastLoggedInForCurrentUser(Calendar calendar){ //dont need
        this.currentUser.setLastLoggedIn(calendar);
    }

    //kaiyi
    /**
     * Retrieve a list containing a list of all the times users were logged in as minutes
     * @return a list of lengths of logged in time of the current User as minutes.
     */
    public List<Double> getLengthsOfTimeLoggedInAsMinutesForCurrentUser(){ //need
        return this.currentUser.getLengthsOfTimeLoggedIn();
    }

    //kaiyi
    /**
     * @return the time of last logged in time of the current User
     */
    public Calendar getLastLoggedInForCurrentUser(){ //need
        return this.currentUser.getLastLoggedIn();
    }

    //kaiyi
    /**
     * @return the average length of logged in time of the current User
     */
    public double getAverageLengthOfTimeLoggedInForCurrentUser(){ //need
        return this.currentUser.getAverageLengthOfTimeLoggedIn();
    }

    //kaiyi
    /**
     * @return total length of logged in time of the current User
     */
    public double getTotalMinutesLoggedInForCurrentUser(){ //need
        return this.currentUser.getTotalMinutesLoggedIn();
    }

    //kaiyi
    /**
     * @return the maximum and minimum length of logged in time of the current User as an array.
     */
    public double[] getMaximumAndMinimumMinutesLoggedInForCurrentUser(){ //need
        return this.currentUser.getMaximumAndMinimumMinutesLoggedIn();
    }

    //kaiyi - get data on each Attendee/Speaker in the system for organizer given
    //currently using a nested private class but we can change this to an array actually!
    /**
     * Retrieve a map where the key is the attendee's user name and the value is an object containing
     * info on how long speaker was logged in for
     * @return a map containing logged in information for each attendee in the system.
     */
    public Map<String, UserTimeData> getTimeElapsedStatisticsForAllAttendees(){
        Map<String, UserTimeData> attendeeData = new HashMap<>();
        for(Attendee attendee: this.getAttendees()){
            double averageLengthOfTimeLoggedIn = attendee.getAverageLengthOfTimeLoggedIn();
            double totalLengthOfTimeLoggedIn = attendee.getTotalMinutesLoggedIn();
            double[] maximumAndMinimum = attendee.getMaximumAndMinimumMinutesLoggedIn();
            double maximumLengthOfTimeLoggedIn = maximumAndMinimum[0];
            double minimumLengthOfTimeLoggedIn = maximumAndMinimum[1];
            List<Double> lengthsOfTimeLoggedIn = attendee.getLengthsOfTimeLoggedIn();
            Calendar lastLoggedIn = attendee.getLastLoggedIn();

            UserTimeData dataObject = new UserTimeData(lastLoggedIn, totalLengthOfTimeLoggedIn, averageLengthOfTimeLoggedIn,
                        maximumLengthOfTimeLoggedIn, minimumLengthOfTimeLoggedIn, lengthsOfTimeLoggedIn);
            attendeeData.putIfAbsent(attendee.getUsername(), dataObject);
        }
        return attendeeData;
    }
//kaiyi
    /**
     * Retrieve a map where the key is the speaker's user name and the value is an object containing
     * info on how long speaker was logged in for
     * @return a map containing logged in information for each speaker in the system.
     */
    public Map<String, UserTimeData> getTimeElapsedStatisticsForAllSpeakers(){
        Map<String, UserTimeData> speakerData = new HashMap<>();
        for(Speaker speaker: this.getSpeakers()){
            double averageLengthOfTimeLoggedIn = speaker.getAverageLengthOfTimeLoggedIn();
            double totalLengthOfTimeLoggedIn = speaker.getTotalMinutesLoggedIn();
            double[] maximumAndMinimum = speaker.getMaximumAndMinimumMinutesLoggedIn();
            double maximumLengthOfTimeLoggedIn = maximumAndMinimum[0];
            double minimumLengthOfTimeLoggedIn = maximumAndMinimum[1];
            List<Double> lengthsOfTimeLoggedIn = speaker.getLengthsOfTimeLoggedIn();
            Calendar lastLoggedIn = speaker.getLastLoggedIn();

            UserTimeData dataObject = new UserTimeData(lastLoggedIn, totalLengthOfTimeLoggedIn,
                        averageLengthOfTimeLoggedIn, maximumLengthOfTimeLoggedIn, minimumLengthOfTimeLoggedIn,
                        lengthsOfTimeLoggedIn);
            speakerData.putIfAbsent(speaker.getUsername(), dataObject);
        }
        return speakerData;
    }
    //Kaiyi: This is a strut for storing user time data. This makes it much easier to get important info for
    // each user in terms of how long they were logged in form for each login
    public class UserTimeData {
        public Calendar lastLoggedIn;
        public double averageLengthOfTimeLoggedIn;
        public double totalLengthOfTimeLoggedIn;
        public double maximumLengthOfTimeLoggedIn;
        public double minimumLengthOfTimeLoggedIn;
        public List<Double> lengthsOfTimeLoggedIn;

        UserTimeData(Calendar lastLoggedIn, double totalLengthOfTimeLoggedIn,
                     double averageLengthOfTimeLoggedIn, double maximumLengthOfTimeLoggedIn,
                     double minimumLengthOfTimeLoggedIn, List<Double> lengthsOfTimeLoggedIn){
            this.lastLoggedIn = lastLoggedIn;
            this.totalLengthOfTimeLoggedIn = totalLengthOfTimeLoggedIn;
            this.averageLengthOfTimeLoggedIn = averageLengthOfTimeLoggedIn;
            this.maximumLengthOfTimeLoggedIn = maximumLengthOfTimeLoggedIn;
            this.minimumLengthOfTimeLoggedIn = minimumLengthOfTimeLoggedIn;
            this.lengthsOfTimeLoggedIn = lengthsOfTimeLoggedIn;
        }

    }
    //kaiyi
    /**
     * Retrieve a map where the key is a string of the date and the value is an int containing the number of attendees
     * that created an account on that day
     * @return a map containing the number of attendees logged in per day since launch of project
     */
    public Map<String, Integer> getTimeLineOfAttendeeCreation(){
        Map<String, Integer> historyOfAttendeeCreation = new HashMap<>();
        for(Attendee attendee : this.getAttendees()){
            String yearOfCreation = String.valueOf(attendee.getTimeOfAccountCreation().get(Calendar.YEAR));
            int monthOfCreation = (attendee.getTimeOfAccountCreation().get(Calendar.MONTH) + 1);
            int dayOfCreation = (attendee.getTimeOfAccountCreation().get(Calendar.DAY_OF_MONTH));
            String month = String.format("%02d", monthOfCreation);
            String day = String.format("%02d", dayOfCreation);
            String date = yearOfCreation + "/" + month + "/" + day;
            if(historyOfAttendeeCreation.containsKey(date)){
                int numberOfAttendeesCreated = historyOfAttendeeCreation.get(date);
                historyOfAttendeeCreation.put(date, numberOfAttendeesCreated + 1);
            } else {
                historyOfAttendeeCreation.put(date, 1);
            }
        }
        return historyOfAttendeeCreation;
    }

    //kaiyi
    /**
     * Retrieve a map where the key is a string of the date and the value is an int containing the number of speakers
     * that created an account on that day
     * @return a map containing the number of speaker accounts created per day since launch of project
     */
    public Map<String, Integer> getTimeLineOfSpeakerCreation(){
        Map<String, Integer> historyOfSpeakerCreation = new HashMap<>();
        for(Speaker speaker : this.getSpeakers()){
            String yearOfCreation = String.valueOf(speaker.getTimeOfAccountCreation().get(Calendar.YEAR));
            int monthOfCreation = (speaker.getTimeOfAccountCreation().get(Calendar.MONTH) + 1);
            int dayOfCreation = (speaker.getTimeOfAccountCreation().get(Calendar.DAY_OF_MONTH));
            String month = String.format("%02d", monthOfCreation);
            String day = String.format("%02d", dayOfCreation);
            String date = yearOfCreation + "/" + month + "/" + day;
            if(historyOfSpeakerCreation.containsKey(date)){
                int numberOfSpeakersCreated = historyOfSpeakerCreation.get(date);
                historyOfSpeakerCreation.put(date, numberOfSpeakersCreated + 1);
            } else {
                historyOfSpeakerCreation.put(date, 1);
            }
        }
        return historyOfSpeakerCreation;
    }

    //kaiyi
    /**
     * Add a request given a request ID
     * @param typeOfRequest
     * @param urgency
     * @param requestContent
     * @return a UUID value which represents the ID of the created request
     */
    public UUID addUserRequest(String typeOfRequest, String urgency, String requestContent){
        Request userRequest = new Request(typeOfRequest, urgency, requestContent);
        this.userRequests.add(userRequest);
        return userRequest.getRequestID();
    }

    //kaiyi
    /**
     * Remove a request given a request ID
     * @param requestID
     * @return a boolean value which is true if request was deleted, otherwise it returns false
     */
    public boolean removeUserRequest(UUID requestID){
        if(this.getRequestIDs().contains(requestID)){
            for(int i = 0; i < this.userRequests.size(); i++){
                if(this.userRequests.get(i).getRequestID().equals(requestID)){
                    this.userRequests.remove(i);
                    return true;
                }
            }
        }
        return false;
    }
    //kaiyi
    /**
     * retrieve a list of request IDs for all requests in the system
     * @return a List of request IDs
     */
    public List<UUID> getRequestIDs(){
        List<UUID> requestIDs = new ArrayList<>();
        for(Request request : this.userRequests){
            requestIDs.add(request.getRequestID());
        }
        return requestIDs;
    }
    //kaiyi
    /**
     * Retrieve type of request given request ID
     * @param requestID
     * @return a string which represents the type of request for given request ID or null if request ID doesn't exist
     */
    public String getTypeOfRequestWithUUID(UUID requestID){
        Request foundRequest = this.getRequestWithUUID(requestID);
        if(foundRequest != null){
            return foundRequest.getTypeOfRequest();
        } else {
            return null;
        }
    }
    //kaiyi
    /**
     * Retrieve urgency given request ID
     * @param requestID
     * @return a string which represents the urgency level for given request ID or null if request ID doesn't exist
     */
    public String getUrgencyWithUUID(UUID requestID){
        Request foundRequest = this.getRequestWithUUID(requestID);
        if(foundRequest != null){
            return foundRequest.getUrgency();
        } else {
            return null;
        }
    }
    //kaiyi
    /**
     * Retrieve content of request given request ID
     * @param requestID
     * @return a string which represents the request content for given request ID or null if request ID doesn't exist
     */
    public String getRequestContentWithUUID(UUID requestID){
        Request foundRequest = this.getRequestWithUUID(requestID);
        if(foundRequest != null){
            return foundRequest.getRequestContent();
        } else {
            return null;
        }
    }
    //kaiyi
    /**
     * Retrieve pending value of request given request ID
     * @param requestID
     * @return true if request is pending or flse if request is not pending. Return null if request ID does not exist
     */
    public Boolean getPendingWithUUID(UUID requestID){
        Request foundRequest = this.getRequestWithUUID(requestID);
        if(foundRequest != null){
            return foundRequest.isPending();
        } else {
            return null;
        }
    }
    //kaiyi
    /**
     * Retrieve addressed value of request given request ID
     * @param requestID
     * @return true if request is addressed or false if request is not addressed. Return null if ID does not exist
     */
    public Boolean getAddressedWithUUID(UUID requestID){
        Request foundRequest = this.getRequestWithUUID(requestID);
        if(foundRequest != null){
            return foundRequest.isAddressed();
        } else {
            return null;
        }
    }
    //kaiyi
    /**
     * Set new pending value for request
     * @param isPending
     * @param requestID
     * @return true if request's pending value was changed, otherwise return false
     */
    public boolean setRequestPending(boolean isPending, UUID requestID){
        Request foundRequest = this.getRequestWithUUID(requestID);
        if(foundRequest != null){
            foundRequest.setPending(isPending);
            return true;
        } else {
            return false;
        }
    }

    //kaiyi
    /**
     * Set new addressed value for request
     * @param isAddressed
     * @param requestID
     * @return true if request's addressed value was changed, otherwise return false
     */
    public boolean setRequestAddressed(boolean isAddressed, UUID requestID){
        Request foundRequest = this.getRequestWithUUID(requestID);
        if(foundRequest != null){
            foundRequest.setAddressed(isAddressed);
            return true;
        } else {
            return false;
        }
    }

    //kaiyi
    //helper function to retrieve request object with request id
    private Request getRequestWithUUID(UUID requestID){
        if(this.getRequestIDs().contains(requestID)){
            for(Request request : this.userRequests){
                if(request.getRequestID().equals(requestID)){
                    return request;
                }
            }
        }
        return null;
    }

    public ArrayList<UUID> getRequestUUIDWithContent(String requestContent){
        ArrayList<UUID> requestUUIDs = new ArrayList<UUID>();
        for(Request request : this.userRequests){
            if(request.getRequestContent().equals(requestContent)){
                requestUUIDs.add(request.getRequestID());
            }
        }
        return requestUUIDs;
    }

    /**
     * @param username the username of a User
     * @return true if the User with the username exists, false otherwise.
     */
    public boolean userExists(String username) {
        return getUsernames().contains(username);
    }

    /**
     * @param username the username of a User
     * @return true if the User with this username is a Speaker.
     */
    public boolean isSpeaker(String username) {
        return getUser(username).isSpeaker();
    }

    /**
     * @return a String including all speakers' usernames.
     */
    public String stringAvailableSpeakers() {
        StringBuilder s = new StringBuilder("Speakers available: ");
        ArrayList<Speaker> speakers = getSpeakers();
        for (int i = 0; i < speakers.size(); i++) {
            s.append(speakers.get(i).getUsername());
            if (i < speakers.size() - 1) {
                s.append(", ");
            }
        } s.append('\n');
        return s.toString();
    }

    /**
     * @param recipientID the UserID of recipient
     * @param senderID the UserID of sender
     * @return a list of messageID which are sent from User with senderID to the User with recipientID.
     */
    public List<UUID> getMessagesFromUser(UUID recipientID, UUID senderID) {
        return getUser(recipientID).getMessages(senderID);
    }

    /**
     * add messages which is sent from the User with senderID to the User with recipientID
     * @param recipientID the UserID of recipient
     * @param senderID the UserID of sender
     * @param messageID the UUID of this message.
     */
    public void addMessage(UUID recipientID, UUID senderID, UUID messageID) {
        getUser(recipientID).addMessage(senderID, messageID);
    }

    /**
     *
     * @param username the username of the User
     * @return null if the username doesn't exist, otherwise UUID of the User
     */
    public UUID getUserID(String username) {
        User user = getUser(username);
        if (user == null) return null;
        return getUser(username).getUserID();
    }

    /**
     *
     * @param UserID the userID of the User
     * @return null if the userID doesn't exist, otherwise username of the User
     */
    public String getUsername(UUID UserID) {
        User user = getUser(UserID);
        if (user == null) return null;
        return getUser(UserID).getUsername();
    }

    /**
     * @param userID The UUID of the user to check.
     * @return UUIDs of users that this user has received messages from.
     */
    Set<UUID> getUserContacts(UUID userID) {
        return getUser(userID).getContacts();
    }

    public ArrayList<String> getSpeakerNames() {
        ArrayList<String> speakerNames = new ArrayList<>();
        for (Speaker speaker : speakers) {
            speakerNames.add(speaker.getUsername());
        } return speakerNames;
    }

    /**
     * return a list of usernames given a string query
     * @param query
     * @return a list of usernames queried using the query string
     */
    public List<String> retrieveUserNamesGivenQuery(String query){
        List<String> queriedUserNames = new ArrayList<>();
        for(User user : this.getUsers()){
            if(user.getUsername().toUpperCase().startsWith(query)){
                queriedUserNames.add(user.getUsername());
            } else if (query.startsWith(user.getUsername())){
                queriedUserNames.add(user.getUsername());
            }
        }
        return queriedUserNames;
    }

}
