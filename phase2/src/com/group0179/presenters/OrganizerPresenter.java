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

    public ArrayList<String> getRooms() {
        ArrayList<String> rooms = new ArrayList<>();
        for (int n = 1; n <= rm.getNumRooms(); n++) {
            rooms.add("Room " + n + " (Capacity: " + rm.getRoomCapacity(n - 1) + ")");
        } return rooms;
    }

    public String reButtonText() {
        return "Rooms/Events";
    }

    public String speakersMenuButtonText() {
        return "Speakers";
    }

    public String logoutButtonText() {
        return "Log out";
    }

    public String createSpeakerButtonText() {
        return "Create Speaker";
    }

    public String viewSpeakersButtonText() {
        return "View Speakers";
    }

    public String createButtonText() {
        return "Create";
    }

    public String viewRoomsButtonText() {
        return "View Rooms";
    }

    public String createRoomButtonText() {
        return "Create Room";
    }

    public String reSceneTitle() {
        return "Organizer Panel: Rooms and Events";
    }

    public String speakersMenuSceneTitle() {
        return "Organizer Panel: Speakers";
    }

    public String createSpeakerSceneTitle() {
        return "Organizer Panel: Create Speakers";
    }

    public String viewSpeakersSceneTitle() {
        return "Organizer Panel: View Speakers";
    }

    public String mainSceneTitle() {
        return "Organizer Panel: Main Menu";
    }

    public String viewRoomsSceneTitle() {
        return "Organizer Panel: View Rooms";
    }

    public String createRoomSceneTitle() {
        return "Organizer Panel: Create Room";
    }

    public String usernamePrompt() {
        return "Username: ";
    }

    public String roomCapacityPrompt() {
        return "Room Capacity: ";
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
