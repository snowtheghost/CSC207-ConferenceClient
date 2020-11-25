package com.group0179.presenters;

import com.group0179.use_cases.MessageManager;
import com.group0179.use_cases.RoomManager;
import com.group0179.use_cases.UserManager;

import java.util.ArrayList;

public class OrganizerPresenter extends Presenter {
    private final UserManager um;
    private final RoomManager rm;
    private final MessageManager mm;

    private final int START_HOUR_EARLIEST = 9;
    private final int START_HOUR_LATEST = 16;

    public OrganizerPresenter(UserManager um, RoomManager rm, MessageManager mm) {
        this.um = um;
        this.rm = rm;
        this.mm = mm;
    }

    public ArrayList<String> getSpeakerNames() {
        return um.getSpeakerNames();
    }

    public ArrayList<String> getRoomListArray() {
        ArrayList<String> rooms = new ArrayList<>();
        for (int n = 1; n <= rm.getNumRooms(); n++) {
            rooms.add("Room " + n + " (Capacity: " + rm.getRoomCapacity(n - 1) + ")");
        } return rooms;
    }

    public int getRoomNumber(String roomsListSelection) {
        return Integer.parseInt(roomsListSelection.split(" ")[1]) - 1;
    }

    public ArrayList<String> getEvents(String roomsListSelection) {
        return rm.getEventsOfRoom(getRoomNumber(roomsListSelection));
    }

    public String reMenuButtonText() {
        return "Rooms/Events";
    }

    public String speakerManagementButtonText() {
        return "Speakers";
    }

    public String logoutButtonText() {
        return "Log out";
    }

    public String createSpeakerFormButtonText() {
        return "Create Speaker";
    }

    public String speakerListButtonText() {
        return "View Speakers";
    }

    public String createButtonText() {
        return "Create";
    }

    public String viewRoomListButtonText() {
        return "View Rooms";
    }

    public String createRoomFormButtonText() {
        return "Create Room";
    }

    public String viewEventListButtonText() {
        return "View Events";
    }

    public String createEventButtonText() {
        return "Create Event";
    }

    public String confirmButtonText() {
        return "Confirm";
    }

    public String reMenuTitle() {
        return "Organizer Panel: Rooms and Events";
    }

    public String speakerManagementMenuTitle() {
        return "Organizer Panel: Speakers";
    }

    public String createSpeakerFormTitle() {
        return "Organizer Panel: Create Speakers";
    }

    public String speakerListTitle() {
        return "Organizer Panel: View Speakers";
    }

    public String mainSceneTitle() {
        return "Organizer Panel: Main Menu";
    }

    public String viewRoomListTitle() {
        return "Organizer Panel: View Rooms";
    }

    public String createRoomFormTitle() {
        return "Organizer Panel: Create Room";
    }

    public String viewEventListTitle() {
        return "Organizer Panel: View Events";
    }

    public String createEventSceneTitle() {
        return "Organizer Panel: Create Event";
    }

    public String usernamePrompt() {
        return "Username: ";
    }

    public String roomCapacityPrompt() {
        return "Room Capacity: ";
    }

    public String eventTitlePrompt() {
        return "Event Title: ";
    }

    public String createSpeakerStatus(boolean status) {
        if (status) {
            return "Speaker created successfully.";
        } return "Username already taken.";
    }

    public String createRoomStatus(boolean status) {
        if (status) {
            return "Room " + rm.getNumRooms() + " created successfully.";
        } return "Invalid room capacity.";
    }
}
