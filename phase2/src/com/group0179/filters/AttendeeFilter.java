package com.group0179.filters;

import com.group0179.use_cases.MessageManager;
import com.group0179.use_cases.RoomManager;
import com.group0179.use_cases.UserManager;

public class AttendeeFilter extends Filter {
    private final UserManager um;
    private final RoomManager rm;
    private final MessageManager mm;

    public AttendeeFilter(UserManager um, RoomManager rm, MessageManager mm) {
        this.um = um;
        this.rm = rm;
        this.mm = mm;
    }

    /**
     * Author: Justin Chan
     * @return the room index in rm.getRoomListArray(), or -1 if a cancel is requested
     */
    public int inputRoom() {
//        while (true) {
//            ip.inputRoomPrompt();
//            String roomRaw = sc.nextLine(); // get the desired roomNumber from the user
//            try {
//                int roomNumber = Integer.parseInt(roomRaw);
//                if (roomNumber == -1) {
//                    return -1; // send cancel signal
//                } else {
//                    if (0 < roomNumber && roomNumber <= rm.getNumRooms()) {
//                        return roomNumber - 1;
//                    } else {
//                        ip.inputRoomStatus();
//                    }
//                }
//            } catch (NumberFormatException e) {
//                ip.invalidInputNotification();
//            }
//        }
        return 3;
    }
    public int inputEventNumber(int roomNumber) {
//        while (true) {
//            ip.inputEventPrompt();
//            String eventRaw = sc.nextLine(); // get the desired roomNumber from the user
//            try {
//                int eventNumber = Integer.parseInt(eventRaw);
//                if (eventNumber == -1) {
//                    return -1; // send cancel signal
//                } else {
//                    if (0 < eventNumber && eventNumber <= rm.getNumEventsInRoom(roomNumber)) {
//                        return eventNumber - 1;
//                    } else {
//                        ip.inputEventStatus();
//                    }
//                }
//            } catch (NumberFormatException e) {
//                ip.invalidInputNotification();
//            }
//        }
        return 3;
    }
}
