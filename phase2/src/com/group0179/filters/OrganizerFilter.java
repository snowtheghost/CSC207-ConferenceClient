package com.group0179.filters;

import com.group0179.use_cases.MessageManager;
import com.group0179.use_cases.RoomManager;
import com.group0179.use_cases.UserManager;

import java.util.*;

/**
 * @author Justin Chan, Kerry
 */

@SuppressWarnings("MagicConstant")
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
        if (rawInput.trim().isEmpty()) return true;
        String[] speakers = rawInput.trim().split(",");
        boolean speakersExist = true;
        for (String speaker : speakers) {
            if (!um.userExists(speaker.trim()) || !um.isSpeaker(speaker.trim())) {
                System.out.println(speaker + " does not exist.");
                speakersExist = false;
            }
        }
        return speakersExist;
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
    public boolean createEvent(String rawTitle, String rawSpeaker, String rawDate, String rawTime, String rawCapacity, int roomNumber,
                               Boolean isVipOnly) {
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

            // Determine event type based on the speaker name input
            ArrayList<String> speakerNames = new ArrayList<>(Arrays.asList(rawSpeaker.split(",")));
            for (int i = 0; i < speakerNames.size(); i++) {
                speakerNames.set(i, speakerNames.get(i).trim());
            }
            UUID newEvent;
            if (rawSpeaker.isEmpty()) {
                if (rm.newNonSpeakerEventValid(title, startTime, endTime, roomNumber, um))
                    newEvent = rm.newNonSpeakerEvent(title, startTime, endTime, roomNumber, um, capacity);
                else return false;
            } else if (speakerNames.size() == 1) {
                if (rm.newEventValid(title, speakerNames.get(0), startTime, endTime, roomNumber, um))
                    newEvent = rm.newEvent(title, speaker, startTime, endTime, roomNumber, um, capacity);
                else return false;
            } else {
                if (rm.newMultiSpeakerEventValid(title, speakerNames, startTime, endTime, roomNumber, um))
                    newEvent = rm.newMultiSpeakerEvent(title, speakerNames, startTime, endTime, roomNumber, um, capacity);
                else return false;
            }
            if (isVipOnly) rm.updateVipStatus(isVipOnly, newEvent);
            return true;
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            return false;
        }
    }

    public boolean removeEvent(int roomNumber, int eventNumber) {
        rm.removeEvent(um, roomNumber, eventNumber);
        return true;
    }

    public boolean rescheduleEvent(int roomNumber, int eventNumber, String rawDate, String rawTime) {
        String[] date = rawDate.trim().split("/");
        String[] time = rawTime.trim().split(":");
        try {
            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]) - 1;
            int day = Integer.parseInt(date[2]);
            int hour = Integer.parseInt(time[0]);
            int minute = Integer.parseInt(time[1]);

            GregorianCalendar startTime = new GregorianCalendar(year, month, day, hour, minute, 0);
            startTime.setLenient(false); startTime.getTime();
            GregorianCalendar endTime = new GregorianCalendar(year, month, day, hour + 1, minute, 0);

            return rm.rescheduleEvent(um, roomNumber, eventNumber, startTime, endTime);
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            return false;
        }
    }

    public boolean recipientExists(String rawRecipient) {
        String recipient = rawRecipient.trim();
        return um.userExists(recipient);
    }

    public boolean recipientIsValid(String rawRecipient) {
        String recipient = rawRecipient.trim();
        return um.userType(recipient).equals("speaker") || um.userType(recipient).equals("attendee");
    }

    public void sendRecipientMessage(String rawRecipient, String message) {
        String recipient = rawRecipient.trim();
        mm.sendMessage(um, um.getCurrentUser(), um.getUserID(recipient), message);
    }

    public void sendAttendeesMessage(String message) {
        mm.sendMessageToAllAttendees(um, um.getCurrentUser(), message);
    }

    public void sendSpeakersMessage(String message) {
        mm.sendMessageToAllSpeakers(um, um.getCurrentUser(), message);
    }

    /**
     * Gets user info for user if user exists
     * @param userName user name of user
     * @return [] if no user found/user never logged in, else ['avg login time', 'last login date']
     */
    public ArrayList<String> getUserInfo(String userName){
        UUID userid = um.getUserID(userName);
        if (userid == null){
            return new ArrayList<String>();
        }
        UserManager.UserTimeData userInfo = um.getTimeElapsedStatisticsForAllAttendees().get(userName);
        ArrayList<String> infoList = new ArrayList<>();
        infoList.add(String.valueOf(userInfo.averageLengthOfTimeLoggedIn));
        if (userInfo.lastLoggedIn != null){
         infoList.add(userInfo.lastLoggedIn.getTime().toString());
        } else {
            infoList.add("");
        }
        return infoList;
    }

    /**
     * @return Dates with number of attendee accounts created on date.
     */
    public Map<String, Integer> getActCreations(){
        return this.um.getTimeLineOfAttendeeCreation();
    }
}
