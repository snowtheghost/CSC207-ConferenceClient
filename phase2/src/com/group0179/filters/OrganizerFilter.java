package com.group0179.filters;

import com.group0179.use_cases.MessageManager;
import com.group0179.use_cases.RoomManager;
import com.group0179.use_cases.UserManager;

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

    public boolean inputNewSpeakerUsername(String input) {
        if (!um.getUsernames().contains(input) || input.trim().length() > 0) {
            um.createSpeakerAccount(input);
            return true;
        } return false;
    }

    public boolean inputRoomCapacity(String rawInput) {
        try {
            int input = Integer.parseInt(rawInput);
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
}
