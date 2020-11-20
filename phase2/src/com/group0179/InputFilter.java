package com.group0179;

import com.group0179.presenters.InputPresenter;
import com.group0179.use_cases.RoomManager;
import com.group0179.use_cases.UserManager;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * @author Justin Chan
 */

public class InputFilter {
    private InputPresenter ip = new InputPresenter();
    private Scanner sc = new Scanner(System.in);
    private UserManager um;
    private RoomManager rm;

    private final int START_HOUR_EARLIEST = 9;
    private final int START_HOUR_LATEST = 16;

    InputFilter(UserManager um, RoomManager rm) {
        this.um = um;
        this.rm = rm;
    }

    /**
     * Author: Justin Chan
     * @return the room index in rm.getRooms(), or -1 if a cancel is requested
     */
     public int inputRoom() {
        while (true) {
             ip.inputRoomPrompt();
            String roomRaw = sc.nextLine(); // get the desired roomNumber from the user
            try {
                int roomNumber = Integer.parseInt(roomRaw);
                if (roomNumber == -1) {
                    return -1; // send cancel signal
                } else {
                    if (0 < roomNumber && roomNumber <= rm.getNumRooms()) {
                        return roomNumber - 1;
                    } else {
                         ip.inputRoomStatus();
                    }
                }
            } catch (NumberFormatException e) {
                 ip.invalidInputNotification();
            }
        }
    }

     public int inputEventNumber(int roomNumber) {
        while (true) {
             ip.inputEventPrompt();
            String eventRaw = sc.nextLine(); // get the desired roomNumber from the user
            try {
                int eventNumber = Integer.parseInt(eventRaw);
                if (eventNumber == -1) {
                    return -1; // send cancel signal
                } else {
                    if (0 < eventNumber && eventNumber <= rm.getNumEventsInRoom(roomNumber)) {
                        return eventNumber - 1;
                    } else {
                         ip.inputEventStatus();
                    }
                }
            } catch (NumberFormatException e) {
                 ip.invalidInputNotification();
            }
        }
    }

    /**
     * Author: Justin Chan
     * @return the validated username (username exists and corresponds to a speaker) or -1 if a cancel is requested
     */
     public String inputSpeakerUsername() {
        while (true) {
             ip.inputSpeakerNamePrompt();
            String speakerName = sc.nextLine();
            if (speakerName.equals("-1")) {
                return speakerName;
            }

            if (um.userExists(speakerName)) {
                if (um.isSpeaker(speakerName)) {
                    return speakerName;
                }  ip.inputSpeakerNameStatusNonSpeaker(speakerName);
            }  ip.inputSpeakerNameStatusDNE(speakerName);
        }
    }

     public String inputNewSpeakerUsername(String userType) {
        while (true) {
             ip.inputNewUserNamePrompt(userType);
            String speakerName = sc.nextLine();
            if (speakerName.equals("-1")) {
                return speakerName;
            }

            if (!um.getUsernames().contains(speakerName)) {
                um.createSpeakerAccount(speakerName);
                return speakerName;
            }  ip.inputNewSpeakerNameStatus(speakerName);
        }
    }

    /**
     * Author: Justin Chan
     * @return the validated year or -1 if a cancel is requested
     */
     public int inputYear() {
        while (true) {
             ip.inputYearPrompt();
            try {
                int year = Integer.parseInt(sc.nextLine());
                if (year == -1) {
                    return -1;
                } if (year >= Calendar.getInstance().get(Calendar.YEAR)) {
                    return year;
                }  ip.inputYearStatus();
            } catch (NumberFormatException e) {
                 ip.invalidInputNotification();
            }
        }
    }

    /**
     * Author: Justin Chan
     * @param year the validated year of the date input
     * @return the validated hour or -1 if a cancel is requested
     */
     public int inputMonth(int year) {
        while (true) {
             ip.inputMonthPrompt();
            try {
                int month = Integer.parseInt(sc.nextLine());
                if (month == -1) {
                    return -1;
                } if (year == Calendar.getInstance().get(Calendar.YEAR)) {
                    if (Calendar.getInstance().get(Calendar.MONTH) <= month - 1 && month <= 12) {
                        return month - 1;
                    }
                } else {
                    if (1 <= month && month <= 12) {
                        return month - 1;
                    }
                }  ip.inputMonthStatus(month);
            } catch (NumberFormatException e) {
                 ip.invalidInputNotification();
            }
        }
    }

    /**
     * Author: Justin Chan
     * @param year the validated year of the date input
     * @param month the validated month of the date input
     * @return the validated day or -1 if a cancel is requested
     */
     public int inputDay(int year, int month) {
        while (true) {
             ip.inputDayPrompt();
            try {
                int day = Integer.parseInt(sc.nextLine());
                if (day == -1) {
                    return -1;
                } if (year == Calendar.getInstance().get(Calendar.YEAR) && month == Calendar.getInstance().get(Calendar.MONTH)) {
                    if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) <= day) {
                        try {
                            GregorianCalendar check = new GregorianCalendar(year, month, day);
                            check.setLenient(false);
                            check.get(Calendar.MONTH);
                            return day;
                        } catch (IllegalArgumentException ignored) { }
                    }  ip.inputDayStatus(day);
                } else {
                    try {
                        GregorianCalendar check = new GregorianCalendar(year, month, day);
                        check.get(Calendar.MONTH);
                        return day;
                    } catch (IllegalArgumentException e) {
                         ip.inputDayStatus(day);
                    }
                }
            } catch (NumberFormatException e) {
                 ip.invalidInputNotification();
            }
        }
    }

    /**
     * Author: Justin Chan
     * @param year the validated year of the date input
     * @param month the validated month of the date input
     * @param day the validated day of the date input
     * @return the validated hour or -1 if a cancel is requested
     */
     public int inputHour(int year, int month, int day) {
        while (true) {
             ip.inputHourPrompt(START_HOUR_EARLIEST, START_HOUR_LATEST);
            try {
                int hour = Integer.parseInt(sc.nextLine());
                if (hour == -1) {
                    return -1;
                }
                if (year == Calendar.getInstance().get(Calendar.YEAR) && month == Calendar.getInstance().get(Calendar.MONTH) && day == Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) {
                    if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) <= hour && START_HOUR_EARLIEST <= hour && hour <= START_HOUR_LATEST) {
                        return hour;
                    }
                } else {
                    if (START_HOUR_EARLIEST <= hour && hour <= START_HOUR_LATEST) {
                        return hour;
                    }
                }  ip.inputHourStatus(hour);
            } catch (NumberFormatException e) {
                 ip.invalidInputNotification();
            }
        }
    }

    /**
     * Author: Justin Chan
     * @return the validated minute or -1 if a cancel is requested
     */
     public int inputMinute() {
        while (true) {
             ip.inputMinutePrompt();
            try {
                int minute = Integer.parseInt(sc.nextLine());
                if (-1 <= minute && minute < 60) {
                    return minute;
                }  ip.inputMinuteStatus(minute);
            } catch (NumberFormatException e) {
                 ip.invalidInputNotification();
            }
        }
    }
}
