package com.group0179.presenters;

import com.group0179.use_cases.MessageManager;
import com.group0179.use_cases.RoomManager;
import com.group0179.use_cases.UserManager;

import java.util.ArrayList;

public class OrganizerPresenterEN extends Presenter implements IOrganizerPresenter {
    private final UserManager um;
    private final RoomManager rm;
    private final MessageManager mm;

    public OrganizerPresenterEN(UserManager um, RoomManager rm, MessageManager mm) {
        this.um = um;
        this.rm = rm;
        this.mm = mm;
    }

    /**
     * @return a list of string representing speakers' names.
     */
    public ArrayList<String> getSpeakerNames() {
        return um.getSpeakerNames();
    }

    /**
     * @return a list of string representing the rooms' infomation.
     */
    public ArrayList<String> getRoomListArray() {
        ArrayList<String> rooms = new ArrayList<>();
        for (int n = 1; n <= rm.getNumRooms(); n++) {
            rooms.add("Room " + n + " (Capacity: " + rm.getRoomCapacity(n - 1) + ")");
        } return rooms;
    }

    private int getRoomNumber(String roomsListSelection) {
        return Integer.parseInt(roomsListSelection.split(" ")[1]) - 1;
    }

    /**
     * @param roomsListSelection a string representing the selection of room
     * @return return a list of string representing the events' information in the room.
     */
    public ArrayList<String> getEvents(String roomsListSelection) {
        return rm.getEventsOfRoom(getRoomNumber(roomsListSelection));
    }

    /**
     * @return a string representing "room/event".
     */
    public String reMenuButtonText() {
        return "Rooms/Events";
    }

    /**
     * @return a string representing "Speaker".
     */
    public String speakerManagementButtonText() {
        return "Speakers";
    }

    /**
     * @return Create Account
     */
    public String createAccountButtonText(){return "Create Account";}
    /**
     * @return a string representing "logout".
     */
    public String logoutButtonText() {
        return "Log out";
    }

    /**
     * @return a string representing "create speaker"
     */
    public String createSpeakerFormButtonText() {
        return "Create Speaker";
    }

    /**
     * @return a string representing "list of speakers"
     */
    public String speakerManagerButtonText() {
        return "Speakers";
    }

    /**
     * @return a string representing "create"
     */
    public String createButtonText() {
        return "Create";
    }

    /**
     * @return a string representing "room/event list".
     */
    public String reManagerButtonText() {
        return "Rooms/Events";
    }

    /**
     * @return a string representing "create room".
     */
    public String createRoomFormButtonText() {
        return "Create Room";
    }

    /**
     * @return a string representing "view events".
     */
    public String viewEventListButtonText() {
        return "View Events";
    }

    /**
     * @return a string representing "create event".
     */
    public String createEventFormButtonText() {
        return "Create Event";
    }

    /**
     * @return a string representing "confirm".
     */
    public String confirmButtonText() {
        return "Confirm";
    }

    /**
     * @return a string representing "remove event".
     */
    public String removeEventButtonText() {
        return "Remove Event";
    }

    /**
     * @return a string representing "reschedule event"
     */
    public String rescheduleEventFormButtonText() {
        return "Reschedule Event";
    }

    /**
     * @return a string representing "reschedule".
     */
    public String rescheduleEventButtonText() {
        return "Reschedule";
    }

    /**
     * @return a string representing "organizer panel:room and event list".
     */
    public String reMenuTitle() {
        return "Organizer Panel: Rooms and Events";
    }

    /**
     * @return a string representing "organizer panel: speaker".
     */
    public String speakerManagementMenuTitle() {
        return "Organizer Panel: Speakers";
    }

    /**
     * @return a string representing "organizer panel: create speaker".
     */
    public String createSpeakerFormTitle() {
        return "Organizer Panel: Create Speakers";
    }

    /**
     * @return a string representing "organizer panel: manage speaker".
     */
    public String speakerManagerTitle() {
        return "Organizer Panel: Speaker Management";
    }

    /**
     * @return a string representing "organizer panel: Main Menu".
     */
    public String mainSceneTitle() {
        return "Organizer Panel: Main Menu";
    }

    /**
     * @return a string representing "organizer panel: manage room & event".
     */
    public String reManagerTitle() {
        return "Organizer Panel: Room & Event Management";
    }

    /**
     * @return a string representing "organizer panel: create room".
     */
    public String createRoomFormTitle() {
        return "Organizer Panel: Create Room";
    }

    /**
     * @return a string representing "organizer panel: manage event".
     */
    public String viewEventListTitle() {
        return "Organizer Panel: Manage Events";
    }

    /**
     * @return a string representing "organizer panel: create event".
     */
    public String createEventFormTitle() {
        return "Organizer Panel: Create Event";
    }

    /**
     * @return a string representing "organizer panel: reschedule event".
     */
    public String rescheduleEventFormTitle() {
        return "Organizer Panel: Reschedule Event";
    }

    /**
     * @return a string representing "username".
     */
    public String usernamePrompt() {
        return "Username:";
    }

    /**
     * @return a string representing "room capacity".
     */
    public String roomCapacityPrompt() {
        return "Room Capacity:";
    }

    /**
     * @return a string representing "name of event".
     */
    public String createEventTitlePrompt() {
        return "Event Title:";
    }

    /**
     * @return a string representing "event speaker".
     */
    public String createEventSpeakerPrompt() {
        return "Event Speaker:";
    }

    /**
     * @return a string representing "date [yyyy/mm/dd]:".
     */
    public String eventDatePrompt() {
        return "Date [yyyy/mm/dd]:";
    }

    /**
     * @return End date [yyyy/mm/dd]:
     */
    @Override
    public String eventEndDatePrompt() {
        return "End date [yyyy/mm/dd]:";
    }

    /**
     * @return End time [hh:mm]:
     */
    @Override
    public String eventEndTimePrompt() {
        return "End time [hh:mm]:";
    }

    /**
     * @return a string representing "time [hh:mm]".
     */
    public String eventTimePrompt() {
        return "Time [hh:mm]:";
    }

    /**
     * @return a string representing "event capacity".
     */
    public String createEventCapacityPrompt() {
        return "Event Capacity:";
    }

    /**
     * @return a string representing whether the speaker is created.
     */
    public String createSpeakerStatus(boolean status) {
        if (status) {
            return "Speaker created successfully.";
        } return "Username exists/invalid.";
    }

    /**
     * @return a string representing whether the room is created.
     */
    public String createRoomStatus(boolean status) {
        if (status) {
            return "Room " + rm.getNumRooms() + " created successfully.";
        } return "Invalid room capacity.";
    }

    /**
     * @return a string representing "invalid event name".
     */
    public String createEventTitleStatus() {
        return "Invalid event title.";
    }

    /**
     * @return a string representing "speaker doesn't exist".
     */
    public String createEventSpeakerStatus() {
        return "Speaker does not exist.";
    }

    /**
     * @return a string representing "invalid date or time: may be conflict".
     */
    public String createEventDateTimeStatus() {
        return "Invalid date or time - may be in conflict.";
    }

    /**
     * @return a string representing "invalid room capacity".
     */
    public String createEventCapacityStatus() {
        return "Invalid event capacity.";
    }

    /**
     * @return a string representing "the event created successfully".
     */
    public String createEventStatus() {
        return "Event created successfully.";
    }

    /**
     * @return a string representing whether the event is scheduled.
     */
    public String rescheduleEventStatus(boolean status) {
        if (status) {
            return "Rescheduled event successfully.";
        } else {
            return "Could not reschedule event - may be in conflict";
        }
    }

    /**
     * @return a string representing "message".
     */
    public String messageMenuButtonText() {
        return "Messages";
    }

    /**
     * @return a string representing "custom recipient".
     */
    public String messageCustomRecipientsFormButtonText() {
        return "Custom Recipients";
    }

    /**
     * @return a string representing "all attendees".
     */
    public String messageAttendeesFormButtonText() {
        return "All Attendees";
    }

    /**
     * @return a string representing "all speakers".
     */
    public String messageSpeakersFormButtonText() {
        return "All Speakers";
    }

    /**
     * @return a string representing "recipient".
     */
    public String recipientsPrompt() {
        return "Recipient:";
    }

    /**
     * @return a string representing "message".
     */
    public String messagePrompt() {
        return "Message:";
    }

    /**
     * @return a string representing "send".
     */
    public String messageSendButtonText() {
        return "Send";
    }

    /**
     * @return a string representing "recipient doesn't exist".
     */
    public String messageRecipientExistence() {
        return "Recipient does not exist.";
    }

    /**
     * @return a string representing "you can only send message to attendee/speaker".
     */
    public String messageRecipientValidity() {
        return "You can only send messages to speakers/attendees.";
    }

    /**
     * @return a string representing "message sent successfully".
     */
    public String messageRecipientStatus() {
        return "Message sent successfully.";
    }

    /**
     * @return a string representing "vip event".
     */
    @Override
    public String isVipOnlyPrompt() {
        return "Event Vip only";
    }

    /**
     * @return Statistics
     */
    @Override
    public String staistics() {
        return "Statistics";
    }

    /**
     * @return Zuckerberg powers
     */
    @Override
    public String zuckerbergPowers() {
        return "Zuckerberg powers";
    }

    /**
     * @return "Accounts created on date:"
     */
    @Override
    public String accountsCreatedOnDate() {
        return "Accounts created on date:";
    }

    /**
     * @return Enter attendee usernames
     */
    @Override
    public String enterAttendeeUsernames() {
        return "Enter attendee usernames:";
    }

    /**
     * @return Get their info
     */
    @Override
    public String getTheirInfo() {
        return "Get their info";
    }

    /**
     * @return Average login time
     */
    @Override
    public String averageLoginTime() {
        return "Average login time: ";
    }

    /**
     * @return Last login:
     */
    @Override
    public String lastLogin() {
        return "Last login: ";
    }

    /**
     * @return User not found
     */
    @Override
    public String userNotFound() {
        return "User not found";
    }

    /**
     * @return Attendee Account
     */
    @Override
    public String AttendeeAccountPrompt() {
        return "Attendee Account";
    }

    /**
     * @return Organizer Account
     */
    @Override
    public String OrganizerAccountPrompt() {
        return "Organizer Account";
    }

    /**
     * @return Speaker Account
     */
    @Override
    public String SpeakerAccountPrompt() {
        return "Speaker Account";
    }

    /**
     * @return Vip Attendee Account
     */
    @Override
    public String VipAttendeeAccountPrompt() {
        return "Vip Attendee Account";
    }

    /**
     * @param username the username of user
     * @return the user name is taken
     */
    @Override
    public String usernameTaken(String username) {
        return "username" + username +"is taken";
    }

    /**
     * @return account created successfully.
     */
    @Override
    public String accountCreatedSuccess() {
        return "Account created successfully";
    }
}
