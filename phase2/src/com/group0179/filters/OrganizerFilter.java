package com.group0179.filters;

import com.group0179.use_cases.MessageManager;
import com.group0179.use_cases.RoomManager;
import com.group0179.use_cases.UserManager;

import java.util.GregorianCalendar;

/**
 * @author Justin Chan
 */

public class OrganizerFilter extends Filter {
    private final UserManager um;
    private final RoomManager rm;
    private final MessageManager mm;

    private final int START_HOUR_EARLIEST = 9;
    private final int START_HOUR_LATEST = 16;

    public OrganizerFilter(UserManager um, RoomManager rm, MessageManager mm) {
        this.um = um;
        this.rm = rm;
        this.mm = mm;
    }

    public boolean inputNewSpeakerUsername(String rawInput) {
        if (!um.getUsernames().contains(rawInput.trim()) && rawInput.trim().length() > 0) {
            um.createSpeakerAccount(rawInput.trim());
            return true;
        } return false;
    }

    public boolean inputRoomCapacity(String rawInput) {
        try {
            int input = Integer.parseInt(rawInput.trim());
            if (0 <= input) {
                rm.newRoom(input);
                return true;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean inputEventTitle(String rawInput) {
        return rawInput.trim().length() > 0;
    }

    public boolean inputEventSpeaker(String rawInput) {
        return um.userExists(rawInput.trim()) && um.isSpeaker(rawInput.trim());
    }

    public boolean inputEventCapacity(String rawInput, int roomNumber) {
        try {
            int capacity = Integer.parseInt(rawInput.trim());
            return capacity >= 0 && capacity <= rm.getRoomCapacity(roomNumber).get(1);
        } catch (Exception e) {
            return false;
        }
    }

    @SuppressWarnings("MagicConstant")
    public boolean inputEventDate(String rawTitle, String rawSpeaker, String rawDate, String rawTime, String rawCapacity, int roomNumber) {
        String title = rawTitle.trim();
        String speaker = rawSpeaker.trim();
        String[] date = rawDate.trim().split("/");
        String[] time = rawTime.trim().split(":");
        try {
            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]) - 1;
            int day = Integer.parseInt(date[2]);
            int hour = Integer.parseInt(time[0]);
            int minute = Integer.parseInt(time[1]);
            int capacity = Integer.parseInt(rawCapacity.trim());
            GregorianCalendar startTime = new GregorianCalendar(year, month, day, hour, minute, 0);
            startTime.setLenient(false); startTime.getTime();
            GregorianCalendar endTime = new GregorianCalendar(year, month, day, hour + 1, minute, 0);
            if (rm.newEventValid(title, speaker, startTime, endTime, roomNumber, um)) {
                rm.newEvent(title, speaker, startTime, endTime, roomNumber, um, capacity);
                return true;
            }
            return false;
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            return false;
        }
    }
}
